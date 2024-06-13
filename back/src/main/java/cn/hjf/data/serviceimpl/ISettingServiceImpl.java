package cn.hjf.data.serviceimpl;

import cn.hjf.data.dao.mapper.SettingMapper;
import cn.hjf.data.entity.Setting;
import cn.hjf.data.service.ISettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 何健锋
 * CSDN: Designer 何健锋
 */
@Service
public class ISettingServiceImpl extends ServiceImpl<SettingMapper, Setting> implements ISettingService {

}
