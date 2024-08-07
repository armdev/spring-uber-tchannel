package io.project.app.client.tchannel.transport;

import com.github.f4b6a3.uuid.UuidCreator;
import com.uber.tchannel.api.SubChannel;
import com.uber.tchannel.api.TChannel;
import com.uber.tchannel.api.TFuture;
import com.uber.tchannel.api.handlers.TFutureCallback;
import com.uber.tchannel.messages.RawRequest;
import com.uber.tchannel.messages.RawResponse;
import java.net.InetAddress;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TchanelClient {

    public void sendData(String model) {
        try {
            TChannel client = createClient();

            SubChannel subChannel = client.makeSubChannel("tchannel-server");

            final long start = System.currentTimeMillis();
            final CountDownLatch done = new CountDownLatch(1);
            TFutureCallback<RawResponse> callback = (RawResponse response) -> {

                if (!response.isError()) {
                    log.info(String.format("Response received: response code: %s, header: %s, body: %s",
                            response.getResponseCode(),
                            response.getHeader(),
                            response.getBody()));
                } else {
                    log.info(String.format("Got error response: %s",
                            response.toString()));
                }
                done.countDown();
            };

            UUID transactionId = UuidCreator.getTimeOrderedEpoch();

            RawRequest request = new RawRequest.Builder("tchannel-server", "dataserver")
                    .setHeader("id:" + transactionId.toString())
                    .setBody(model)
                    .build();

            TFuture<RawResponse> future = subChannel.send(request,
                    InetAddress.getByName("tchannel-server"),
                    8888
            );
            future.addCallback(callback);
            done.await();
            log.info(String.format("\nTime cost: %dms", System.currentTimeMillis() - start));
            client.shutdown(false);
        } catch (Exception ex) {
            log.error("ERROR: " + ex.getLocalizedMessage());

        }

    }

    protected static TChannel createClient() throws Exception {

        // create TChannel
        TChannel tchannel = new TChannel.Builder("tchannel-client")
                .build();

        // create sub channel to talk to server
        tchannel.makeSubChannel("tchannel-server");
        return tchannel;
    }

}
