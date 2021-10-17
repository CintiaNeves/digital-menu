package br.com.digitalmenu.domain.request;

import br.com.digitalmenu.domain.enums.Status;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderStatusRequest {

    @NotNull(message = "Status is mandatory.")
    private Status status;
}
