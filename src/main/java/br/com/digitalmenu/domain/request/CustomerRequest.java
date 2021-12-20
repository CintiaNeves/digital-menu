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

    @NotBlank(message = "Name must not be null.")
    private String name;

    @NotBlank(message = "Phone must not be null.")
    private String phone;

    private String email;

    @NotNull(message = "Address must not be null.")
    List<AddressRequest> addressList;
}
