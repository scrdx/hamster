package site.bias.hamster.bookmark.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.bias.hamster.bookmark.bean.param.BookmarkParam;
import site.bias.hamster.bookmark.bean.Response;
import site.bias.hamster.bookmark.service.BookmarkService;

import javax.annotation.Resource;

/**
 * @author chenbinbin
 * @date 2020/2/27 0:20
 */
@RestController
@Slf4j
@RequestMapping("hamster/bookmark")
public class BookmarkController {

    @Resource
    private BookmarkService bookmarkService;

    @PostMapping("add")
    public Response add(@RequestBody BookmarkParam bookmark) throws Exception{
        return bookmarkService.add(bookmark);
    }

    @DeleteMapping("delete")
    public Response delete(@RequestParam Integer id) throws Exception{
        return bookmarkService.delete(id);
    }

    @PostMapping("update")
    public Response update(@RequestBody BookmarkParam bookmarkParam) throws Exception{
        return bookmarkService.update(bookmarkParam);
    }

    @GetMapping("query")
    public Response query(@RequestParam(value = "key", required = false) String key,
                          @RequestParam(value = "categoryId", required = false) Integer categoryId,
                          @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                          @RequestParam(value = "pageSize", required = false, defaultValue="50") Integer pageSize) throws Exception{

        return bookmarkService.query(key, categoryId, page, pageSize);
    }

    @GetMapping("queryById")
    public Response queryById(@RequestParam(value = "id") Integer id) throws Exception{
        return bookmarkService.queryById(id);
    }

    @GetMapping("queryByTagId")
    public Response queryByTagId(@RequestParam(value = "tagId") Integer tagId) throws Exception{
        return bookmarkService.queryByTagId(tagId);
    }

}
