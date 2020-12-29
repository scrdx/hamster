package site.bias.hamster.mapper;

import org.apache.ibatis.annotations.Param;
import site.bias.hamster.pojo.FapRecord;
import site.bias.hamster.pojo.FapRecordExample;

import java.util.List;

public interface FapRecordMapper {
    long countByExample(FapRecordExample example);

    int deleteByExample(FapRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FapRecord record);

    int insertSelective(FapRecord record);

    List<FapRecord> selectByExample(FapRecordExample example);

    FapRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FapRecord record, @Param("example") FapRecordExample example);

    int updateByExample(@Param("record") FapRecord record, @Param("example") FapRecordExample example);

    int updateByPrimaryKeySelective(FapRecord record);

    int updateByPrimaryKey(FapRecord record);

    List<String> selectAddress();
}