package rs.goran.spring.proxy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rs.goran.spring.proxy.interfaces.Services;

import javax.inject.Inject;


@ContextConfiguration(locations = {"classpath:spring-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ServicesTest {

    @Inject
    private Services services;

    public void setServices(Services services) {
        this.services = services;
    }

    @Test
    public void testServicesInjection() {
        Assert.assertNotNull(services);
    }

    @Test
    public void testServiceACallHelloWorld() {
        Assert.assertEquals(Constants.HELLO_WORLD, services.getHelloWorld());
    }

    @Test
    public void testServiceBCallMin() {
        int a = 5;
        int b = 10;
        int min = Math.min(a, b);
        Assert.assertEquals(min, services.min(a, b));
    }

    @Test
    public void testServiceCCallMax() {
        int a = 5;
        int b = 10;
        int max = Math.max(a, b);
        Assert.assertEquals(max, services.max(a, b));
    }
}
