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

    @NotNull(message = "OrderItemList must not be null..")
    List<OrderItemRequest> orderItemList;

    @NotNull(message = "customerId must not be null..")
    private Long customerId;

    @NotNull(message = "AddressId must not be null..")
    private Long addressId;

}
