package cn.hjf.data.controller;

import cn.hjf.basics.log.LogType;
import cn.hjf.basics.log.SystemLog;
import cn.hjf.basics.utils.PageUtil;
import cn.hjf.basics.utils.ResultUtil;
import cn.hjf.basics.baseVo.PageVo;
import cn.hjf.basics.baseVo.Result;
import cn.hjf.data.entity.User;
import cn.hjf.data.service.IUserService;
import cn.hjf.data.utils.HjfNullUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author 何健锋
 * CSDN: Designer 何健锋
 */
@Slf4j
@RestController
@Api(tags = "新用户接口")
@RequestMapping("/zwz/myUser")
@Transactional
public class MyUserController {

    @Autowired
    private IUserService iUserService;

    @SystemLog(about = "查询用户", type = LogType.DATA_CENTER,doType = "USER-01")
    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户")
    public Result<IPage<User>> getByPage(@ModelAttribute User user,@ModelAttribute PageVo page){
        QueryWrapper<User> qw = new QueryWrapper<>();
        if(user.getDepartmentId() != null && !HjfNullUtils.isNull(user.getDepartmentId())) {
            qw.like("department_id",user.getDepartmentId());
        }
        if(user.getNickname() != null && !HjfNullUtils.isNull(user.getNickname())) {
            qw.like("nickname",user.getNickname());
        }
        IPage<User> data = iUserService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<User>>().setData(data);
    }
}
