package crk.study.paymentsservice.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "payments")
public class PaymentEntity {

    @Id
    private String paymentId;
    @Column
    public String orderId;
}
