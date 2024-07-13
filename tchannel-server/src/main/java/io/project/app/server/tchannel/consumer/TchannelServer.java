package io.project.app.server.tchannel.consumer;

import com.uber.tchannel.api.ResponseCode;
import com.uber.tchannel.api.TChannel;
import com.uber.tchannel.api.handlers.RawRequestHandler;
import com.uber.tchannel.messages.RawRequest;
import com.uber.tchannel.messages.RawResponse;
import java.net.InetAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TchannelServer {
    
    @Autowired
    private ApplicationEventPublisher accountEventPublisher;
    
    public void init() {
        try {
            TChannel server = createServer();
            
        } catch (Exception ex) {
            log.error("Error " + ex.getLocalizedMessage());
        }
    }
    
    private TChannel createServer() throws Exception {
        // create TChannel
        TChannel tchannel = new TChannel.Builder("tchannel-server")
                .setServerHost(InetAddress.getByName("tchannel-server"))
                .setServerPort(8888)
                .build();
        
        log.info("Server started");
        tchannel.makeSubChannel("tchannel-server")
                .register("dataserver", new RawRequestHandler() {
                    @Override
                    public RawResponse handleImpl(RawRequest request) {
                        if (request.getBody() != null) {
                            log.info("REQUEST " + request.getBody());
                            accountEventPublisher.publishEvent(request.getBody());
                        }
                        return new RawResponse.Builder(request)
                                .setTransportHeaders(request.getTransportHeaders())
                                .setHeader("Response")
                                .setResponseCode(ResponseCode.OK)
                                .setBody("RECEIVED " + request.getBody())
                                .build();
                    }
                });
        tchannel.listen();
        return tchannel;
    }
    
}
