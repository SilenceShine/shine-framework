package io.github.SilenceShine.shine.spring.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @author SilenceShine
 * @github <a href="https://github.com/SilenceShine">SilenceShine</a>
 * @since 1.0
 */
public class AspectUtil {

    public static <A extends Annotation> Optional<A> findAnnotation(ProceedingJoinPoint joinPoint,
                                                                    @Nullable Class<A> annotationType) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return Optional.ofNullable(AnnotationUtils.findAnnotation(method, annotationType));
    }

    public static Method getSpecificmethod(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(pjp.getTarget());
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
        return specificMethod;
    }

}
