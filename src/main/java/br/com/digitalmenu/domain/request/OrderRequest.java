package br.com.digitalmenu.domain.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderRequest {

    @NotNull(message = "OrderItemList is mandatory.")
    List<OrderItemRequest> orderItemList;

    @NotNull(message = "ClientId is mandatory.")
    private Long clientId;

    @NotNull(message = "AddressId is mandatory.")
    private Long addressId;

}
