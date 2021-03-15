package br.com.digitalmenu.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class ClientRequest {

    @NotBlank(message = "Name is mandatory.")
    private String name;

    @NotBlank(message = "Phone is mandatory.")
    private String phone;

    private String email;

    List<AddressRequest> addressList;
}
