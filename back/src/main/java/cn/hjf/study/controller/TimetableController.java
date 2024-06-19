package cn.hjf.study.controller;

import cn.hjf.basics.utils.PageUtil;
import cn.hjf.basics.utils.ResultUtil;
import cn.hjf.basics.baseVo.PageVo;
import cn.hjf.basics.baseVo.Result;
import cn.hjf.basics.utils.SecurityUtil;
import cn.hjf.data.entity.User;
import cn.hjf.data.service.IUserService;
import cn.hjf.data.utils.HjfNullUtils;
import cn.hjf.study.entity.Curriculum;
import cn.hjf.study.entity.Timetable;
import cn.hjf.study.service.ICurriculumService;
import cn.hjf.study.service.ITimetableService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 何健锋
 * CSDN: Designer 何健锋
 */
@Slf4j
@RestController
@Api(tags = "课表管理接口")
@RequestMapping("/zwz/timetable")
@Transactional
public class TimetableController {

    @Autowired
    private ITimetableService iTimetableService;

    @Autowired
    private ICurriculumService iCurriculumService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条课表")
    public Result<Timetable> get(@RequestParam String id){
        return new ResultUtil<Timetable>().setData(iTimetableService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部课表个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iTimetableService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部课表")
    public Result<List<Timetable>> getAll(@RequestParam String curriculumId){
        QueryWrapper<Timetable> qw = new QueryWrapper<>();
        qw.eq("curriculum_id",curriculumId);
        return new ResultUtil<List<Timetable>>().setData(iTimetableService.list(qw));
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询课表")
    public Result<IPage<Timetable>> getByPage(@ModelAttribute Timetable timetable ,@ModelAttribute PageVo page){
        QueryWrapper<Timetable> qw = new QueryWrapper<>();
        if(!HjfNullUtils.isNull(timetable.getCurriculumName())) {
            qw.like("curriculum_name",timetable.getCurriculumName());
        }
        if(!HjfNullUtils.isNull(timetable.getUserName())) {
            qw.like("user_name",timetable.getUserName());
        }
        IPage<Timetable> data = iTimetableService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Timetable>>().setData(data);
    }

    @RequestMapping(value = "/getMyPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询课表")
    public Result<IPage<Timetable>> getMyPage(@ModelAttribute Timetable timetable ,@ModelAttribute PageVo page){
        QueryWrapper<Timetable> qw = new QueryWrapper<>();
        User currUser = securityUtil.getCurrUser();
        qw.eq("user_id",currUser.getId());
        if(!HjfNullUtils.isNull(timetable.getCurriculumName())) {
            qw.like("curriculum_name",timetable.getCurriculumName());
        }
        if(!HjfNullUtils.isNull(timetable.getUserName())) {
            qw.like("user_name",timetable.getUserName());
        }
        IPage<Timetable> data = iTimetableService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Timetable>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改课表")
    public Result<Timetable> saveOrUpdate(Timetable timetable){
        if(iTimetableService.saveOrUpdate(timetable)){
            return new ResultUtil<Timetable>().setData(timetable);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增课表")
    public Result<Timetable> insert(Timetable timetable){
        Curriculum c = iCurriculumService.getById(timetable.getCurriculumId());
        if(c == null) {
            return ResultUtil.error("课程不存在");
        }
        timetable.setCurriculumName(c.getTitle());
        User u = iUserService.getById(timetable.getUserId());
        if(u == null) {
            return ResultUtil.error("学生不存在");
        }
        timetable.setUserName(u.getNickname());
        User currUser = securityUtil.getCurrUser();
        timetable.setWorkUser(currUser.getNickname());
        iTimetableService.saveOrUpdate(timetable);
        return new ResultUtil<Timetable>().setData(timetable);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑课表")
    public Result<Timetable> update(Timetable timetable){
        Curriculum c = iCurriculumService.getById(timetable.getCurriculumId());
        if(c == null) {
            return ResultUtil.error("课程不存在");
        }
        timetable.setCurriculumName(c.getTitle());
        User u = iUserService.getById(timetable.getUserId());
        if(u == null) {
            return ResultUtil.error("学生不存在");
        }
        timetable.setUserName(u.getNickname());
        iTimetableService.saveOrUpdate(timetable);
        return new ResultUtil<Timetable>().setData(timetable);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除课表")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iTimetableService.removeById(id);
        }
        return ResultUtil.success();
    }
}
