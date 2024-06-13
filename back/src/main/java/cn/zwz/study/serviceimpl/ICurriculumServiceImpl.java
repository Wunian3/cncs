package cn.zwz.study.serviceimpl;

import cn.zwz.study.mapper.CurriculumMapper;
import cn.zwz.study.entity.Curriculum;
import cn.zwz.study.service.ICurriculumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 课程 服务层接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class ICurriculumServiceImpl extends ServiceImpl<CurriculumMapper, Curriculum> implements ICurriculumService {

    @Autowired
    private CurriculumMapper curriculumMapper;
}