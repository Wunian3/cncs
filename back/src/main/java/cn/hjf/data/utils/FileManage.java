package cn.hjf.data.utils;

import cn.hjf.data.vo.OssSettingVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author 何健锋
 * CSDN: Designer 何健锋
 */
@Api(tags = "文件配置接口类")
public interface FileManage {

    @ApiOperation(value = "删除文件")
    void deleteFile(String key);

    @ApiOperation(value = "重命名文件")
    String renameFile(String fromKey, String toKey);

    @ApiOperation(value = "获取配置")
    OssSettingVo getOssSetting();

    @ApiOperation(value = "拷贝文件")
    String copyFile(String fromKey, String toKey);

    @ApiOperation(value = "文件流上传")
    String inputStreamUpload(InputStream inputStream, String key, MultipartFile file);
}
