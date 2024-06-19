package cn.hjf.basics.security.jwt;

import cn.hjf.basics.baseVo.TokenUser;
import cn.hjf.basics.parameter.HjfLoginProperties;
import cn.hjf.basics.redis.RedisTemplateHelper;
import cn.hjf.basics.utils.ResponseUtil;
import cn.hjf.basics.utils.SecurityUtil;
import cn.hjf.data.utils.HjfNullUtils;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 何健锋
 * CSDN: Designer 何健锋
 */
@ApiOperation(value = "自定义权限过滤")
@Slf4j
public class JwtTokenOncePerRequestFilter extends OncePerRequestFilter {

    private SecurityUtil securityUtil;

    @Autowired
    private RedisTemplateHelper redisTemplate;

    private HjfLoginProperties hjfLoginProperties;

    private static final boolean RESPONSE_FAIL_FLAG = false;

    private static final int RESPONSE_NO_ROLE_CODE = 401;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader(HjfLoginProperties.HTTP_HEADER);
        if(HjfNullUtils.isNull(tokenHeader)){
            tokenHeader = request.getParameter(HjfLoginProperties.HTTP_HEADER);
        }
        if (HjfNullUtils.isNull(tokenHeader)) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken token = getUsernamePasswordAuthenticationToken(tokenHeader, response);
            SecurityContextHolder.getContext().setAuthentication(token);
        }catch (Exception e){
            log.warn("自定义权限过滤失败" + e);
        }
        filterChain.doFilter(request, response);
    }

    @ApiOperation(value = "判断登录是否失效")
    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String header, HttpServletResponse response) {
        String userName = null;
        String tokenInRedis = redisTemplate.get(HjfLoginProperties.HTTP_TOKEN_PRE + header);
        if(HjfNullUtils.isNull(tokenInRedis)){
            ResponseUtil.out(response, ResponseUtil.resultMap(RESPONSE_FAIL_FLAG,RESPONSE_NO_ROLE_CODE,"登录状态失效，需要重登！"));
            return null;
        }

        TokenUser tokenUser = JSONObject.parseObject(tokenInRedis,TokenUser.class);
        userName = tokenUser.getUsername();
        List<GrantedAuthority> permissionList = new ArrayList<>();
        if(hjfLoginProperties.getSaveRoleFlag()){
            for(String permission : tokenUser.getPermissions()){
                permissionList.add(new SimpleGrantedAuthority(permission));
            }
        } else{
            permissionList = securityUtil.getCurrUserPerms(userName);
        }
        if(!tokenUser.getSaveLogin()){
            redisTemplate.set(HjfLoginProperties.USER_TOKEN_PRE + userName, header, hjfLoginProperties.getUserTokenInvalidDays(), TimeUnit.MINUTES);
            redisTemplate.set(HjfLoginProperties.HTTP_TOKEN_PRE + header, tokenInRedis, hjfLoginProperties.getUserTokenInvalidDays(), TimeUnit.MINUTES);
        }
        if(!HjfNullUtils.isNull(userName)) {
            User user = new User(userName, "", permissionList);
            return new UsernamePasswordAuthenticationToken(user, null, permissionList);
        }
        return null;
    }

    public JwtTokenOncePerRequestFilter(RedisTemplateHelper redis, SecurityUtil securityUtil, HjfLoginProperties hjfLoginProperties) {
        this.redisTemplate = redis;
        this.securityUtil = securityUtil;
        this.hjfLoginProperties = hjfLoginProperties;
    }
}
