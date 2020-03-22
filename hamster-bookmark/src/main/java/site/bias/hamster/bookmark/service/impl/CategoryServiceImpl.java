package site.bias.hamster.bookmark.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import site.bias.hamster.bookmark.bean.Response;
import site.bias.hamster.bookmark.bean.vo.CategoryVO;
import site.bias.hamster.bookmark.constant.ErrorCodeEnum;
import site.bias.hamster.bookmark.mapper.BookmarkRecordMapper;
import site.bias.hamster.bookmark.mapper.CategoryRecordMapper;
import site.bias.hamster.bookmark.pojo.BookmarkRecord;
import site.bias.hamster.bookmark.pojo.BookmarkRecordExample;
import site.bias.hamster.bookmark.pojo.CategoryRecord;
import site.bias.hamster.bookmark.pojo.CategoryRecordExample;
import site.bias.hamster.bookmark.service.CategoryService;
import site.bias.hamster.bookmark.util.TokenUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenbinbin
 * @date 2020/3/22 17:55
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryRecordMapper categoryMapper;

    @Resource
    private BookmarkRecordMapper bookmarkMapper;

    @Override
    public Response add(String title, Integer parentId) throws Exception {
        CategoryRecord categoryRecord = new CategoryRecord();
        categoryRecord.setTitle(title);
        categoryRecord.setParentId(parentId);
        //获取父分类的parents信息
        CategoryRecord parentRecord = categoryMapper.selectByPrimaryKey(parentId);
        if (null == parentRecord) {
            log.warn("category/add:不存在的分类ID:{} userCode:{}", parentId, TokenUtils.getCurrentUserCode());
            return Response.build(ErrorCodeEnum.BAD_PARAM);
        }
        String parents = parentRecord.getParents();
        if (StringUtils.isEmpty(parents)) {
            parents = parentRecord.getId() + "/";
        } else {
            parents = parents + parentId;
        }
        categoryRecord.setParents(parents);
        categoryRecord.setUserCode(TokenUtils.getCurrentUserCode());
        categoryRecord.setCreated(new Date());
        categoryMapper.insertSelective(categoryRecord);
        return Response.build(ErrorCodeEnum.SUCCESS, categoryRecord.getId());
    }

    @Override
    public Response delete(Integer id) throws Exception {
        BookmarkRecordExample bookmarkRecordExample = new BookmarkRecordExample();
        BookmarkRecordExample.Criteria bookmarkCriteria = bookmarkRecordExample.createCriteria();
        bookmarkCriteria.andCategoryIdEqualTo(id);
        List<BookmarkRecord> bookmarkRecords = bookmarkMapper.selectByExample(bookmarkRecordExample);
        if (bookmarkRecords.size() > 0) {
            return new Response(ErrorCodeEnum.BUSINESS_ERR.getCode(), "有书签的分类不能删除!");
        }
        CategoryRecordExample categoryRecordExample = new CategoryRecordExample();
        CategoryRecordExample.Criteria categoryCriteria = categoryRecordExample.createCriteria();
        categoryCriteria.andParentsLike("%" + id + "%");
        List<CategoryRecord> parents = categoryMapper.selectByExample(categoryRecordExample);
        if (parents.size() > 0) {
            return new Response(ErrorCodeEnum.BUSINESS_ERR.getCode(), "该分类下有子分类,无法删除!");
        }
        categoryMapper.deleteByPrimaryKey(id);
        return Response.build(ErrorCodeEnum.SUCCESS);
    }

    @Override
    public Response query() throws Exception {
        CategoryRecordExample example = new CategoryRecordExample();
        CategoryRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUserCodeEqualTo(TokenUtils.getCurrentUserCode());
        List<CategoryRecord> categoryRecords = categoryMapper.selectByExample(example);
        List<CategoryVO> data = new ArrayList<>();
        for (CategoryRecord record : categoryRecords) {
            CategoryVO category = new CategoryVO();
            category.setId(record.getId());
            category.setTitle(record.getTitle());
            category.setDescription(record.getDescription());
            category.setParentId(record.getParentId());
            category.setCreated(record.getCreated());
            data.add(category);
        }
        CategoryVO root = null;
        for (CategoryVO categoryVO : data) {
            for (CategoryVO item : data) {
                if (item.getId().equals(categoryVO.getParentId())) {
                    List<CategoryVO> children = item.getChildren();
                    if (null == children) {
                        children = new ArrayList<>();
                        item.setChildren(children);
                    }
                    children.add(categoryVO);
                    break;
                }
            }
            if (null == categoryVO.getParentId()) {
                root = categoryVO;
            }
        }

        return Response.build(ErrorCodeEnum.SUCCESS, root);
    }
}
