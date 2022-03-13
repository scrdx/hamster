package site.bias.hamster.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import site.bias.hamster.bean.Response;
import site.bias.hamster.constant.Constants;
import site.bias.hamster.constant.ErrorCodeEnum;
import site.bias.hamster.constant.NoteStatus;
import site.bias.hamster.mapper.NoteCategoryRecordMapper;
import site.bias.hamster.mapper.NoteRecordMapper;
import site.bias.hamster.pojo.NoteCategoryRecord;
import site.bias.hamster.pojo.NoteCategoryRecordExample;
import site.bias.hamster.pojo.NoteRecord;
import site.bias.hamster.pojo.NoteRecordExample;
import site.bias.hamster.service.NoteService;
import site.bias.hamster.util.TokenUtils;

import javax.annotation.Resource;
import javax.security.auth.login.Configuration;
import java.util.Date;
import java.util.List;

/**
 * @author chenbinbin
 * @date 2021-01-31 23:39
 */
@Service
@Slf4j
public class NoteServiceImpl implements NoteService {

    @Resource
    private NoteRecordMapper noteMapper;

    @Resource
    private NoteCategoryRecordMapper noteCategoryMapper;

    @Override
    public Response add(NoteRecord note) {
        note.setStatus(NoteStatus.PROCESSING);
        note.setUserCode(TokenUtils.getCurrentUserCode());
        NoteCategoryRecord noteCategoryRecord = noteCategoryMapper.selectByPrimaryKey(note.getCategoryId());
        String parents = noteCategoryRecord.getParents();
        if (!StringUtils.isEmpty(parents)) {
            note.setParents(parents + note.getCategoryId() + Constants.SPLIT_CHARACTER);
        } else {
            note.setParents(Constants.CATEGORY_SPLIT_CHARACTER + note.getCategoryId() + Constants.CATEGORY_SPLIT_CHARACTER);
        }
        note.setCreated(new Date());
        noteMapper.insertSelective(note);
        return Response.build(ErrorCodeEnum.SUCCESS);
    }

    @Override
    public Response delete(Integer id) {
        NoteRecord noteRecord = new NoteRecord();
        noteRecord.setId(id);
        noteRecord.setStatus(NoteStatus.DELETED);
        noteMapper.updateByPrimaryKeySelective(noteRecord);
        return Response.build(ErrorCodeEnum.SUCCESS);
    }

    @Override
    public Response physicalDelete(Integer id) {
        noteMapper.deleteByPrimaryKey(id);
        return Response.build(ErrorCodeEnum.SUCCESS);
    }

    @Override
    public Response update(NoteRecord note) {
        noteMapper.updateByPrimaryKeySelective(note);
        return Response.build(ErrorCodeEnum.SUCCESS);
    }

    @Override
    public Response query(String key, Boolean recursive, Integer type, Integer categoryId, Date begin, Date end, Integer pageNum, Integer pageSize) {
        NoteRecordExample example = new NoteRecordExample();
        NoteRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUserCodeEqualTo(TokenUtils.getCurrentUserCode());
        if (!StringUtils.isEmpty(key)) {
            criteria.andTitleLike("%" + key + "%");
        }
        if (null != categoryId) {
            if (recursive) {
                criteria.andParentsLike("%/" + categoryId + "/%");
            } else {
                criteria.andCategoryIdEqualTo(categoryId);
            }
        }

        if (null != begin) {
            criteria.andCreatedGreaterThanOrEqualTo(begin);
        }
        if (null != end) {
            criteria.andCreatedLessThan(end);
        }

        if (null != type) {
            criteria.andItemTypeEqualTo(type);
        }
        Page<NoteRecord> page = null;
        if (null != pageNum && null != pageSize) {
            page = PageHelper.startPage(pageNum, pageSize);
        }
        List<NoteRecord> data = noteMapper.selectByExample(example);
        Response response = Response.build(ErrorCodeEnum.SUCCESS, data);
        if (null != page) {
            response.setPageNum(pageNum);
            response.setPageSize(pageSize);
            response.setTotalCount(page.getTotal());
            response.setPages(page.getPages());
        }
        return response;
    }

    @Override
    public Response finish(Integer id) {
        NoteRecord noteRecord = new NoteRecord();
        noteRecord.setId(id);
        noteRecord.setStatus(NoteStatus.FINISHED);
        noteMapper.updateByPrimaryKeySelective(noteRecord);
        return Response.build(ErrorCodeEnum.SUCCESS);
    }

    @Override
    public Response addCategory(NoteCategoryRecord noteCategory) {
        noteCategory.setUserCode(TokenUtils.getCurrentUserCode());
        noteCategory.setCreated(new Date());

        Integer parentId = noteCategory.getParentId();
        if (parentId != null) {
            NoteCategoryRecord parentCategory = noteCategoryMapper.selectByPrimaryKey(parentId);
            String parents = parentCategory.getParents();
            if (!StringUtils.isEmpty(parents)) {
                noteCategory.setParents(parents + Constants.CATEGORY_SPLIT_CHARACTER + parentId + Constants.CATEGORY_SPLIT_CHARACTER);
            } else {
                noteCategory.setParents(Constants.CATEGORY_SPLIT_CHARACTER + parentId + Constants.CATEGORY_SPLIT_CHARACTER);
            }
        }
        noteCategoryMapper.insertSelective(noteCategory);

        return Response.build(ErrorCodeEnum.SUCCESS);
    }

    @Override
    public Response deleteCategory(Integer id) {
        NoteRecordExample example = new NoteRecordExample();
        NoteRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUserCodeEqualTo(TokenUtils.getCurrentUserCode());
        criteria.andCategoryIdEqualTo(id);
        long count = noteMapper.countByExample(example);
        if (count > 0) {
            return new Response(ErrorCodeEnum.BUSINESS_ERR.getCode(), "该分类下便签不为空,无法删除!");
        }

        NoteCategoryRecordExample categoryExample = new NoteCategoryRecordExample();
        NoteCategoryRecordExample.Criteria categoryCriteria = categoryExample.createCriteria();
        categoryCriteria.andUserCodeEqualTo(TokenUtils.getCurrentUserCode());
        categoryCriteria.andParentIdEqualTo(id);
        long categoryCount = noteCategoryMapper.countByExample(categoryExample);
        if (categoryCount > 0) {
            return new Response(ErrorCodeEnum.BUSINESS_ERR.getCode(), "该分类下有子分类,无法删除!");
        }

        noteCategoryMapper.deleteByPrimaryKey(id);
        return Response.build(ErrorCodeEnum.SUCCESS);
    }

    @Override
    public Response updateCategory(NoteCategoryRecord noteCategory) {
        noteCategory.setUserCode(TokenUtils.getCurrentUserCode());
        noteCategoryMapper.updateByPrimaryKeySelective(noteCategory);
        return Response.build(ErrorCodeEnum.SUCCESS);
    }
}
