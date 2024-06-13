package cn.hjf.study.serviceimpl;

import cn.hjf.study.mapper.TimetableMapper;
import cn.hjf.study.entity.Timetable;
import cn.hjf.study.service.ITimetableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 课表 服务层接口实现
 * @author 何健锋
 */
@Slf4j
@Service
@Transactional
public class ITimetableServiceImpl extends ServiceImpl<TimetableMapper, Timetable> implements ITimetableService {

    @Autowired
    private TimetableMapper timetableMapper;
}