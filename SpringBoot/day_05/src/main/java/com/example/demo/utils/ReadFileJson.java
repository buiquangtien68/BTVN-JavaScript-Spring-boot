package com.example.demo.utils;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Component

public class ReadFileJson  implements IReadFile{
    @Override
    public List<Person> readFile(String path) {
        System.out.println("Đọc file Json");
        ObjectMapper mapper = new ObjectMapper();// sử dụng Jackson

        mapper.registerModule(new JavaTimeModule());
        try {
            // Đọc file JSON và chuyển đổi nó thành danh sách các đối tượng Book
            return mapper.readValue(Paths.get(path).toFile(), new TypeReference<List<Person>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();  // Trả về danh sách trống nếu có lỗi
    }
}
