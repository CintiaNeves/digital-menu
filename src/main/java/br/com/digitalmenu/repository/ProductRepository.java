package br.com.digitalmenu.repository;

import br.com.digitalmenu.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Optional<Product> findByDescription(String description);

    public List<Product> findByDescriptionContaining(String description);
}
