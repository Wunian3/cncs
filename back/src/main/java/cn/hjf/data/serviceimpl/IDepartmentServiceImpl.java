package cn.hjf.data.serviceimpl;

import cn.hjf.data.dao.mapper.DepartmentMapper;
import cn.hjf.data.entity.Department;
import cn.hjf.data.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 何健锋
 * CSDN: Designer 何健锋
 */
@Service
public class IDepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
