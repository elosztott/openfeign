package hu.me.iit.distributedsystems.springcloudconfigclient;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringCloudConfigClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigClientApplication.class, args);
    }

    @EventListener
    public void handleContextRefreshEvent(EnvironmentChangeEvent environmentChangeEvent) {
        System.out.println("environmentChangeEvent." + environmentChangeEvent.getKeys());
    }
}
