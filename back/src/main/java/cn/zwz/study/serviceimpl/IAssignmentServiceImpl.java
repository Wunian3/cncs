package cn.zwz.study.serviceimpl;

import cn.zwz.study.mapper.AssignmentMapper;
import cn.zwz.study.entity.Assignment;
import cn.zwz.study.service.IAssignmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 课程作业 服务层接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class IAssignmentServiceImpl extends ServiceImpl<AssignmentMapper, Assignment> implements IAssignmentService {

    @Autowired
    private AssignmentMapper assignmentMapper;
}