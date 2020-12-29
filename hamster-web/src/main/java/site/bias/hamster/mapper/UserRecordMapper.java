package site.bias.hamster.mapper;

import org.apache.ibatis.annotations.Param;
import site.bias.hamster.pojo.UserRecord;
import site.bias.hamster.pojo.UserRecordExample;

import java.util.List;

public interface UserRecordMapper {
    long countByExample(UserRecordExample example);

    int deleteByExample(UserRecordExample example);

    int deleteByPrimaryKey(String userCode);

    int insert(UserRecord record);

    int insertSelective(UserRecord record);

    List<UserRecord> selectByExample(UserRecordExample example);

    UserRecord selectByPrimaryKey(String userCode);

    int updateByExampleSelective(@Param("record") UserRecord record, @Param("example") UserRecordExample example);

    int updateByExample(@Param("record") UserRecord record, @Param("example") UserRecordExample example);

    int updateByPrimaryKeySelective(UserRecord record);

    int updateByPrimaryKey(UserRecord record);
}