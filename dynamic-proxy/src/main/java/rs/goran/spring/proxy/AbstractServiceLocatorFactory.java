package rs.goran.spring.proxy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.*;
import java.util.Map;

public abstract class AbstractServiceLocatorFactory<S> implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private Class<S> serviceLocatorInterface;

    protected AbstractServiceLocatorFactory() {
        this.serviceLocatorInterface = (Class<S>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected Map<String, ?> getBeansOfType(Class<?> declaringClass) {
        return applicationContext.getBeansOfType(declaringClass);
    }

    public S createServiceDelegate() {
        return (S) Proxy.newProxyInstance(AbstractServiceLocatorFactory.class.getClassLoader(), new Class[]{serviceLocatorInterface}, new ServiceInovocationHandler());
    }

    private class ServiceInovocationHandler implements InvocationHandler {

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Class<?> declaringClass = method.getDeclaringClass();
            if (Object.class == declaringClass)
                return invokeOnObject(proxy, method, args);
            else
                return invokeOnService(method, args, declaringClass);
        }

        private Object invokeOnObject(Object proxy, Method method, Object[] args) {
            if (ReflectionUtils.isEqualsMethod(method)) {
                return (proxy == args[0]);
            } else if (ReflectionUtils.isHashCodeMethod(method)) {
                return System.identityHashCode(proxy);
            } else if (ReflectionUtils.isToStringMethod(method)) {
                return "Service locator: " + serviceLocatorInterface.getName();
            } else {
                throw new IllegalStateException(String.valueOf(method));
            }
        }

    }

    protected Object invokeOnService(Method method, Object[] args, Class<?> declaringClass) throws IllegalAccessException, InvocationTargetException {
        return method.invoke(getImplementetion(declaringClass), args);
    }

    protected abstract Object getImplementetion(Class<?> declaringClass);

}