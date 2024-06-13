package cn.hjf.study.serviceimpl;

import cn.hjf.study.mapper.AssignmentMapper;
import cn.hjf.study.entity.Assignment;
import cn.hjf.study.service.IAssignmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 课程作业 服务层接口实现
 * @author 何健锋
 */
@Slf4j
@Service
@Transactional
public class IAssignmentServiceImpl extends ServiceImpl<AssignmentMapper, Assignment> implements IAssignmentService {

    @Autowired
    private AssignmentMapper assignmentMapper;
}