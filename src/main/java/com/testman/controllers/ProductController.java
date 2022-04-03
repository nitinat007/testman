package com.testman.controllers;

import com.testman.exceptions.ErrorModels;
import com.testman.exceptions.TestmanException;
import com.testman.models.ProductDao;
import com.testman.services.ProductService;
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
 * Created: 19/03/22
 * Info: Contains all endpoints for product
 **/

@RestController
@Tag(name = "Product APIs")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    @Operation(summary = "Get all product", description = "To get all products in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDao.class)))
    })
    public ResponseEntity<Collection<ProductDao>> getProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get a product", description = "To get a product for the given product id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDao.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModels.MinimalErrorModel.class)))
    })
    public ResponseEntity<ProductDao> getProduct(@PathVariable long id) throws Exception {
        ProductDao productFetched = productService.getProduct(id);
        if (productFetched == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productFetched, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add a product", description = "To add a product for the given request body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDao.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModels.MinimalErrorModel.class)))
    })
    public ResponseEntity<?> addAProduct(@Valid @RequestBody ProductDao product) {
        ProductDao savedProduct = productService.addProduct(product);
        if (savedProduct != null) {
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Edit a product", description = "To edit a product for the given product id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModels.MinimalErrorModel.class)))
    })
    public void editProduct(@PathVariable long id, @Valid @RequestBody ProductDao product) throws TestmanException {
        productService.editProduct(id, product);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a product", description = "To delete a product for the given product id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModels.MinimalErrorModel.class)))
    })
    public void deleteAProduct(@PathVariable long id) throws Exception {
        productService.deleteProduct(id);
    }
}
