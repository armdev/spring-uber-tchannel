package io.project.app.server.tchannel;

import com.uber.tchannel.api.TChannel;
import com.uber.tchannel.api.handlers.RawRequestHandler;
import com.uber.tchannel.messages.RawRequest;
import com.uber.tchannel.messages.RawResponse;
import java.net.InetAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@Slf4j
public class TchannelServer {

    public void init() {
        try {
            TChannel server = createServer();
        } catch (Exception ex) {
            log.error("Error " + ex.getLocalizedMessage());
        }
    }

    private TChannel createServer() throws Exception {
        // create TChannel
        TChannel tchannel = new TChannel.Builder("server")
                .setServerHost(InetAddress.getByName("127.0.0.1"))
                .setServerPort(8888)
                .build();

        log.info("Server started");
        tchannel.makeSubChannel("server")
                .register("dataserver", new RawRequestHandler() {
                    @Override
                    public RawResponse handleImpl(RawRequest request) {
                        if (request.getBody() != null) {
                            log.info("Request Body " + request.getBody());
                        }
                        //  String get = request.getTransportHeaders().get("id");

                        return new RawResponse.Builder(request)
                                .setTransportHeaders(request.getTransportHeaders())
                                .setHeader("Response")
                                .setBody("Your id is " + request.getBody())
                                .build();
                    }
                });
        tchannel.listen();
        return tchannel;
    }

}
