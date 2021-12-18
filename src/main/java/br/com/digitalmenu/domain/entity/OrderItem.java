package br.com.digitalmenu.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    @Column(name = "IDT_ORDER_ITEM")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AMOUNT", nullable = false)
    private Integer amount;

    @OneToOne
    @JoinColumn(name = "IDT_PRODUCT")
    private Product product;

    @CreationTimestamp
    @Column(name = "DAT_CREATE")
    private LocalDateTime datCreate;

    @UpdateTimestamp
    @Column(name = "DAT_UPDATE")
    private LocalDateTime datUpdate;
}

