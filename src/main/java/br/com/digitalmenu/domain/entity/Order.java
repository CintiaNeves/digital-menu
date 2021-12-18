package br.com.digitalmenu.domain.entity;

import br.com.digitalmenu.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS")
public class Order {
    @Id
    @Column(name = "IDT_ORDERS")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STATUS")
    private Status status;

    @JoinColumn(name = "IDT_ADDRESS")
    @OneToOne
    private Address address;

    @JoinColumn(name = "IDT_ORDERS")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItemList;

    @OneToOne
    private Customer customer;

    @CreationTimestamp
    @Column(name = "DAT_CREATE")
    private LocalDateTime datCreate;

    @UpdateTimestamp
    @Column(name = "DAT_UPDATE")
    private LocalDateTime datUpdate;
}
