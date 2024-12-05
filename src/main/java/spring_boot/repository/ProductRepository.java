package spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring_boot.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

}
