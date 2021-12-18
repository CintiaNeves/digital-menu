package br.com.digitalmenu.domain.request;

import br.com.digitalmenu.domain.entity.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    @NotBlank(message = "Address must not be null.")
    private String addressName;

    @NotNull(message = "Number must not be null.")
    private Integer number;

    @NotNull(message = "City must not be null.")
    private City city;

    private String postalArea;
}
