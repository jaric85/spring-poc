package rs.goran.spring.proxy.interfaces.impl;

import org.springframework.stereotype.Service;
import rs.goran.spring.proxy.interfaces.ServiceB;

@Service
public class ServiceBImpl implements ServiceB {

    public int min(int a, int b) {
        return Math.min(a, b);
    }

}
