package br.com.digitalmenu.domain.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "CLIENT")
public class Client {
    @Id
    @Column(name = "IDT_CLIENT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAM_CLIENT", nullable = false)
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @JoinColumn(name = "IDT_CLIENT")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addressList;
}
