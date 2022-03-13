package site.bias.hamster.mapper;

import org.apache.ibatis.annotations.Param;
import site.bias.hamster.pojo.NoteCategoryRecord;
import site.bias.hamster.pojo.NoteCategoryRecordExample;

import java.util.List;

public interface NoteCategoryRecordMapper {
    long countByExample(NoteCategoryRecordExample example);

    int deleteByExample(NoteCategoryRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NoteCategoryRecord record);

    int insertSelective(NoteCategoryRecord record);

    List<NoteCategoryRecord> selectByExample(NoteCategoryRecordExample example);

    NoteCategoryRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NoteCategoryRecord record, @Param("example") NoteCategoryRecordExample example);

    int updateByExample(@Param("record") NoteCategoryRecord record, @Param("example") NoteCategoryRecordExample example);

    int updateByPrimaryKeySelective(NoteCategoryRecord record);

    int updateByPrimaryKey(NoteCategoryRecord record);
}