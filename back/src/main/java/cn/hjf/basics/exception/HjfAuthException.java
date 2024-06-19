package cn.hjf.basics.exception;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

/**
 * @author 何健锋
 * CSDN: Designer 何健锋
 */
@ApiOperation(value = "自定义异常")
public class HjfAuthException extends InternalAuthenticationServiceException {

    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_MSG = "系统鉴权失败";

    @ApiModelProperty(value = "异常消息内容")
    private String msg;

    public HjfAuthException(String msg){
        super(msg);
        this.msg = msg;
    }

    public HjfAuthException(){
        super(DEFAULT_MSG);
        this.msg = DEFAULT_MSG;
    }

    public HjfAuthException(String msg, Throwable t) {
        super(msg, t);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
