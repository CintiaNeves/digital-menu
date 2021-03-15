package br.com.digitalmenu.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CityRequest {

    @NotBlank(message = "Name is mandatory.")
    private String name;
}
