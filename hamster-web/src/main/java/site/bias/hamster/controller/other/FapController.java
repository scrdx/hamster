package site.bias.hamster.controller.other;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.bias.hamster.bean.Response;
import site.bias.hamster.pojo.FapRecord;
import site.bias.hamster.service.FapService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author chenbinbin
 * @date 2020/8/18 1:57
 */
@RestController
@Slf4j
@RequestMapping("hamster/other/fap")
public class FapController {

    @Resource
    private FapService fapService;

    @PostMapping("add")
    public Response add(@RequestBody FapRecord record) {
        return fapService.add(record);
    }

    @DeleteMapping("delete")
    public Response delete(@RequestParam("id") Integer id) {
        return fapService.delete(id);
    }

    @PostMapping("update")
    public Response update(@RequestBody FapRecord record) {
        return fapService.update(record);
    }

    @GetMapping("query")
    public Response query(@RequestParam(value = "begin", required = false) Long begin,
                          @RequestParam(value = "end", required = false) Long end,
                          @RequestParam(value = "type", required = false) Integer type,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        Date beginDate = null;
        Date endDate = null;
        if (null != begin) {
            beginDate = new Date(begin);
        }
        if (null != end) {
            endDate = new Date(end);
        }
        return fapService.query(beginDate, endDate, type, pageNum, pageSize);
    }

    @GetMapping("queryById")
    public Response queryById(@RequestParam("id") Integer id) {
        return fapService.queryById(id);
    }

    @GetMapping("getAddress")
    public Response getAddress() {
        return fapService.getAddress();
    }
}
