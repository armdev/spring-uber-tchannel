package io.project.app.server.tchannel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@ComponentScan(basePackages = {"io.project"})
@Slf4j
public class UberTchannelServerApplication {

    @Autowired
    private TchannelServer tchannelServer;

    public static void main(String[] args) {
        final SpringApplication application = new SpringApplication(UberTchannelServerApplication.class);
        application.setBannerMode(Banner.Mode.CONSOLE);
        application.setWebApplicationType(WebApplicationType.SERVLET);

        application.run(args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runUberServer() {
        log.info("Uber server up");
        tchannelServer.init();
    }
}
