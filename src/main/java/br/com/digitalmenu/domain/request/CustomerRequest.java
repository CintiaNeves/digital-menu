package br.com.digitalmenu.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    @NotBlank(message = "Name is mandatory.")
    private String name;

    @NotBlank(message = "Phone is mandatory.")
    private String phone;

    private String email;

    @NotNull(message = "Address is mandatory.")
    List<AddressRequest> addressList;
}
