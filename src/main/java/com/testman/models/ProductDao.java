package com.testman.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Author: kunitin
 * Created: 19/03/22
 * Info: Model for product
 **/

@Data
public class ProductDao {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double id;

    @NotNull(message = "name must be passed to identify the product")
    @Size(min = 3, message = "name must be between 2 and 101 characters", max = 100)
    private String name;

    @Size(message = "description must be less than 256 char long", max = 255)
    private String desc;

}
