package com.testman.services;

import com.testman.entities.TestCategoryEntity;
import com.testman.exceptions.TestmanErrorCodes;
import com.testman.exceptions.TestmanException;
import com.testman.models.TestCategoryDao;
import com.testman.repositories.TestCategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Author: kunitin
 * Created: 03/04/22
 * Info: Implementation of TestCategoryService
 **/

@Service
public class TestCategoryServiceImpl implements TestCategoryService {

    @Autowired
    TestCategoryRepository TestCategoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Collection<TestCategoryDao> getTestCategory() {
        List<TestCategoryEntity> TestCategoryList = TestCategoryRepository.findAll();
        Collection<TestCategoryDao> TestCategoryDaos = new ArrayList<TestCategoryDao>();
        for (TestCategoryEntity TestCategoryEntity : TestCategoryList) {
            TestCategoryDaos.add(modelMapper.map(TestCategoryEntity, TestCategoryDao.class));
        }

        return TestCategoryDaos;
    }

    @Override
    public TestCategoryDao addTestCategory(TestCategoryDao testCategory) {

        try {
            TestCategoryEntity savedTestCategory = TestCategoryRepository.save(modelMapper.map(testCategory, TestCategoryEntity.class));
            return modelMapper.map(savedTestCategory, TestCategoryDao.class);
        } catch (DataIntegrityViolationException e) {
            if (e.getLocalizedMessage().contains("ConstraintViolationException"))
                throw new TestmanException("ConstraintViolationException: Failed to add TestCategory due to user error. Check if TestCategory name is unique", TestmanErrorCodes.TESTMAN_CLIENT_ERROR);
        } catch (Exception e) {
            throw new TestmanException("Failed to add TestCategory", TestmanErrorCodes.TESTMAN_INTERNAL_ERROR, e);
        }
        throw new TestmanException("Failed to add TestCategory", TestmanErrorCodes.TESTMAN_INTERNAL_ERROR);
    }

    @Override
    public void deleteTestCategory(long id) throws Exception {
        Optional<TestCategoryEntity> testCategory = TestCategoryRepository.findById(id);
        if (testCategory.isEmpty()) {
            throw new TestmanException("Failed to find TestCategory to delete", TestmanErrorCodes.TEST_CATEGORY_NOT_FOUND);
        }
        TestCategoryRepository.delete(modelMapper.map(getTestCategory(id), TestCategoryEntity.class));
        if (containsTestCategory(id)) {
            throw new TestmanException("Failed to delete TestCategory", TestmanErrorCodes.FAILED_TO_DELETE_TESTCATEGORY);
        }
    }

    @Override
    public TestCategoryDao getTestCategory(long id) throws Exception {
        Optional<TestCategoryEntity> TestCategory = TestCategoryRepository.findById(id);
        if (TestCategory.isEmpty()) {
            throw new TestmanException("Failed to find TestCategory", TestmanErrorCodes.TEST_CATEGORY_NOT_FOUND);
        }
        return modelMapper.map(TestCategory, TestCategoryDao.class);
    }

    @Override
    public boolean containsTestCategory(long id) {
        return TestCategoryRepository.existsById(id);
    }

    @Override
    public void editTestCategory(long id, TestCategoryDao testCategoryDao) throws TestmanException {
        if (!containsTestCategory(id)) {
            throw new TestmanException("Failed to find TestCategory to edit", TestmanErrorCodes.TEST_CATEGORY_NOT_FOUND);
        }
        TestCategoryRepository.updateTestCategoryNameAndDescription(id, testCategoryDao.getName(), testCategoryDao.getDesc());
    }

}
