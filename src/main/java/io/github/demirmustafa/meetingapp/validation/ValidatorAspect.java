package io.github.demirmustafa.meetingapp.validation;

import io.github.demirmustafa.meetingapp.validation.annotation.Valid;
import io.github.demirmustafa.meetingapp.validation.annotation.Validator;
import io.github.demirmustafa.meetingapp.validation.contract.IValidator;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Component
@Aspect
@RequiredArgsConstructor
public class ValidatorAspect {

    private final AutowireCapableBeanFactory beanFactory;

    @Before("@annotation(validator)")
    public void validator(JoinPoint joinPoint, Validator validator) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Parameter[] params = method.getParameters();
        Object[] args = joinPoint.getArgs();

        IValidator v = null;

        if (!validator.validator().isInterface()) {
            v = beanFactory.createBean(validator.validator());
        }

        for (int i = 0; i < method.getParameterCount(); i++) {
            Parameter param = params[i];
            Object arg = args[i];

            if (param.isAnnotationPresent(Valid.class)) {

                if (v != null) {
                    v.validate(arg);
                    v.isValid();
                }
            }
        }
    }
}
