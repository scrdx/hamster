package site.bias.hamster.service;


import site.bias.hamster.bean.Response;
import site.bias.hamster.pojo.FapRecord;

import java.util.Date;

/**
 * @author chenbinbin
 * @date 2020/8/18 1:57
 */
public interface FapService {

    Response query(Date begin, Date end, Integer type, Integer page, Integer pageSize);

    Response queryById(Integer id);

    Response add(FapRecord record);

    Response delete(Integer id);

    Response update(FapRecord record);

    Response getAddress();
}
