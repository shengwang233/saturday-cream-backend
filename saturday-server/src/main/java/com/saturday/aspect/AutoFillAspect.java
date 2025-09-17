package com.saturday.aspect;

import com.saturday.annotaion.AutoFill;
import com.saturday.context.BaseContext;
import com.saturday.enumeration.OperationType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * AOP Aspect for automatically filling common fields
 */
@Aspect
@Component
public class AutoFillAspect {

    /**
     * Pointcut definition
     * Intercept methods in mapper package annotated with @AutoFill
     */
    @Pointcut("execution(* com.saturday.mapper.*.*(..)) && @annotation(com.saturday.annotaion.AutoFill)")
    public void autoFillPointCut() {}

    /**
     * Before advice to fill common fields automatically
     * @param joinPoint the intercepted join point
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        // 1. Get operation type from @AutoFill annotation(REFLECTION)
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType = autoFill.value();

        // 2. Get method parameters (entity object)
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }
        Object entity = args[0];

        // 3. Prepare values for filling
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        // 4. Fill fields based on operation type
        if (operationType == OperationType.INSERT) {
            // Fill 4 fields: createTime, createUser, updateTime, updateUser
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod("setCreateTime", LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod("setCreateUser", Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod("setUpdateUser", Long.class);

                setCreateTime.invoke(entity, now);
                setCreateUser.invoke(entity, currentId);
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else if (operationType == OperationType.UPDATE) {
            // Fill 2 fields: updateTime, updateUser
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod("setUpdateUser", Long.class);

                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
