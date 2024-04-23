package com.example.demo.database;

import com.example.demo.model.Product;
import com.example.demo.utils.IReadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDB implements CommandLineRunner {
    @Qualifier("readFileJson")
    @Autowired
    private IReadFile fileReader;
    @Override
    public void run(String... args) throws Exception {
        ProductDB.products=fileReader.readFile("ProductData.json");
        for (Product product : ProductDB.products) {
            System.out.println(product);
        }

    }
}
