package br.com.digitalmenu.domain.entity;

import br.com.digitalmenu.domain.enums.Category;
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
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT")
public class Product {

    @Id
    @Column(name = "IDT_PRODUCT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESC_PRODUCT", nullable = false)
    private String description;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Column(name = "NAM_CATEGORY", nullable = false)
    private Category category;

    @Column(name = "IMAGE_PATH")
    private String imagePath;

    @Column(name = "INGREDIENTS", nullable = false)
    private String ingredients;

    @Column(name = "FLG_ADDITIONAL", nullable = false)
    private Boolean additional;

    @CreationTimestamp
    @Column(name = "DAT_CREATE")
    private LocalDateTime datCreate;

    @UpdateTimestamp
    @Column(name = "DAT_UPDATE")
    private LocalDateTime datUpdate;
}