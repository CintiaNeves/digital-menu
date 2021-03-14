package br.com.digitalmenu.domain.entity;

import br.com.digitalmenu.domain.enums.Category;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @Column(name = "IDT_PRODUCT")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DESC_PRODUCT", nullable = false)
    private String description;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Column(name = "NAM_CATEGORY", nullable = false)
    private Category category;

    @Column(name = "IMAGE")
    private String image;

    @CreationTimestamp
    @Column(name = "DAT_CREATE")
    private LocalDateTime datCreate;

    @UpdateTimestamp
    @Column(name = "DAT_UPDATE")
    private LocalDateTime datUpdate;
}