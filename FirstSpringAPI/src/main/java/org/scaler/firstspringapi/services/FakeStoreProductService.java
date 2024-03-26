package org.scaler.firstspringapi.services;

import org.scaler.firstspringapi.dtos.FakeStoreProductDto;
import org.scaler.firstspringapi.models.Category;
import org.scaler.firstspringapi.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(category);
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        return product;
    }

    @Override
    public Product getProduct(Long id){
        //Call FakeStore API here to get the Product with give id
        FakeStoreProductDto fakeStoreProductDto =
        restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);
        //convert dto into product object
        if(fakeStoreProductDto==null){
            return null;
        }
        return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    };

//    @Override
//    public List<Product> getAllProducts(){
//        int i = 1;
//        ArrayList<Product> products = new ArrayList<>();
//        while(true){
//            FakeStoreProductDto fakeStoreProductDto =
//                    restTemplate.getForObject("https://fakestoreapi.com/products/"+i, FakeStoreProductDto.class);
//            if(fakeStoreProductDto == null){
//                break;
//            }
//            products.add(convertFakeStoreDtoToProduct(fakeStoreProductDto));
//            i++;
//        }
//        return products;
//    };

    @Override
    public List<Product> getAllProducts(){
        ArrayList<Product> products = new ArrayList<>();
        FakeStoreProductDto [] fakeStoreProductDtos =
                restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        for(FakeStoreProductDto dto: fakeStoreProductDtos){
            if(dto == null){
                continue;
            }
            products.add(convertFakeStoreDtoToProduct(dto));
            }
        return products;
    }
}
