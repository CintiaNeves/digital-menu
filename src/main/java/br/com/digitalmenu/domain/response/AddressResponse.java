package br.com.digitalmenu.domain.response;

import lombok.Data;

@Data
public class AddressResponse {

    private String addressName;

    private Integer number;

    private String cityName;
}
