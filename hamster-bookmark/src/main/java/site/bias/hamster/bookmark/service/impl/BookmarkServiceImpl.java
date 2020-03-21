package site.bias.hamster.bookmark.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import site.bias.hamster.bookmark.bean.Response;
import site.bias.hamster.bookmark.bean.param.BookmarkParam;
import site.bias.hamster.bookmark.bean.vo.BookmarkVO;
import site.bias.hamster.bookmark.bean.vo.TagVO;
import site.bias.hamster.bookmark.config.HamsterConfig;
import site.bias.hamster.bookmark.constant.BookmarkStatus;
import site.bias.hamster.bookmark.constant.ErrorCodeEnum;
import site.bias.hamster.bookmark.constant.Constants;
import site.bias.hamster.bookmark.dao.TagDAO;
import site.bias.hamster.bookmark.mapper.BookmarkRecordMapper;
import site.bias.hamster.bookmark.mapper.TagRecordMapper;
import site.bias.hamster.bookmark.pojo.BookmarkRecord;
import site.bias.hamster.bookmark.pojo.BookmarkRecordExample;
import site.bias.hamster.bookmark.pojo.TagRecord;
import site.bias.hamster.bookmark.pojo.TagRecordExample;
import site.bias.hamster.bookmark.service.BookmarkService;
import site.bias.hamster.util.Base64Util;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.stream.Collectors;

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
    private TagDAO tagDAO;

    @Resource
    private HamsterConfig config;

    private String userCode = "test";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response add(BookmarkParam bookmark) throws Exception {
        BookmarkRecord bookmarkRecord = new BookmarkRecord();
        bookmarkRecord.setTitle(bookmark.getTitle());
        bookmarkRecord.setUrl(bookmark.getUrl());
        bookmarkRecord.setDescription(bookmark.getDescription());
        bookmarkRecord.setCategoryId(bookmark.getCategoryId());
        bookmarkRecord.setCreated(new Date());
        bookmarkRecord.setStatus(BookmarkStatus.NORMAL);
        bookmarkRecord.setUserCode(userCode);
        //标签处理
        String tags = bookmark.getTags();
        if (!StringUtils.isEmpty(tags)) {
            bookmarkRecord.setTags(tagDAO.getTagIds(bookmark.getTags(), userCode));
        }
        //书签图标处理
        String pic = bookmark.getPic();
        BookmarkParam.CropParam cropParam = bookmark.getCropParam();
        if (!StringUtils.isEmpty(pic)) {
            String fileName = UUID.randomUUID().toString().replaceAll("-", "");
            Thumbnails.of(new ByteArrayInputStream(Base64Util.toBytes(pic)))
                    .outputQuality(1.0d)
                    .sourceRegion(cropParam.getX(), cropParam.getY(), cropParam.getWidth(), cropParam.getHeight())
                    .size(cropParam.getWidth(), cropParam.getHeight())
                    .outputFormat(Constants.IMAGE_FORMAT)
                    .toFile(config.getPath() + fileName + "." + Constants.IMAGE_FORMAT);
            bookmarkRecord.setIconUrl(fileName + "." + Constants.IMAGE_FORMAT);
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
        bookmarkRecord.setCategoryId(bookmark.getCategoryId());
        bookmarkRecord.setModified(new Date());
        //标签处理
        String tags = bookmark.getTags();
        if (!StringUtils.isEmpty(tags)) {
            bookmarkRecord.setTags(tagDAO.getTagIds(bookmark.getTags(), userCode));
        } else {
            //如果为空字符串,则意味着删除原来的标签
            bookmarkRecord.setTags("");
        }
        //书签图标处理
        String pic = bookmark.getPic();
        BookmarkParam.CropParam cropParam = bookmark.getCropParam();
        if (!StringUtils.isEmpty(pic)) {
            String fileName = UUID.randomUUID().toString().replaceAll("-", "");
            Thumbnails.of(new ByteArrayInputStream(Base64Util.toBytes(pic)))
                    .outputQuality(1.0d)
                    .sourceRegion(cropParam.getX(), cropParam.getY(), cropParam.getWidth(), cropParam.getHeight())
                    .size(cropParam.getWidth(), cropParam.getHeight())
                    .outputFormat(Constants.IMAGE_FORMAT)
                    .toFile(config.getPath() + fileName);
            bookmarkRecord.setIconUrl(fileName + "." + Constants.IMAGE_FORMAT);
        }
        bookmarkMapper.updateByPrimaryKeySelective(bookmarkRecord);
        return Response.build(ErrorCodeEnum.SUCCESS);
    }

    @Override
    public Response query(String key, Integer categoryId, Integer page, Integer pageSize) throws Exception {
        BookmarkRecordExample bookmarkExample = new BookmarkRecordExample();
        BookmarkRecordExample.Criteria bookmarkCriteria = bookmarkExample.createCriteria();
        if (!StringUtils.isEmpty(key)) {
            bookmarkCriteria.andTitleLike("%" + key + "%");
        }
        if (null != categoryId) {
            bookmarkCriteria.andCategoryIdEqualTo(categoryId);
        }
        //标签条件
        if (!StringUtils.isEmpty(key)) {
            TagRecordExample tagExample = new TagRecordExample();
            TagRecordExample.Criteria tagCriteria = tagExample.createCriteria();
            tagCriteria.andNameLike("%" + key + "%");
            tagCriteria.andUserCodeEqualTo(userCode);
            List<TagRecord> tagRecords = tagMapper.selectByExample(tagExample);
            StringBuilder tagCondition = new StringBuilder("%");
            for (TagRecord tagRecord : tagRecords) {
                tagCondition.append(tagRecord.getId()).append("%");
            }
            if (tagRecords.size() > 0) {
                BookmarkRecordExample.Criteria orCriteria = bookmarkExample.or();
                orCriteria.andTagsLike(tagCondition.toString());
                orCriteria.andUserCodeEqualTo(userCode);
                if (null != categoryId) {
                    orCriteria.andCategoryIdEqualTo(categoryId);
                }
            }
        }
        bookmarkCriteria.andUserCodeEqualTo(userCode);

        Page<BookmarkRecord> pageObject = PageHelper.startPage(page, pageSize);
        List<BookmarkRecord> bookmarkRecords = bookmarkMapper.selectByExample(bookmarkExample);
        List<BookmarkVO> data = new ArrayList<>();
        for (BookmarkRecord bookmarkRecord : bookmarkRecords) {
            BookmarkVO bookmark = new BookmarkVO(bookmarkRecord);
            String tags = bookmarkRecord.getTags();
            if (!StringUtils.isEmpty(tags)) {
                bookmark.setTagInfoList(tagDAO.getTagInfos(tags, userCode));
            }
            data.add(bookmark);
        }
        return Response.build(ErrorCodeEnum.SUCCESS, data,
                pageObject.getPageNum(), pageObject.getPageSize(), pageObject.getPages(), pageObject.getTotal());
    }

    @Override
    public Response queryById(Integer id) throws Exception {
        BookmarkRecord bookmarkRecord = bookmarkMapper.selectByPrimaryKey(id);
        BookmarkVO bookmark = new BookmarkVO(bookmarkRecord);
        String tags = bookmarkRecord.getTags();
        if (!StringUtils.isEmpty(tags)) {
            bookmark.setTagInfoList(tagDAO.getTagInfos(tags, userCode));
        }
        return Response.build(ErrorCodeEnum.SUCCESS, bookmark);
    }

    @Override
    public Response queryByTagId(Integer tagId) throws Exception {
        BookmarkRecordExample bookmarkExample = new BookmarkRecordExample();
        BookmarkRecordExample.Criteria bookmarkCriteria = bookmarkExample.createCriteria();
        bookmarkCriteria.andUserCodeEqualTo(userCode);
        bookmarkCriteria.andTagsLike("%" + tagId + "%");
        List<BookmarkRecord> bookmarkRecords = bookmarkMapper.selectByExample(bookmarkExample);
        List<BookmarkVO> data = new ArrayList<>();
        for (BookmarkRecord bookmarkRecord : bookmarkRecords) {
            BookmarkVO bookmark = new BookmarkVO(bookmarkRecord);
            String tags = bookmarkRecord.getTags();
            if (!StringUtils.isEmpty(tags)) {
                bookmark.setTagInfoList(tagDAO.getTagInfos(tags, userCode));
            }
            data.add(bookmark);
        }
        return Response.build(ErrorCodeEnum.SUCCESS, data);
    }
}
