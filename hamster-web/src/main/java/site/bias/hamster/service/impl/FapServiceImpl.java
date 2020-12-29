package site.bias.hamster.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.bias.hamster.bean.Response;
import site.bias.hamster.constant.ErrorCodeEnum;
import site.bias.hamster.mapper.FapRecordMapper;
import site.bias.hamster.pojo.FapRecord;
import site.bias.hamster.pojo.FapRecordExample;
import site.bias.hamster.service.FapService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author chenbinbin
 * @date 2020/8/20 10:42
 */
@Slf4j
@Service
public class FapServiceImpl implements FapService {

    @Resource
    private FapRecordMapper fapRecordMapper;

    @Override
    public Response query(Date begin, Date end, Integer type, Integer page, Integer pageSize) {
        FapRecordExample example = new FapRecordExample();
        FapRecordExample.Criteria criteria = example.createCriteria();
        if (null != begin) {
            criteria.andTimeGreaterThanOrEqualTo(begin);
        }
        if (null != end) {
            criteria.andTimeLessThan(end);
        }
        if (null != type) {
            criteria.andTypeEqualTo(type);
        }
        Page<FapRecord> pageObject = PageHelper.startPage(page, pageSize);
        List<FapRecord> data = fapRecordMapper.selectByExample(example);
        return Response.build(ErrorCodeEnum.SUCCESS, data, page, pageSize, pageObject.getPages(), pageObject.getTotal());
    }

    @Override
    public Response queryById(Integer id) {
        return Response.build(ErrorCodeEnum.SUCCESS, fapRecordMapper.selectByPrimaryKey(id));
    }

    @Override
    public Response add(FapRecord record) {
        return Response.build(ErrorCodeEnum.SUCCESS, fapRecordMapper.insertSelective(record));
    }

    @Override
    public Response delete(Integer id) {
        return Response.build(ErrorCodeEnum.SUCCESS, fapRecordMapper.deleteByPrimaryKey(id));
    }

    @Override
    public Response update(FapRecord record) {
        return Response.build(ErrorCodeEnum.SUCCESS, fapRecordMapper.updateByPrimaryKeySelective(record));
    }

    @Override
    public Response getAddress() {
        return Response.build(ErrorCodeEnum.SUCCESS, fapRecordMapper.selectAddress());
    }
}
