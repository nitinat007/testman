package com.testman.repositories;

import com.testman.entities.TestCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface TestCategoryRepository extends JpaRepository<TestCategoryEntity, Long> {

    @Transactional
    @Modifying
    @Query("update TestCategoryEntity tc set tc.name=?2, tc.desc=?3 where tc.id=?1")
    int updateTestCategoryNameAndDescription(long id, String name, String desc);
}
