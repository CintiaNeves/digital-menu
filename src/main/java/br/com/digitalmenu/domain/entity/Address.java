package br.com.digitalmenu.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id
    @Column(name = "IDT_ADDRESS")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "POSTAL_AREA")
    private String postalArea;

    @Column(name = "NAM_ADDRESS", nullable = false)
    private String addressName;

    @Column(name = "NUMBER", nullable = false)
    private Integer number;

    @OneToOne
    @JoinColumn(name = "IDT_CITY")
    private City city;
}
