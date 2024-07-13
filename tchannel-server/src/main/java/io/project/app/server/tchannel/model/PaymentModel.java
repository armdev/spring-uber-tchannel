package io.project.app.server.tchannel.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author armen
 */
@Data
public class PaymentModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String clientId;

    private String transactionType;

    private double amount;

    private String creditAccountType;

    private String creditAccount;

    private String debitAccountType;

    private String debitAccount;

    private String status;

    private LocalDateTime created;



}
