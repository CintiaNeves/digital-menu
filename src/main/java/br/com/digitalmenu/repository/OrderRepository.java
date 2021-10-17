package br.com.digitalmenu.repository;

import br.com.digitalmenu.domain.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByCustomerId(Long clientId);
}
