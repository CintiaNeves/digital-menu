package br.com.digitalmenu.domain.request;

import br.com.digitalmenu.domain.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotBlank(message = "Description must not be null.")
    private String description;

    @Min(value = 1, message = "Price is lower than minimum.")
    @NotNull(message = "Price must not be null.")
    private Double price;

    @NotNull(message = "Category must not be null.")
    private Category category;
    
    @NotNull(message = "Additional must not be null.")
    private Boolean additional;

    @NotBlank(message = "Ingredients must not be null.")
    private String ingredients;
}
