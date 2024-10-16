package com.fatima.springboot.di.app.springboot_di.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fatima.springboot.di.app.springboot_di.models.Product;
import com.fatima.springboot.di.app.springboot_di.services.ProductService;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
 

@RestController
@RequestMapping("/api")
 
public class SomeController {
 
    @Autowired
private ProductService service ;

@GetMapping

    public List<Product> list () {
return service.findAll();
    }

    @GetMapping("/{id}")
    public Product show (@PathVariable Long id ){
        return service.findById(id);
    }

}
