package cn.hjf.data.serviceimpl;

import cn.hjf.data.dao.mapper.LogMapper;
import cn.hjf.data.entity.Log;
import cn.hjf.data.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 何健锋
 * CSDN: Designer 何健锋
 */
@Service
public class ILogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
