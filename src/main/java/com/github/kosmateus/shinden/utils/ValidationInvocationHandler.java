package com.github.kosmateus.shinden.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ValidationInvocationHandler implements InvocationHandler {

    private final Object target;
    private final MethodValidator methodValidator;

    public ValidationInvocationHandler(Object target) {
        this.target = target;
        this.methodValidator = new MethodValidator();
    }

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(T target, Class<T> interfaceType) {
        return (T) Proxy.newProxyInstance(
                interfaceType.getClassLoader(),
                new Class<?>[]{interfaceType},
                new ValidationInvocationHandler(target)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        methodValidator.validateMethodParameters(args);
        return method.invoke(target, args);
    }
}
