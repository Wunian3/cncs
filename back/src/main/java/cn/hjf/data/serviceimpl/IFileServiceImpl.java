package cn.hjf.data.serviceimpl;

import cn.hjf.data.dao.mapper.FileMapper;
import cn.hjf.data.entity.File;
import cn.hjf.data.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 何健锋
 * CSDN: Designer 何健锋
 */
@Service
public class IFileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

}
