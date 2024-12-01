package spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring_boot.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long>{

}
