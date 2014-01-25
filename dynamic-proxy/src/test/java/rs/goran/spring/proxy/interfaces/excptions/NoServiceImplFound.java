package rs.goran.spring.proxy.interfaces.excptions;

import java.text.MessageFormat;

@SuppressWarnings("serial")
public class NoServiceImplFound extends RuntimeException {

    public NoServiceImplFound(Class<?> clazz) {
        super(MessageFormat.format("No service implementer found for {0}", clazz));
    }
}