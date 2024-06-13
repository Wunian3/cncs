package cn.hjf.data.serviceimpl;

import cn.hjf.data.dao.mapper.UserRoleMapper;
import cn.hjf.data.entity.UserRole;
import cn.hjf.data.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 何健锋
 * CSDN: Designer 何健锋
 */
@Service
public class IUserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
