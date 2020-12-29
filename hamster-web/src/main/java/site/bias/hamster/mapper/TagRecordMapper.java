package site.bias.hamster.mapper;

import org.apache.ibatis.annotations.Param;
import site.bias.hamster.pojo.TagRecord;
import site.bias.hamster.pojo.TagRecordExample;

import java.util.List;

public interface TagRecordMapper {
    long countByExample(TagRecordExample example);

    int deleteByExample(TagRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TagRecord record);

    int insertSelective(TagRecord record);

    List<TagRecord> selectByExample(TagRecordExample example);

    TagRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TagRecord record, @Param("example") TagRecordExample example);

    int updateByExample(@Param("record") TagRecord record, @Param("example") TagRecordExample example);

    int updateByPrimaryKeySelective(TagRecord record);

    int updateByPrimaryKey(TagRecord record);
}