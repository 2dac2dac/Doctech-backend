package dac2dac.doctect.bootpay.entity;

import dac2dac.doctect.bootpay.entity.constant.PaymentStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @CreatedDate
    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDateTime createDate;

    private Integer price;
    private String orderName;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Builder
    public PaymentInfo(PaymentMethod paymentMethod, LocalDateTime createDate, Integer price, String orderName, PaymentStatus status) {
        this.paymentMethod = paymentMethod;
        this.createDate = createDate;
        this.price = price;
        this.orderName = orderName;
        this.status = status;
    }
}
