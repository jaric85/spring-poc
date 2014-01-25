package rs.goran.spring.proxy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rs.goran.spring.proxy.interfaces.Services;

import javax.inject.Inject;
import java.security.SecureRandom;


@ContextConfiguration(locations = {"classpath:spring-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ServicesTest {

    @Inject
    private Services services;

    private int a;
    private int b;

    public void setServices(Services services) {
        this.services = services;
    }

    @Before
    public void setUp() {
        SecureRandom generator = new SecureRandom();
        a = generator.nextInt();
        b = generator.nextInt();
    }

    @Test
    public void testServicesEqualsInvoke() {
        Object dummy = new Object();
        Assert.assertFalse(services.equals(dummy));
    }

    @Test
    public void testServicesToStringInvoke() {
        Assert.assertNotNull(services.toString());
    }

    @Test
    public void testServicesHashCodeInvoke() {
        Assert.assertNotNull(services.hashCode());
    }

    @Test
    public void testServicesInjected() {
        Assert.assertNotNull(services);
    }

    @Test
    public void testServiceACallHelloWorld() {
        Assert.assertEquals(Constants.HELLO_WORLD, services.getHelloWorld());
    }

    @Test
    public void testServiceBCallMin() {
        int given = Math.min(a, b);
        int expected = services.min(a, b);
        Assert.assertEquals(expected, given);
    }

    @Test
    public void testServiceCCallMax() {
        int given = Math.max(a, b);
        int expected = services.max(a, b);
        Assert.assertEquals(expected, given);
    }
}
