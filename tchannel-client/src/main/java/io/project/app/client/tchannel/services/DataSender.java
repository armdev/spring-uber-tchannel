package io.project.app.client.tchannel.services;

import com.google.gson.Gson;
import io.project.app.client.tchannel.domain.Payments;
import io.project.app.client.tchannel.transport.TchanelClient;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class DataSender {

    private final TchanelClient tchanelClient;

    public void justSend(Payments event) {

        Gson gson = new Gson();

        tchanelClient.sendData(gson.toJson(event));

    }

    public void sendMessageVirtual(Payments event) {
        long startTime = System.nanoTime(); // Start the timer

        Gson gson = new Gson();

        ThreadFactory factory = Thread.ofVirtual().factory();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(0, factory);

        Callable<String> scheduledCallable = () -> {

            tchanelClient.sendData(gson.toJson(event));

            long endTime = System.nanoTime(); // Stop the timer
            long executionTimeNano = endTime - startTime; // Calculate the execution time in nanoseconds
            double executionTimeSeconds = executionTimeNano / 1_000_000_000.0; // Convert nanoseconds to seconds

            log.info("Execution time: " + executionTimeSeconds + " seconds");

            return "Done";
        };

        scheduledExecutorService.schedule(scheduledCallable, 1, TimeUnit.SECONDS);

    }
}
