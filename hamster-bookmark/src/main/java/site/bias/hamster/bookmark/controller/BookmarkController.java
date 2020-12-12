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
    public Response add(@RequestBody BookmarkParam bookmark) throws Exception {
        return bookmarkService.add(bookmark);
    }

    @DeleteMapping("delete")
    public Response delete(@RequestParam Integer id) throws Exception {
        return bookmarkService.delete(id);
    }

    @PostMapping("update")
    public Response update(@RequestBody BookmarkParam bookmarkParam) throws Exception {
        return bookmarkService.update(bookmarkParam);
    }

    @GetMapping("query")
    public Response query(@RequestParam(value = "key", required = false) String key,
                          @RequestParam(value = "categoryId", required = false) Integer categoryId,
                          @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {

        return bookmarkService.query(key, categoryId, pageNum, pageSize);
    }

    @GetMapping("queryById")
    public Response queryById(@RequestParam(value = "id") Integer id) throws Exception {
        return bookmarkService.queryById(id);
    }

    @GetMapping("queryByTagId")
    public Response queryByTagId(@RequestParam(value = "tagId") Integer tagId) throws Exception {
        return bookmarkService.queryByTagId(tagId);
    }

    @GetMapping("getMetaInfoByUrl")
    public Response getMetaInfoByUrl(@RequestParam(value = "url") String url) throws Exception {
        return bookmarkService.getMetaInfoByUrl(url);
    }

    @GetMapping("getOften")
    public Response getOften(@RequestParam(value = "size") Integer size) throws Exception {
        return bookmarkService.getOften(size);
    }

    @GetMapping("getRandom")
    public Response getRandom(@RequestParam(value = "size") Integer size) throws Exception {
        return bookmarkService.getRandom(size);
    }

    @PutMapping("fix")
    public Response fix(@RequestParam(value = "id") Integer id) throws Exception {
        return bookmarkService.fix(id);
    }

    @PutMapping("unfix")
    public Response unfix(@RequestParam(value = "id") Integer id) throws Exception {
        return bookmarkService.unfix(id);
    }

    @PostMapping("increase")
    public Response increase(@RequestParam(value = "id") Integer id) throws Exception {
        return bookmarkService.increase(id);
    }

}
