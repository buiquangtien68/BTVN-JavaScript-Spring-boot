package com.example.demoapp.utils;

import com.example.demoapp.model.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
@Component
public class CSVFileReader implements IFileReader {
    @Override
    public List<Book> readFile(String path) throws IOException {
        System.out.println("Đọc file CSV");// sử dụng OpenCSV
        FileReader reader = new FileReader(path);
        CsvToBean<Book> csvToBean = new CsvToBeanBuilder(reader)
                .withType(Book.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<Book> objects = csvToBean.parse();
        reader.close();
        return objects;
    }
}
