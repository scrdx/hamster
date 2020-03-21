package site.bias.hamster.bookmark.mapper;

import org.apache.ibatis.annotations.Param;
import site.bias.hamster.bookmark.pojo.CategoryRecord;
import site.bias.hamster.bookmark.pojo.CategoryRecordExample;

import java.util.List;

public interface CategoryRecordMapper {
    long countByExample(CategoryRecordExample example);

    int deleteByExample(CategoryRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CategoryRecord record);

    int insertSelective(CategoryRecord record);

    List<CategoryRecord> selectByExample(CategoryRecordExample example);

    CategoryRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CategoryRecord record, @Param("example") CategoryRecordExample example);

    int updateByExample(@Param("record") CategoryRecord record, @Param("example") CategoryRecordExample example);

    int updateByPrimaryKeySelective(CategoryRecord record);

    int updateByPrimaryKey(CategoryRecord record);
}