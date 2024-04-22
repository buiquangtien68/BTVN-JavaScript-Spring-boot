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
        return personDAO.sortPeopleByFullName();
    }

    @Override
    public List<Person> sortPeopleByFullNameReversed() {
        return personDAO.sortPeopleByFullNameReversed();
    }

    @Override
    public List<String> getSortedJobs() {
        return personDAO.getSortedJobs();
    }

    @Override
    public List<String> getSortedCities() {
        return personDAO.getSortedCities();
    }

    @Override
    public List<String> femaleNames() {
        return personDAO.femaleNames();
    }

    @Override
    public Person highestEarner() {
        return personDAO.highestEarner();
    }

    @Override
    public List<Person> bornAfter1990() {
        return personDAO.bornAfter1990();
    }

    @Override
    public double averageSalary() {
        return personDAO.averageSalary();
    }

    @Override
    public double averageAge() {
        return personDAO.averageAge();
    }

    @Override
    public List<Person> nameContains(String keyword) {
        return personDAO.nameContains(keyword);
    }

    @Override
    public List<Person> sortedByAgeForMale() {
        return personDAO.sortedByAgeForMale();
    }

    @Override
    public Person longestName() {
        return personDAO.longestName();
    }

    @Override
    public List<Person> aboveAverageSalary() {
        return personDAO.aboveAverageSalary();
    }

    @Override
    public Map<String, List<Person>> groupPeopleByCity() {
        return personDAO.groupPeopleByCity();
    }
}
