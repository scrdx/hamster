package site.bias.hamster.bookmark.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import site.bias.hamster.bookmark.bean.Response;
import site.bias.hamster.bookmark.bean.param.BookmarkParam;
import site.bias.hamster.bookmark.bean.param.CropParam;
import site.bias.hamster.bookmark.bean.vo.BookmarkVO;
import site.bias.hamster.bookmark.config.HamsterConfig;
import site.bias.hamster.bookmark.constant.BookmarkStatus;
import site.bias.hamster.bookmark.constant.ErrorCodeEnum;
import site.bias.hamster.bookmark.constant.Constants;
import site.bias.hamster.bookmark.dao.TagDAO;
import site.bias.hamster.bookmark.mapper.BookmarkRecordMapper;
import site.bias.hamster.bookmark.mapper.CategoryRecordMapper;
import site.bias.hamster.bookmark.mapper.TagRecordMapper;
import site.bias.hamster.bookmark.pojo.*;
import site.bias.hamster.bookmark.service.BookmarkService;
import site.bias.hamster.bookmark.util.AvatarUtil;
import site.bias.hamster.bookmark.util.TokenUtils;

import javax.annotation.Resource;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author chenbinbin
 * @date 2020/2/28 20:02
 */
@Service
public class BookmarkServiceImpl implements BookmarkService {

    @Resource
    private BookmarkRecordMapper bookmarkMapper;

    @Resource
    private TagRecordMapper tagMapper;

    @Resource
    private CategoryRecordMapper categoryMapper;

    @Resource
    private TagDAO tagDAO;

    @Resource
    private HamsterConfig config;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response add(BookmarkParam bookmark) throws Exception {
        BookmarkRecord bookmarkRecord = new BookmarkRecord();
        bookmarkRecord.setTitle(bookmark.getTitle());
        bookmarkRecord.setUrl(bookmark.getUrl());
        bookmarkRecord.setDescription(bookmark.getDescription());

        //获取分类路径
        bookmarkRecord.setCategoryId(bookmark.getCategoryId());
        CategoryRecord categoryRecord = categoryMapper.selectByPrimaryKey(bookmark.getCategoryId());
        String parents = categoryRecord.getParents();
        if (!StringUtils.isEmpty(parents)) {
            bookmarkRecord.setParents(parents + bookmark.getCategoryId() + Constants.CATEGORY_SPLIT_CHARACTER);
        } else {
            bookmarkRecord.setParents(bookmark.getCategoryId() + "/");
        }

        bookmarkRecord.setCreated(new Date());
        bookmarkRecord.setStatus(BookmarkStatus.NORMAL);
        bookmarkRecord.setUserCode(TokenUtils.getCurrentUserCode());
        //标签处理
        String tags = bookmark.getTags();
        if (!StringUtils.isEmpty(tags)) {
            bookmarkRecord.setTags(tagDAO.getTagIds(bookmark.getTags(), TokenUtils.getCurrentUserCode()));
        }
        //书签图标处理
        String pic = bookmark.getPic();
        CropParam cropParam = bookmark.getCropParam();
        if (!StringUtils.isEmpty(pic)) {
            bookmarkRecord.setIconUrl(AvatarUtil.crop(pic, config.getUploadPath(), cropParam));
        }

        bookmarkMapper.insertSelective(bookmarkRecord);
        return Response.build(ErrorCodeEnum.SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response delete(Integer id) throws Exception {
        bookmarkMapper.deleteByPrimaryKey(id);
        return Response.build(ErrorCodeEnum.SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response update(BookmarkParam bookmark) throws Exception {
        BookmarkRecord bookmarkRecord = new BookmarkRecord();
        bookmarkRecord.setId(bookmark.getId());
        bookmarkRecord.setTitle(bookmark.getTitle());
        bookmarkRecord.setUrl(bookmark.getUrl());
        bookmarkRecord.setDescription(bookmark.getDescription());

        //获取分类路径
        bookmarkRecord.setCategoryId(bookmark.getCategoryId());
        CategoryRecord categoryRecord = categoryMapper.selectByPrimaryKey(bookmark.getCategoryId());
        String parents = categoryRecord.getParents();
        if (!StringUtils.isEmpty(parents)) {
            bookmarkRecord.setParents(parents + bookmark.getCategoryId() + Constants.CATEGORY_SPLIT_CHARACTER);
        } else {
            bookmarkRecord.setParents(bookmark.getCategoryId() + "/");
        }

        bookmarkRecord.setModified(new Date());
        //标签处理
        String tags = bookmark.getTags();
        if (!StringUtils.isEmpty(tags)) {
            bookmarkRecord.setTags(tagDAO.getTagIds(bookmark.getTags(), TokenUtils.getCurrentUserCode()));
        } else {
            //如果为空字符串,则意味着删除原来的标签
            bookmarkRecord.setTags("");
        }
        //书签图标处理
        String pic = bookmark.getPic();
        CropParam cropParam = bookmark.getCropParam();
        String oldIconFile = bookmarkRecord.getIconUrl();
        if (!StringUtils.isEmpty(pic)) {
            bookmarkRecord.setIconUrl(AvatarUtil.crop(pic, config.getUploadPath(), cropParam));
            if (!StringUtils.isEmpty(oldIconFile)) {
                Files.deleteIfExists(Paths.get(config.getUploadPath() + oldIconFile));
            }
        }
        bookmarkMapper.updateByPrimaryKeySelective(bookmarkRecord);
        return Response.build(ErrorCodeEnum.SUCCESS);
    }

    @Override
    public Response query(String key, Integer categoryId, Integer pageNum, Integer pageSize) throws Exception {
        BookmarkRecordExample bookmarkExample = new BookmarkRecordExample();
        BookmarkRecordExample.Criteria bookmarkCriteria = bookmarkExample.createCriteria();
        if (!StringUtils.isEmpty(key)) {
            bookmarkCriteria.andTitleLike("%" + key + "%");
        }
        if (null != categoryId) {
            bookmarkCriteria.andParentsLike("%" + categoryId + "%");
        }
        bookmarkCriteria.andUserCodeEqualTo(TokenUtils.getCurrentUserCode());
        //标签条件
        if (!StringUtils.isEmpty(key)) {
            TagRecordExample tagExample = new TagRecordExample();
            TagRecordExample.Criteria tagCriteria = tagExample.createCriteria();
            tagCriteria.andNameLike("%" + key + "%");
            tagCriteria.andUserCodeEqualTo(TokenUtils.getCurrentUserCode());
            List<TagRecord> tagRecords = tagMapper.selectByExample(tagExample);
            for (TagRecord tagRecord : tagRecords) {
                BookmarkRecordExample.Criteria orCriteria = bookmarkExample.or();
                orCriteria.andTagsLike("%" + tagRecord.getId() + "%");
                orCriteria.andUserCodeEqualTo(TokenUtils.getCurrentUserCode());
                orCriteria.andUserCodeEqualTo(TokenUtils.getCurrentUserCode());
            }
        }

        Page<BookmarkRecord> pageObject = null;
        if (null != pageSize) {
            pageObject = PageHelper.startPage(pageNum, pageSize);
        }
        //获取书签名称数据
        CategoryRecordExample categoryExample = new CategoryRecordExample();
        CategoryRecordExample.Criteria categoryCriteria = categoryExample.createCriteria();
        categoryCriteria.andUserCodeEqualTo(TokenUtils.getCurrentUserCode());
        List<CategoryRecord> categoryRecords = categoryMapper.selectByExample(categoryExample);
        Map<Integer, String> categoryNameMap = new HashMap<>(categoryRecords.size());
        categoryRecords.forEach(c -> {
            categoryNameMap.put(c.getId(), c.getTitle());
        });
        List<BookmarkRecord> bookmarkRecords = bookmarkMapper.selectByExample(bookmarkExample);
        List<BookmarkVO> data = new ArrayList<>();
        for (BookmarkRecord bookmarkRecord : bookmarkRecords) {
            BookmarkVO bookmark = new BookmarkVO(bookmarkRecord);
            bookmark.setIconUrl(config.getImgPrefix() + bookmark.getIconUrl());
            bookmark.setCategoryName(categoryNameMap.get(bookmarkRecord.getCategoryId()));
            String tags = bookmarkRecord.getTags();
            if (!StringUtils.isEmpty(tags)) {
                bookmark.setTagInfoList(tagDAO.getTagInfos(tags, TokenUtils.getCurrentUserCode()));
            }
            data.add(bookmark);
        }

        if (null != pageSize) {
            return Response.build(ErrorCodeEnum.SUCCESS, data,
                    pageNum, pageObject.getPageSize(), pageObject.getPages(), pageObject.getTotal());
        }
        return Response.build(ErrorCodeEnum.SUCCESS, data);
    }

    @Override
    public Response queryById(Integer id) throws Exception {
        BookmarkRecord bookmarkRecord = bookmarkMapper.selectByPrimaryKey(id);
        BookmarkVO bookmark = new BookmarkVO(bookmarkRecord);
        String tags = bookmarkRecord.getTags();
        if (!StringUtils.isEmpty(tags)) {
            bookmark.setTagInfoList(tagDAO.getTagInfos(tags, TokenUtils.getCurrentUserCode()));
        }
        bookmark.setIconUrl(config.getImgPrefix() + bookmark.getIconUrl());
        return Response.build(ErrorCodeEnum.SUCCESS, bookmark);
    }

    @Override
    public Response queryByTagId(Integer tagId) throws Exception {
        BookmarkRecordExample bookmarkExample = new BookmarkRecordExample();
        BookmarkRecordExample.Criteria bookmarkCriteria = bookmarkExample.createCriteria();
        bookmarkCriteria.andUserCodeEqualTo(TokenUtils.getCurrentUserCode());
        bookmarkCriteria.andTagsLike("%" + tagId + "%");
        List<BookmarkRecord> bookmarkRecords = bookmarkMapper.selectByExample(bookmarkExample);
        List<BookmarkVO> data = new ArrayList<>();
        for (BookmarkRecord bookmarkRecord : bookmarkRecords) {
            BookmarkVO bookmark = new BookmarkVO(bookmarkRecord);
            String tags = bookmarkRecord.getTags();
            if (!StringUtils.isEmpty(tags)) {
                bookmark.setTagInfoList(tagDAO.getTagInfos(tags, TokenUtils.getCurrentUserCode()));
            }
            bookmark.setIconUrl(config.getImgPrefix() + bookmark.getIconUrl());
            data.add(bookmark);
        }
        return Response.build(ErrorCodeEnum.SUCCESS, data);
    }
}
