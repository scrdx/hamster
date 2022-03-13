package site.bias.hamster.controller.note;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.bias.hamster.bean.Response;
import site.bias.hamster.pojo.NoteCategoryRecord;
import site.bias.hamster.pojo.NoteRecord;
import site.bias.hamster.service.NoteService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author chenbinbin
 * @date 2021-01-03 19:04
 */
@RestController
@Slf4j
@RequestMapping("hamster/note")
public class NoteController {

    @Resource
    private NoteService noteService;

    @PostMapping("add")
    public Response add(@RequestBody NoteRecord record) {
        return noteService.add(record);
    }

    @DeleteMapping("delete")
    public Response delete(@RequestParam("id") Integer id) {
        return noteService.delete(id);
    }

    @DeleteMapping("physicalDelete")
    public Response physicalDelete(@RequestParam("id") Integer id) {
        return noteService.physicalDelete(id);
    }

    @PostMapping("update")
    public Response update(@RequestBody NoteRecord record) {
        return noteService.update(record);
    }

    @GetMapping("query")
    public Response query(@RequestParam(name = "key", required = false) String key,
                          @RequestParam(name = "recursive", required = false, defaultValue = "false") Boolean recursive,
                          @RequestParam("type") Integer type,
                          @RequestParam("categoryId") Integer categoryId,
                          @RequestParam(name = "begin", required = false) Long begin,
                          @RequestParam(name = "end", required = false) Long end,
                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        Date beginDate = null;
        Date endDate = null;
        if (null != begin) {
            beginDate = new Date(begin);
        }
        if (null != end) {
            endDate = new Date(end);
        }

        return noteService.query(key, recursive, type, categoryId, beginDate, endDate, pageNum, pageSize);
    }

    @PutMapping("finish")
    public Response finish(@RequestParam("id") Integer id) {
        return noteService.finish(id);
    }

    @PostMapping("addCategory")
    Response addCategory(@RequestBody NoteCategoryRecord noteCategory) {
        return noteService.addCategory(noteCategory);
    }

    @DeleteMapping("deleteCategory")
    Response deleteCategory(@RequestParam("id") Integer id) {
        return noteService.deleteCategory(id);
    }

    @PostMapping("updateCategory")
    Response updateCategory(@RequestBody NoteCategoryRecord noteCategory) {
        return noteService.updateCategory(noteCategory);
    }
}
