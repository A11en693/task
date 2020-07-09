package com.yan.task.zone.aop;


import com.yan.utils.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class GlobalExceptionHandler {

    private final static Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //execution里面的参数表示获取serviceimpl包及其子包下面所有的方法
    @Pointcut("execution(* com.yan.task.serviceimpl..*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object handlerException(ProceedingJoinPoint proceedingJoinPoint) {
        LOG.info("AOP动态获取代理对象:{}", proceedingJoinPoint);
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            LOG.error("task项目系统报错信息:{}",e);
        }
        Result<Boolean> result = new Result<>();
        result.setMsg("系统出了小差，请稍后再试");
        result.setCode("500");
        result.setSuccess(false);
        result.setData(null);
        return result;
    }
}
