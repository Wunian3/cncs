package cn.hjf.data.serviceimpl;

import cn.hjf.data.dao.mapper.PermissionMapper;
import cn.hjf.data.entity.Permission;
import cn.hjf.data.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 何健锋
 * CSDN: Designer 何健锋
 */
@Service
public class IPermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
