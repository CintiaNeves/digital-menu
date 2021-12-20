package br.com.digitalmenu.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

    @NotNull(message = "productId must not be null..")
    private Long productId;

    @NotNull(message = "Amount must not be null..")
    @Min(value = 1)
    private Integer amount;

}
