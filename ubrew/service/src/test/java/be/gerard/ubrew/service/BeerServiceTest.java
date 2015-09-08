package be.gerard.ubrew.service;

import be.gerard.ubrew.interface_v1.model.product.Beer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bartgerard
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:be.gerard.ubrew.core.service.test.xml")
@ActiveProfiles("jpa")
public class BeerServiceTest {
    
    @Autowired
    private BeerServiceImpl beerServiceImpl;
    
    @Transactional
    @Test
    public void test() {
        beerServiceImpl.saveOrUpdate(new Beer());
    }

}
