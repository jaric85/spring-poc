package rs.goran.spring.proxy.interfaces.impl;

import org.springframework.stereotype.Service;
import rs.goran.spring.proxy.interfaces.ServiceC;

@Service
public class ServiceCImpl implements ServiceC {

    public int max(int a, int b) {
        return Math.max(a, b);
    }

}
