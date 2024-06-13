package cn.zwz.study.serviceimpl;

import cn.zwz.study.mapper.AppraiseMapper;
import cn.zwz.study.entity.Appraise;
import cn.zwz.study.service.IAppraiseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 课程评价 服务层接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class IAppraiseServiceImpl extends ServiceImpl<AppraiseMapper, Appraise> implements IAppraiseService {

    @Autowired
    private AppraiseMapper appraiseMapper;
}