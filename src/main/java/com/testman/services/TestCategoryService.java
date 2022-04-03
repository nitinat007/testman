package com.testman.services;

import com.testman.models.TestCategoryDao;

import java.util.Collection;


public interface TestCategoryService {

    public Collection<TestCategoryDao> getTestCategory();

    public TestCategoryDao addTestCategory(TestCategoryDao testCategoryDao);

    public void deleteTestCategory(long id) throws Exception;

    public TestCategoryDao getTestCategory(long id) throws Exception;

    public boolean containsTestCategory(long id);

    public void editTestCategory(long id, TestCategoryDao testCategoryDao) throws Exception;
}
