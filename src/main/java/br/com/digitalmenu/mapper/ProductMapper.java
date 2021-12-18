package br.com.digitalmenu.mapper;

import br.com.digitalmenu.domain.entity.Product;
import br.com.digitalmenu.domain.request.ProductRequest;
import br.com.digitalmenu.domain.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "imagePath", ignore = true)
    @Mapping(target = "datCreate", ignore = true)
    @Mapping(target = "datUpdate", ignore = true)
    Product productFromRequest(ProductRequest productRequest);

    List<ProductResponse> productToProductResponse(List<Product> productList);

    ProductResponse productToProductResponse(Product product);
}
