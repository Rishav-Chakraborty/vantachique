//package com.securityservice.loggers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class LoggerAspect {
//
//    public final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
//
//    @Pointcut("execution(* com.securityservice.controller.*.*(..) ) " +
//            "|| execution(* com.securityservice.dto.*.*(..) )" +
//            "|| execution(* com.securityservice.services.*.*(..) )" )
//    public void executionPointcut(){}
//
//    @Around("executionPointcut()")
//    public Object loggingAspect(ProceedingJoinPoint pjp) throws Throwable {
//        ObjectMapper objMapper = new ObjectMapper();
//        String methodName = pjp.getSignature().getName();
//        String className = pjp.getTarget().getClass().getName();
//        Object[] args = pjp.getArgs();
//        long startTime = System.currentTimeMillis();
//        Object obj = pjp.proceed();
//        long timeElapsed =System.currentTimeMillis() - startTime;
//        logger.debug("STACK_TRACE:\nCLASS_NAME: {};\nMETHOD_NAME: {} (ARGS: {});\nRETURN:{};\nTIME_ELAPSED: {}ms"
//                ,className,methodName,objMapper.writeValueAsString(args), objMapper.writeValueAsString(obj),timeElapsed);
//        return obj;
//    }
//}
