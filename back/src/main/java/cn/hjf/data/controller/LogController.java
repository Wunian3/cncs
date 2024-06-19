package cn.hjf.data.controller;

import cn.hjf.basics.log.LogType;
import cn.hjf.basics.log.SystemLog;
import cn.hjf.basics.utils.PageUtil;
import cn.hjf.basics.utils.ResultUtil;
import cn.hjf.basics.baseVo.PageVo;
import cn.hjf.basics.baseVo.Result;
import cn.hjf.data.entity.Log;
import cn.hjf.data.service.ILogService;
import cn.hjf.data.utils.HjfNullUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author 何健锋
 * CSDN: Designer 何健锋
 */
@RestController
@Api(tags = "日志管理接口")
@RequestMapping("/zwz/log")
@Transactional
public class LogController{

    @Autowired
    private ILogService iLogService;

    @SystemLog(about = "查询日志", type = LogType.DATA_CENTER,doType = "LOG-01")
    @RequestMapping(value = "/getAllByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询日志")
    public Result<Object> getAllByPage(@ModelAttribute Log log, @ModelAttribute PageVo page){
        QueryWrapper<Log> qw = new QueryWrapper<>();
        if(!HjfNullUtils.isNull(log.getName())) {
            qw.like("name",log.getName());
        }
        if(log.getLogType() != null) {
            qw.eq("log_type",log.getLogType());
        }
        if(!HjfNullUtils.isNull(log.getUsername())) {
            qw.like("username",log.getUsername());
        }
        if(!HjfNullUtils.isNull(log.getIp())) {
            qw.like("ip",log.getIp());
        }
        if(!HjfNullUtils.isNull(log.getStartDate())) {
            qw.ge("create_time",log.getStartDate());
            qw.le("create_time",log.getEndDate());
        }
        return ResultUtil.data(iLogService.page(PageUtil.initMpPage(page),qw));
    }
}
