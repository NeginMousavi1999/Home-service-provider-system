package ir.maktab.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Negin Mousavi
 */
@ComponentScan(basePackages = "ir.maktab")
@Configuration
@Import(DatabaseConfig.class)
public class SpringConfig {
}
