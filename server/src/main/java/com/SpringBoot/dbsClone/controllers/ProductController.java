package com.SpringBoot.dbsClone.controllers;

import com.SpringBoot.dbsClone.models.Product;
import com.SpringBoot.dbsClone.repositories.FactoryRepository;
import com.SpringBoot.dbsClone.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "api/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FactoryRepository factoryRepository;

    @GetMapping("")
    List<Product> getAll() {
//        return ResponseEntity.status(200).body(productRepository.findAll());
        return productRepository.findAll();
    }

    @PostMapping("/insert")
    ResponseEntity insertProduct(@RequestBody Product newProduct) {
        try {
            newProduct.setFactory(factoryRepository.findById(newProduct.getFactory().getIdFactor()).orElseThrow(() ->
                    new RuntimeException("factory not found")));
            if (newProduct.getProductBonus() != null)
                newProduct.setProductBonus(productRepository.findById(newProduct.getProductBonus().getName()).orElseThrow(() ->
                    new RuntimeException("product bonus not found")));
//        productRepository.save(newProduct);
        } catch (Exception e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(200).body(productRepository.save(newProduct));
    }

    @DeleteMapping("/delete/{name}")
    ResponseEntity deleteByName(@PathVariable String name){
        if (!productRepository.findById(name).isPresent()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                "Dont found product have name : " + name
        );
        productRepository.deleteById(name);
        return ResponseEntity.status(200).body(productRepository.findAll());
    }

    @GetMapping("/{key}")
    ResponseEntity getProductByKey(@PathVariable String key){
        System.out.println(key);
        try {
            List<Product> foundedProducts = productRepository.findProductByNameContaining(key).orElseThrow(() ->
                    new RuntimeException("Not found")
            );
            System.out.println(foundedProducts);
            return ResponseEntity.status(HttpStatus.OK).body(foundedProducts);
        } catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Done founded product contain : " + key);
        }
    }

    @PutMapping("/update/{name}")
    ResponseEntity updateProduct(@PathVariable String name,@RequestBody Product newProduct) {
//        Product foundedProduct = productRepository.findById(name).orElse(newProduct);
        try {
            factoryRepository.findById(newProduct.getFactory().getIdFactor()).orElseThrow(() ->
                    new RuntimeException("factory not found"));
            if (newProduct.getProductBonus() != null)
                productRepository.findById(newProduct.getProductBonus().getName()).orElseThrow(() ->
                        new RuntimeException("product bonus not found"));
            System.out.println(newProduct);

            // update
//            foundedProduct.setDebutTime(newProduct.getDebutTime());
//            foundedProduct.setDiscount(newProduct.getDiscount());
//            foundedProduct.setPrice(newProduct.getPrice());
//            foundedProduct.setMaterial(newProduct.getMaterial());
//            foundedProduct.setGuaranteeTime(newProduct.getGuaranteeTime());
//            foundedProduct.setName(newProduct.getName());

            return ResponseEntity.status(200).body(productRepository.save(newProduct));
        } catch (Exception e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/test-api")
    ResponseEntity<Object> testApi(@Valid @RequestBody Product product) {
//        if (bindingResult.hasErrors()) {
//            throw new RuntimeException("hehehe Siuuuuuuu");
//        }
        System.out.println(product);
        return ResponseEntity.status(HttpStatus.OK).body("Test API");
//        List<Product> page = productRepository.findAll(PageRequest.of(0,3, Sort.by("name").ascending())).toList();
////        System.out.println(productRepository.findById(product.getProductBonus().getName()));
//        return ResponseEntity.status(HttpStatus.OK).body(page);
    }
}
