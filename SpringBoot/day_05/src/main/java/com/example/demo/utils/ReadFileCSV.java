package com.example.demo.utils;

import com.example.demo.model.Person;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
@Component
public class ReadFileCSV implements IReadFile{

    @Override
    public List<Person> readFile(String path) throws IOException {
        System.out.println("Đọc file CSV");// sử dụng OpenCSV
        FileReader reader = new FileReader(path);
        CsvToBean<Person> csvToBean = new CsvToBeanBuilder(reader)
                .withType(Person.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<Person> objects = csvToBean.parse();

        reader.close();
        return objects;
    }
}
