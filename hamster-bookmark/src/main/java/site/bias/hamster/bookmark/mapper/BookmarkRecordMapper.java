package site.bias.hamster.bookmark.mapper;

import org.apache.ibatis.annotations.Param;
import site.bias.hamster.bookmark.pojo.BookmarkRecord;
import site.bias.hamster.bookmark.pojo.BookmarkRecordExample;

import java.util.List;

public interface BookmarkRecordMapper {
    long countByExample(BookmarkRecordExample example);

    int deleteByExample(BookmarkRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookmarkRecord record);

    int insertSelective(BookmarkRecord record);

    List<BookmarkRecord> selectByExample(BookmarkRecordExample example);

    BookmarkRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookmarkRecord record, @Param("example") BookmarkRecordExample example);

    int updateByExample(@Param("record") BookmarkRecord record, @Param("example") BookmarkRecordExample example);

    int updateByPrimaryKeySelective(BookmarkRecord record);

    int updateByPrimaryKey(BookmarkRecord record);

    List<Integer> selectIds(@Param("userCode") String userCode);
}