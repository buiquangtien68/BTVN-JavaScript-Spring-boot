package com.example.demo.service.impl;

import com.example.demo.dao.PersonDAO;
import com.example.demo.model.Person;
import com.example.demo.response.PageResponse;
import com.example.demo.response.impl.PageResponseImpl;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDAO personDAO;
    @Override
    public void printListPeople(List<Person> persons) {

    }

    @Override
    public List<Person> getAll() {
        return personDAO.getAll();
    }

    @Override
    public PageResponse<Person> getAllPeople(int page, int pageSize) {
        return new PageResponseImpl<>(personDAO.getAll(), page, pageSize);
    }

    @Override
    public List<Person> sortPeopleByFullName() {
        return List.of();
    }

    @Override
    public List<Person> sortPeopleByFullNameReversed() {
        return List.of();
    }

    @Override
    public List<String> getSortedJobs() {
        return List.of();
    }

    @Override
    public List<String> getSortedCities() {
        return List.of();
    }

    @Override
    public List<String> femaleNames() {
        return List.of();
    }

    @Override
    public Person highestEarner() {
        return null;
    }

    @Override
    public List<Person> bornAfter1990() {
        return List.of();
    }

    @Override
    public double averageSalary() {
        return 0;
    }

    @Override
    public double averageAge() {
        return 0;
    }

    @Override
    public List<Person> nameContains(String keyword) {
        return List.of();
    }

    @Override
    public List<Person> sortedByAgeForMale() {
        return List.of();
    }

    @Override
    public Person longestName() {
        return null;
    }

    @Override
    public List<Person> aboveAverageSalary() {
        return List.of();
    }

    @Override
    public Map<String, List<Person>> groupPeopleByCity() {
        return Map.of();
    }
}
