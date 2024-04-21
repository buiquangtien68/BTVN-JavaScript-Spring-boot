package com.example.demo.utils;

import com.example.demo.model.Person;

import java.io.IOException;
import java.util.List;

public interface IReadFile {
    List<Person> readFile(String path) throws IOException;
}
