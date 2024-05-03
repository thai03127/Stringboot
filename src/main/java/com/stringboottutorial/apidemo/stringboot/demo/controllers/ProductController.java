package com.stringboottutorial.apidemo.stringboot.demo.controllers;

import com.stringboottutorial.apidemo.stringboot.demo.models.Product;
import com.stringboottutorial.apidemo.stringboot.demo.models.ResponseObject;
import com.stringboottutorial.apidemo.stringboot.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {
    //ben trong day se dinh nghia cac Methods
    //DI = Dependencies Injection
    @Autowired
    private ProductRepository repository;
    //khi su dung Autowired thi repository se duoc tao ra ngay khi app chay - chi tao 1 lan

    @GetMapping("")
    // this request is http://localhost:8080/api/v1/Products
    List<Product> getAllProducts(){
//       return List.of(
//               new Product(1L,"Iphone 5",12,802.500,""),
//        new Product(2L,"Ipad 1",23,954.670,"")
//       );
        return repository.findAll();//where is data?
    }
    //You must save this to database, Now we have H2 DB - In-memory Database
    //You can also send request by using Postman


    //GET detail product (FIND product)
    @GetMapping("/{id}")
    //Let's return an object with: data, message, status
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<Product> foundProduct = repository.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("Done", "Query Product successfullly", foundProduct)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find product with id: "+id,"")
                );

//        if(foundProduct.isPresent()){
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("ok", "Query Product successfullly", foundProduct)
//            );
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("failed", "Cannot find product with id: "+id,"")
//            );
//        }
    }

    //ADD new Product with POST method
    //Postman: Raw, JSON
    @PostMapping("addProduct")
    ResponseEntity<ResponseObject> addProduct(@RequestBody Product newProduct){
        List<Product> foundProducts = repository.findByName(newProduct.getName().trim());
        if(foundProducts.size()>0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("Failed", "Product Name has exists", "")
            );
        }
        //2 product must not have the same name
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Done", "Add Product successfullly", repository.save(newProduct))
        );
    }

    //UPDATE, UPSERT = update if found, otherwise insert -- PUT method
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id){
        Object updateProduct = repository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setQuantity(newProduct.getQuantity());
                    product.setPrice(newProduct.getPrice());
                    product.setUrl(newProduct.getUrl());
                    return repository.save(product);
                }).orElseGet(()->{
                        newProduct.setId(id);
                        return repository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Done", "Updated Product successfullly", updateProduct)
        );
    }

    //DELETE product -- DELETE method
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> delteProduct(@PathVariable Long id){
        boolean exists = repository.existsById(id);
        if(exists){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Done", "Delete Product successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Failed", "Not found product to delete", "")
        );
    }
}
