package br.com.digitalmenu.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddressRequest {
    private String postalArea;

    @NotBlank(message = "Address name is mandatory.")
    private String addressName;

    @NotNull(message = "Number is mandatory.")
    private Integer number;

    @NotNull(message = "City id is mandatory.")
    private Long cityId;
}
