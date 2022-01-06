package dao;

import config.DaoSpringConfig;
import model.members.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Negin Mousavi
 */
public class CustomerDaoFindByEmail {
    CustomerDao customerDao;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoSpringConfig.class);
        customerDao = (CustomerDao) context.getBean("customerDao");
    }

    @Test
    void givenExistEmail_WhenFindByEmailCalls_ThenReturnTrueResponse() {
        Customer customer = customerDao.findByEmail("jack@gmail.com");
        assertNotNull(customer);
    }

    @Test
    void givenNotExistEmail_WhenFindByEmailCalls_ThenReturnTrueResponse() {
        Customer customer = customerDao.findByEmail("something@gmail.com");
        assertNull(customer);
    }
}
