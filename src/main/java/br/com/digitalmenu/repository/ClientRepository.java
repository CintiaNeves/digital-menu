package br.com.digitalmenu.repository;

import br.com.digitalmenu.domain.entity.Client;
import br.com.digitalmenu.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    public Optional<Client> findByPhone(String phone);

    public Optional<Client> findByName(String name);

    public List<Client> findByNameContaining(String name);
}
