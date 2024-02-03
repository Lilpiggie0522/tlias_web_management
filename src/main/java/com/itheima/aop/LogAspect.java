package com.itheima.aop;

import com.alibaba.fastjson.JSONObject;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * ClassName: LogAspect
 * Package: com.itheima.aop
 * Description:
 *
 * @Author Piggie
 * @Create 31/01/2024 3:30 pm
 * @Version 1.0
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Pointcut("@annotation(com.itheima.anno.Log)")
    public void pt() { }
    @Around("pt()")

    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String token = httpServletRequest.getHeader("token");
        Claims claims = JwtUtils.parseJWT(token);
        //  obtain operatorId
        Integer operateId = (Integer) claims.get("id");

        //  obtain operation time
        LocalDateTime operateTime = LocalDateTime.now();

        //  obtain operation className
        String operateClassName = proceedingJoinPoint.getClass().getName();

        //  obtain method name
        String operateMethod = proceedingJoinPoint.getSignature().getName();

        //  obtain method arguments
        Object[] args = proceedingJoinPoint.getArgs();

        long begin = System.currentTimeMillis();
        //  obtain return value and convert it to an object
        Object object = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();

        Long costTime = end - begin;

        String returnValue = JSONObject.toJSONString(object);

        OperateLog operateLog = new OperateLog();
        operateLog.setOperateUser(operateId);
        operateLog.setOperateTime(operateTime);
        operateLog.setClassName(operateClassName);
        operateLog.setMethodName(operateMethod);
        operateLog.setMethodParams(Arrays.toString(args));
        operateLog.setCostTime(costTime);
        operateLog.setReturnValue(returnValue);
        operateLogMapper.insert(operateLog);
        return object;
    }
}
