package br.com.digitalmenu.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id
    @Column(name = "IDT_ADDRESS")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAM_ADDRESS", nullable = false)
    private String addressName;

    @ManyToOne
    @JoinColumn(name = "IDT_CLIENT")
    private Client client;
}
