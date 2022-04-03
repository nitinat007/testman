package com.testman.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Author: kunitin
 * Created: 03/04/22
 * Info: Model for Test Category
 **/

@Data
public class TestCategoryDao {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double id;

    @NotNull(message = "name must be passed to identify the product")
    @Size(min = 2, message = "name must be between 2 and 10 characters", max = 10)
    private String name;

    @Size(message = "description must be less than 256 char long", max = 255)
    private String desc;
}
