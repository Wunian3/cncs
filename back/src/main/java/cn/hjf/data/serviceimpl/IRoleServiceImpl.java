package cn.hjf.data.serviceimpl;

import cn.hjf.data.dao.mapper.RoleMapper;
import cn.hjf.data.entity.Role;
import cn.hjf.data.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 何健锋
 * CSDN: Designer 何健锋
 */
@Service
public class IRoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
