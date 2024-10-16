package com.fatima.springboot.di.app.springboot_di.services;
//manipula los datos es decir hace calculos,trabaar con ellos,interactuar o combinar con otros repositorios 
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fatima.springboot.di.app.springboot_di.models.Product;
import com.fatima.springboot.di.app.springboot_di.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

   @Autowired
    private Environment environment;
    private ProductRepository repository ;
     public ProductServiceImpl(@Qualifier("productList")ProductRepository repository){
        this.repository = repository;
    }

    @Override
  public List<Product> findAll(){
        return repository.findAll().stream().map(p->{
            @SuppressWarnings("null")
            //System.out.println(environment.getProperty("config.price.tax",Double.class));
            //System.out.println(tax);
            Double priceTax = p.getPrice()* environment.getProperty("config.price.tax",Double.class);
           // Product newProd= new Product(p.getId(),p.getName(),priceImp.longValue());
           Product newProd =(Product)p.clone();
            newProd.setPrice(priceTax.longValue());
            return newProd;
            //p.setPrice(priceTax.longValue());
            //return p;
        
        }).collect (Collectors.toList());
    }
@Override
    public Product findById(Long id){
        return repository.findById( id );
    }

}
