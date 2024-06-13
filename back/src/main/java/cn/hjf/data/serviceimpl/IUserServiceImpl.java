package cn.hjf.data.serviceimpl;

import cn.hjf.data.dao.mapper.UserMapper;
import cn.hjf.data.entity.User;
import cn.hjf.data.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 何健锋
 * CSDN: Designer 何健锋
 */
@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
