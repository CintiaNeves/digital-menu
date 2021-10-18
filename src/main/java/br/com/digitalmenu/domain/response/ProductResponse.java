package br.com.digitalmenu.domain.response;

import br.com.digitalmenu.domain.enums.Category;
import lombok.Data;

@Data
public class ProductResponse {

    private String description;

    private Double price;

    private Category category;

    private String ingredients;

}
