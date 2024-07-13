package io.project.app.server.tchannel;

import io.project.app.server.tchannel.consumer.TchannelServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 *
 * @author armena
 */
@Configuration
@Slf4j
public class BootLoadConfiguration {

    @Autowired
    private TchannelServer tchannelServer;

    @EventListener(ApplicationReadyEvent.class)
    public void runUberServer() {
        log.info("UberTchannelClientApplication app ready for any event");
        log.info("Uber server up");
        tchannelServer.init();
    }
}
