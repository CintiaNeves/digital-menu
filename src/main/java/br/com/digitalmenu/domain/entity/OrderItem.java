package br.com.digitalmenu.domain.entity;

import lombok.Data;
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
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    @Column(name = "IDT_ORDER_ITEM")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AMOUNT", nullable = false)
    private Integer amount;

    @Column(name = "PRICE_ITEM", nullable = false)
    private Double priceItem;

    @OneToOne
    @JoinColumn(name = "IDT_PRODUCT")
    private Product product;

    @CreationTimestamp
    @Column(name = "DAT_CREATE")
    private LocalDateTime datCreate;

    @UpdateTimestamp
    @Column(name = "DAT_UPDATE")
    private LocalDateTime datUpdate;

    public OrderItem() {}

    public OrderItem(Integer amount, Double priceItem, Product product) {
        this.product = product;
        this.amount = amount;
        this.priceItem = priceItem;
    }
}
