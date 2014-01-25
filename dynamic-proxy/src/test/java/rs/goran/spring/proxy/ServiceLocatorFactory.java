package rs.goran.spring.proxy;

import org.springframework.stereotype.Service;
import rs.goran.spring.proxy.interfaces.Services;
import rs.goran.spring.proxy.interfaces.excptions.NoServiceImplFound;

import java.util.Collection;
import java.util.Map;

public class ServiceLocatorFactory extends AbstractServiceLocatorFactory<Services> {

    @Override
    protected Object getImplementetion(Class<?> declaringClass) {
        Map<String, ?> beansOfType = getBeansOfType(declaringClass);
        Collection<?> serviceImpls = beansOfType.values();
        for (Object object : serviceImpls) {
            if (object.getClass().isAnnotationPresent(Service.class))
                return object;
        }
        throw new NoServiceImplFound(declaringClass);

    }

}
