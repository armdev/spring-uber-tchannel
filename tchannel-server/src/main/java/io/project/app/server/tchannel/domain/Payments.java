package io.project.app.server.tchannel.domain;

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
@Entity
@Table(name = "payment", schema = "public")
@Data
public class Payments implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "transaction_id", updatable = false)
    private String transactionId;
    @Basic(optional = false)
    @Column(name = "client_id", updatable = false)
    private String clientId;
    @Basic(optional = false)
    @Column(name = "transaction_type", updatable = false)
    private String transactionType;
    @Basic(optional = false)
    @Column(name = "amount", updatable = false)
    private double amount;
    @Basic(optional = false)
    @Column(name = "credit_account_type", updatable = false)
    private String creditAccountType; 
    @Basic(optional = false)
    @Column(name = "credit_account", updatable = false)
    private String creditAccount;
    @Basic(optional = false)
    @Column(name = "debit_account_type", updatable = false)
    private String debitAccountType;
    @Basic(optional = false)
    @Column(name = "debit_account", updatable = false)
    private String debitAccount;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created;  

}
