package br.com.digitalmenu.domain.entity;

import lombok.Data;
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
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
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

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @JoinColumn(name = "IDT_CLIENT")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Address> addressList = new HashSet<>();

    @CreationTimestamp
    @Column(name = "DAT_CREATE")
    private LocalDateTime datCreate;

    @UpdateTimestamp
    @Column(name = "DAT_UPDATE")
    private LocalDateTime datUpdate;
}
