package cn.hjf.data.utils;

import io.swagger.annotations.Api;

import java.util.Objects;

/**
 * @author 何健锋
 * CSDN: Designer 何健锋
 */
@Api(tags = "判断为空工具类")
public class HjfNullUtils {
    public static boolean isNull(String str){
        if(str == null || Objects.equals("",str) || Objects.equals("null",str) || Objects.equals("undefined",str)) {
            return true;
        }
        return false;
    }
}
