package site.bias.hamster.bookmark.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Component;
import site.bias.hamster.bookmark.bean.vo.TagVO;
import site.bias.hamster.bookmark.constant.Constants;
import site.bias.hamster.bookmark.dao.TagDAO;
import site.bias.hamster.bookmark.mapper.TagRecordMapper;
import site.bias.hamster.bookmark.pojo.TagRecord;
import site.bias.hamster.bookmark.pojo.TagRecordExample;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chenbinbin
 * @date 2020/3/21 23:53
 */
@Component
public class TagDAOImpl implements TagDAO {
    @Resource
    private TagRecordMapper tagMapper;

    @Override
    public String getTagIds(String tagNames, String userCode) {
        StringBuilder tagIds = new StringBuilder();
        //查询出所有已经建立的tag的Id
        List<String> tagList = Arrays.asList(tagNames.split(Constants.SPLIT_CHARACTER));

        TagRecordExample tagExample = new TagRecordExample();
        TagRecordExample.Criteria tagCriteria = tagExample.createCriteria();
        tagCriteria.andNameIn(tagList);
        List<TagRecord> definedTags = tagMapper.selectByExample(tagExample);

        //已存在的tag获取其id拼接,不存在的tag则新建然后获取id拼接
        //去重，防止重复的tag
        Set<String> tagSet = new HashSet<>(tagList);
        boolean isDefined;
        for (String tagName : tagSet) {
            isDefined = false;
            for (TagRecord tagRecord : definedTags) {
                if (tagName.equals(tagRecord.getName())) {
                    tagIds.append(tagRecord.getId()).append(Constants.SPLIT_CHARACTER);
                    isDefined = true;
                    break;
                }
            }
            if (isDefined) {
                continue;
            }
            TagRecord tag = new TagRecord();
            tag.setName(tagName);
            tag.setUserCode(userCode);
            tag.setCreated(new Date());
            tagMapper.insertSelective(tag);
            tagIds.append(tag.getId()).append(Constants.SPLIT_CHARACTER);
        }
        return tagIds.substring(0, tagIds.length() - 1);
    }

    @Override
    public List<TagVO> getTagInfos(String tagIds, String userCode) {
        List<Integer> ids = Arrays.stream(tagIds.split(Constants.SPLIT_CHARACTER))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        TagRecordExample tagExample = new TagRecordExample();
        TagRecordExample.Criteria tagCriteria = tagExample.createCriteria();
        tagCriteria.andIdIn(ids);
        tagCriteria.andUserCodeEqualTo(userCode);
        List<TagRecord> tagRecords = tagMapper.selectByExample(tagExample);
        List<TagVO> tagInfoList = new ArrayList<>();
        for (TagRecord tagRecord : tagRecords) {
            TagVO tag = new TagVO();
            tag.setId(tagRecord.getId());
            tag.setName(tagRecord.getName());
            tagInfoList.add(tag);
        }
        return tagInfoList;
    }
}
