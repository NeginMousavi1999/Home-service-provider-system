package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import service.ServiceService;
import view.ManagerView;
import view.UserView;

/**
 * @author Negin Mousavi
 */
@Configuration
@Import(ServicesSpringConfig.class)
public class ViewSpringConfig {

    @Bean
    public UserView userView() {
        return new UserView();
    }

    @Bean
    public ManagerView managerView(ServiceService serviceService) {
        ManagerView managerView = new ManagerView();
        managerView.setServiceService(serviceService);
        return managerView;
    }
}
