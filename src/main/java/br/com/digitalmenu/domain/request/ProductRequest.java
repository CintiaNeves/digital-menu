package br.com.digitalmenu.domain.request;

import br.com.digitalmenu.domain.enums.Category;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductRequest {

    @NotBlank(message = "Description is mandatory.")
    private String description;

    @NotNull(message = "Price is mandatory.")
    private Double price;

    @NotNull(message = "Category is mandatory.")
    private Category category;

    @NotBlank(message = "Image is mandatory.")
    private String image;

    @NotNull(message = "Additional is mandatory.")
    private Boolean additional;

    @NotBlank(message = "Ingredients is mandatory.")
    private String ingredients;
}
