package ir.maktab.dao;

import ir.maktab.config.SpringConfig;
import ir.maktab.model.members.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Negin Mousavi
 */
public class CustomerDaoFindByEmailTest {
    CustomerDao customerDao;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        customerDao = context.getBean(CustomerDao.class);
    }

    @Test
    void givenExistEmail_WhenFindByEmailCalls_ThenReturnTrueResponse() {
        Customer customer = customerDao.findByEmail("jack@gmail.com").get();
        assertNotNull(customer);
    }

    @Test
    void givenNotExistEmail_WhenFindByEmailCalls_ThenReturnTrueResponse() {
        Customer customer = customerDao.findByEmail("something@gmail.com").get();
        assertNull(customer);
    }
}
