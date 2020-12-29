package site.bias.hamster.controller.bookmark;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.bias.hamster.bean.Response;
import site.bias.hamster.service.CategoryService;

import javax.annotation.Resource;

/**
 * @author chenbinbin
 * @date 2020/2/27 0:20
 */
@RestController
@Slf4j
@RequestMapping("hamster/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("add")
    public Response add(@RequestParam String title, @RequestParam Integer parentId) throws Exception {
        return categoryService.add(title, parentId);
    }

    @DeleteMapping("delete")
    public Response delete(@RequestParam Integer id) throws Exception {
        return categoryService.delete(id);
    }

    @GetMapping("query")
    public Response query() throws Exception {
        return categoryService.query();
    }
}
