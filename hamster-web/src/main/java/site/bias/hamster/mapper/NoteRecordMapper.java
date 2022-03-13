package site.bias.hamster.mapper;

import org.apache.ibatis.annotations.Param;
import site.bias.hamster.pojo.NoteRecord;
import site.bias.hamster.pojo.NoteRecordExample;

import java.util.List;

public interface NoteRecordMapper {
    long countByExample(NoteRecordExample example);

    int deleteByExample(NoteRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NoteRecord record);

    int insertSelective(NoteRecord record);

    List<NoteRecord> selectByExampleWithBLOBs(NoteRecordExample example);

    List<NoteRecord> selectByExample(NoteRecordExample example);

    NoteRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NoteRecord record, @Param("example") NoteRecordExample example);

    int updateByExampleWithBLOBs(@Param("record") NoteRecord record, @Param("example") NoteRecordExample example);

    int updateByExample(@Param("record") NoteRecord record, @Param("example") NoteRecordExample example);

    int updateByPrimaryKeySelective(NoteRecord record);

    int updateByPrimaryKeyWithBLOBs(NoteRecord record);

    int updateByPrimaryKey(NoteRecord record);
}