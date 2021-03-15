package br.com.digitalmenu.domain.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderItemRequest {

    @NotNull(message = "productId is mandatory.")
    private Long productId;

    @NotNull(message = "Amount is mandatory.")
    private Integer amount;

    @NotNull(message = "Price item is mandatory.")
    private Double priceItem;

}
