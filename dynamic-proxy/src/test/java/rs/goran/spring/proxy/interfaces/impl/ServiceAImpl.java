package rs.goran.spring.proxy.interfaces.impl;

import org.springframework.stereotype.Service;
import rs.goran.spring.proxy.Constants;
import rs.goran.spring.proxy.interfaces.ServiceA;

@Service
public class ServiceAImpl implements ServiceA {

    public String getHelloWorld() {
        return Constants.HELLO_WORLD;
    }

}
