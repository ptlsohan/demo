package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Product;
import com.example.demo.repository.ProductRepository;



@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productrepository;

    Product iphone = new Product();
    Product samsung = new Product();
    Product pixel = new Product();

    public void addProducts() {
    	iphone.setName("Ted Williams");
        

        productrepository.save(iphone);

        pixel.setName("pixel");
       

        productrepository.save(pixel);

        samsung.setName("samsung");
       

        productrepository.save(samsung);

        System.out.println("Products have been added : " + productrepository.findAll());

    }
}