package com.testman.controllers;

import com.testman.exceptions.ErrorModels;
import com.testman.models.TestCategoryDao;
import com.testman.services.TestCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Author: kunitin
 * Created: 03/04/22
 * Info: TestCategory controller
 **/

@RestController
@Tag(name = "TestCategory APIs")
@RequestMapping("/testcategory")
public class TestCategoryController {


    @Autowired
    TestCategoryService testCategoryService;

    @GetMapping
    @Operation(summary = "Get all test categories", description = "To get all test categories in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TestCategoryDao.class)))
    })
    public ResponseEntity<Collection<TestCategoryDao>> getTestCategories() {
        return new ResponseEntity<>(testCategoryService.getTestCategory(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get a testCategory", description = "To get a TestCategory for the given TestCategory id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TestCategoryDao.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModels.MinimalErrorModel.class)))
    })
    public ResponseEntity<TestCategoryDao> getTestCategory(@PathVariable long id) throws Exception {
        TestCategoryDao testCategoryFetched = testCategoryService.getTestCategory(id);
        if (testCategoryFetched == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(testCategoryFetched, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add a testCategory", description = "To add a testCategory for the given request body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TestCategoryDao.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModels.MinimalErrorModel.class)))
    })
    public ResponseEntity<?> addATestCategory(@Valid @RequestBody TestCategoryDao testCategory) {
        TestCategoryDao savedtestCategory = testCategoryService.addTestCategory(testCategory);
        if (savedtestCategory != null) {
            return new ResponseEntity<>(savedtestCategory, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Edit a testCategory", description = "To edit a testCategory for the given testCategory id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModels.MinimalErrorModel.class)))
    })
    public void editTestCategory(@PathVariable long id, @Valid @RequestBody TestCategoryDao testCategory) throws Exception {
        testCategoryService.editTestCategory(id, testCategory);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a testCategory", description = "To delete a testCategory for the given testCategory id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModels.MinimalErrorModel.class)))
    })
    public void deleteATestCategory(@PathVariable long id) throws Exception {
        testCategoryService.deleteTestCategory(id);
    }
}
