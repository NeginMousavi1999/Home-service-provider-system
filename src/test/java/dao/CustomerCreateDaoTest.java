package dao;

import config.DaoSpringConfig;
import enumuration.UserRole;
import enumuration.UserStatus;
import model.members.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Negin Mousavi
 */
public class CustomerCreateDaoTest {
    CustomerDao customerDao;
    Customer customer;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoSpringConfig.class);
        customerDao = (CustomerDao) context.getBean("customerDao");
    }

    @Test
    void givenExpert_WhenCreateCalls_ThenReturnTrueResponse() {
        customer = Customer.builder()
                .firstName("jack")
                .lastName("jack")
                .userRole(UserRole.CUSTOMER)
                .userStatus(UserStatus.WAITING)
                .password("Jack1234")
                .email("jack@gmail.com")
                .credit(10000)
                .build();

        Long before = customerDao.getCountOfRecordsByEntityName("Customer");
        customerDao.create(customer);
        Long after = customerDao.getCountOfRecordsByEntityName("Customer");
        assertEquals(before, after - 1);
    }

}
