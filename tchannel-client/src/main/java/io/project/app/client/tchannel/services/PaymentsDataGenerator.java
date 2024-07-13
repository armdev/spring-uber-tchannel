package io.project.app.client.tchannel.services;

import com.github.f4b6a3.uuid.UuidCreator;
import com.github.javafaker.Faker;
import io.project.app.client.tchannel.domain.Payments;
import io.project.app.client.tchannel.repositories.PaymentsRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author armena
 */
@Slf4j
@Service
@AllArgsConstructor
public class PaymentsDataGenerator {

    private final PaymentsRepository paymentsRepository;

    private final DataSender dataSender;

    public void generateSaveAndSend() {
        
        List<Payments> generateRandomPayment = generateRandomPayment(100);
        for (Payments pp : generateRandomPayment) {
            Payments save = paymentsRepository.save(pp);
            dataSender.justSend(save);
        }

        long count = paymentsRepository.count();
        log.info("Data SENT " + count);

    }

    private List<Payments> generateRandomPayment(Integer count) {
        String globalId = UuidCreator.getTimeOrderedEpoch().toString();

        Faker faker = new Faker();
        List<Payments> all = new ArrayList<>();
        Payments payment = new Payments();
        for (int i = 0; i <= count; i++) {
            payment = new Payments();
            payment.setId(UuidCreator.getTimeOrderedEpoch().toString());
            if (i % 2 == 0) {
                payment.setClientId(globalId);
            } else {
                payment.setClientId(UuidCreator.getTimeOrderedEpoch().toString());
            }
            payment.setTransactionType(generateRandomTransactionType());
            payment.setAmount(faker.number().randomDouble(2, 10, 10000));
            payment.setCreditAccountType(generateRandomAccountType());
            payment.setCreditAccount(faker.finance().creditCard());
            payment.setDebitAccountType(generateRandomAccountType());
            payment.setDebitAccount(faker.finance().iban());
            payment.setStatus("CREATED");
            payment.setCreated(LocalDateTime.now());
            payment.setUpdated(LocalDateTime.now());
            all.add(payment);

        }
        return all;
    }

    private String generateRandomTransactionType() {
        Faker faker = new Faker();
        String[] transactionTypes = {
            "DEPOSIT", "WITHDRAWAL", "TRANSFER", "INTERNATIONAL_TRANSFER", "PAYMENT",
            "REMITTANCE", "PURCHASE", "REFUND", "CHARGE", "DISBURSEMENT",
            "CREDIT", "DEBIT", "FEE", "REVERSAL"
        };
        return transactionTypes[faker.random().nextInt(transactionTypes.length)];
    }

    private String generateRandomAccountType() {
        Faker faker = new Faker();
        String[] accountTypes = {
            "ACCOUNT", "CARD", "CRYPTO_ACCOUNT", "SAVINGS_ACCOUNT", "CHECKING_ACCOUNT",
            "MOBILE_WALLET", "PAYPAL", "ALIPAY", "WECHAT_PAY", "BANK_TRANSFER",
            "WIRE_TRANSFER", "PREPAID_CARD", "DEPOSITORY_ACCOUNT", "LOAN_ACCOUNT"
        };
        return accountTypes[faker.random().nextInt(accountTypes.length)];
    }

}
