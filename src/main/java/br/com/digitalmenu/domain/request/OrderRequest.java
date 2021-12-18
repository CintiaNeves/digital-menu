package br.com.digitalmenu.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @NotNull(message = "OrderItemList is mandatory.")
    List<OrderItemRequest> orderItemList;

    @NotNull(message = "customerId is mandatory.")
    private Long customerId;

    @NotNull(message = "AddressId is mandatory.")
    private Long addressId;

}
