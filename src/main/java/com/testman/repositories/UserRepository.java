package com.testman.repositories;

import com.testman.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<ProductEntity, Long> {

    @Transactional
    @Modifying
    @Query("update UserEntity u set u.password=?3 where u.id=?1 and u.password=?2")
    int resetPasswordOfUser(long id, String oldPassword, String newPassword);
}