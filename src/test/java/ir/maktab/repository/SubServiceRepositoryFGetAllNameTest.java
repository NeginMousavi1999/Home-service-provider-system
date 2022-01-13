package ir.maktab.repository;

import ir.maktab.config.SpringConfig;
import ir.maktab.model.services.SubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Negin Mousavi
 */
public class SubServiceRepositoryFGetAllNameTest {
    SubServiceRepository subServiceRepository;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        subServiceRepository = context.getBean(SubServiceRepository.class);
    }

    @Test
    void callGetAllName_WhenTableIsNotEmpty_ThenReturnTrueResponse() {
        List<String> allName = subServiceRepository.findAll().stream().map(SubService::getName).collect(Collectors.toList());
        assertTrue(allName.size() > 0);
    }
}
