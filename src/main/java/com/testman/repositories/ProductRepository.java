package com.testman.repositories;

import com.testman.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Transactional
    @Modifying
    @Query("update ProductEntity p set p.name=?2, p.desc=?3 where p.id=?1")
    int updateProductNameAndDescription(long id, String name, String desc);
}
