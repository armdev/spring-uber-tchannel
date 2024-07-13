package io.project.app.server.tchannel.consumer;

import com.github.f4b6a3.uuid.UuidCreator;
import com.google.gson.Gson;
import io.project.app.server.tchannel.domain.Payments;
import io.project.app.server.tchannel.model.PaymentModel;
import io.project.app.server.tchannel.repositories.PaymentsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class EventConsumer {
    
    private final PaymentsRepository paymentsRepository;
    
    @EventListener
    public void handleEvent(String json) {
        Gson gson = new Gson();
        
        PaymentModel data = gson.fromJson(json, PaymentModel.class);
        
        Payments payments = new Payments();
        payments.setId(UuidCreator.getTimeOrderedEpoch().toString());
        payments.setTransactionId(data.getId());
        payments.setAmount(data.getAmount());
        payments.setClientId(data.getClientId());
        payments.setCreated(data.getCreated());
        payments.setCreditAccount(data.getCreditAccount());
        payments.setCreditAccountType(data.getCreditAccountType());
        payments.setDebitAccount(data.getDebitAccount());
        payments.setDebitAccountType(data.getDebitAccountType());
        payments.setStatus("PROCESSED");
        payments.setTransactionType(data.getTransactionType());
        
        paymentsRepository.save(payments);
        
        long count = paymentsRepository.count();
        log.info("Data size " + count);
    }
    
}
