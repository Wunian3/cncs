package cn.hjf.study.serviceimpl;

import cn.hjf.study.mapper.CourseResourcesMapper;
import cn.hjf.study.entity.CourseResources;
import cn.hjf.study.service.ICourseResourcesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 资源 服务层接口实现
 * @author 何健锋
 */
@Slf4j
@Service
@Transactional
public class ICourseResourcesServiceImpl extends ServiceImpl<CourseResourcesMapper, CourseResources> implements ICourseResourcesService {

    @Autowired
    private CourseResourcesMapper courseResourcesMapper;
}