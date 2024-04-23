package com.example.demo.utils;

import com.example.demo.model.Product;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
@Component
public class ReadFileCSV implements IReadFile{

    @Override
    public List<Product> readFile(String path) throws IOException {
        System.out.println("Đọc file CSV");// sử dụng OpenCSV
        FileReader reader = new FileReader(path);
        CsvToBean<Product> csvToBean = new CsvToBeanBuilder(reader)
                .withType(Product.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<Product> objects = csvToBean.parse();

        reader.close();
        return objects;
    }
}
