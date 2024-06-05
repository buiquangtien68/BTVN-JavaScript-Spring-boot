package com.example.demo.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminApi {
    //Quản lý user
    @PostMapping("/manage-user/create-user")
    public ResponseEntity<?>createUser(){
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }

    @GetMapping("/manage-user/read-profile/{id}")
    public ResponseEntity<?>readProfile(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @PutMapping("/manage-user/update-profile/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Quản lý category (CRU)
    @PostMapping("/manage-category/create-category")
    public ResponseEntity<?>createCategory(){
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }

    @GetMapping("/manage-category/read-category/{id}")
    public ResponseEntity<?>readCategory(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @PutMapping("/manage-category/update-category/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/manage-category/delete-category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Quản lý sản phẩm (CRUD)
    @PostMapping("/manage-product/create-product")
    public ResponseEntity<?>createProduct(){
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }

    @GetMapping("/manage-product/read-product/{id}")
    public ResponseEntity<?>readProduct (@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @PutMapping("/manage-product/update-product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/manage-product/delete-product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Quản lý brand (CRUD)
    @PostMapping("/manage-brand/create-brand")
    public ResponseEntity<?>createBrand(){
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }

    @GetMapping("/manage-brand/read-brand/{id}")
    public ResponseEntity<?>readBrand (@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @PutMapping("/manage-brand/update-brand/{id}")
    public ResponseEntity<?> updateBrand(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/manage-brand/delete-brand/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Quản lý order (CRU)
    @PostMapping("/manage-order/create-order")
    public ResponseEntity<?>createOrder(){
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }

    @GetMapping("/manage-order/read-order/{id}")
    public ResponseEntity<?>readOrder(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @PutMapping("/manage-order/update-order/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Quản lý bài viết (CRUD)
    @PostMapping("/manage-blog/create-blog")
    public ResponseEntity<?>createBlog(){
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }

    @GetMapping("/manage-blog/read-blog/{id}")
    public ResponseEntity<?>readBlog (@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @PutMapping("/manage-blog/update-blog/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/manage-blog/delete-blog/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
