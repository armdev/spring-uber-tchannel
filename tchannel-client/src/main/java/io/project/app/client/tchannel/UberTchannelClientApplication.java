package io.project.app.client.tchannel;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan(basePackages = {"io.project"})
@EnableAsync
public class UberTchannelClientApplication {

    public static void main(String[] args) {
        final SpringApplication application = new SpringApplication(UberTchannelClientApplication.class);
        application.setBannerMode(Banner.Mode.CONSOLE);
        application.setWebApplicationType(WebApplicationType.SERVLET);

        application.run(args);
    }
}
