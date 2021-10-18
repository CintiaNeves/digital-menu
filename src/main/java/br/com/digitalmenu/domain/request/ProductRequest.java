package br.com.digitalmenu.domain.request;

import br.com.digitalmenu.domain.enums.Category;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductRequest {

    @NotBlank(message = "Description is mandatory.")
    private String description;

    @Min(value = 1, message = "Price is lower than minimum.")
    @NotNull(message = "Price is mandatory.")
    private Double price;

    @NotNull(message = "Category is mandatory.")
    private Category category;
    
    @NotNull(message = "Additional is mandatory.")
    private Boolean additional;

    @NotBlank(message = "Ingredients is mandatory.")
    private String ingredients;
}
