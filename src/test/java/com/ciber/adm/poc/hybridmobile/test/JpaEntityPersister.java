package com.ciber.adm.poc.hybridmobile.test;

import com.ciber.adm.poc.hybridmobile.domain.*;
import com.ciber.adm.poc.hybridmobile.util.DateUtil;
import com.ciber.adm.poc.hybridmobile.util.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ciber.adm.poc.hybridmobile.test.EntityFactory.newCustomer;
import static com.ciber.adm.poc.hybridmobile.test.EntityFactory.newUser;

@Component
@Transactional
public class JpaEntityPersister implements EntityPersister {

    @Autowired
    private EntityRepository entityRepository;

    @Override
    public Customer createCustomer(String email) {
        Customer customer = newCustomer(email);
        entityRepository.persist(customer);
        return customer;
    }

    @Override
    public HybridmobileUserDetails createUser(String username, String password) {
        HybridmobileUserDetails user = newUser(username, password);
        entityRepository.persist(user);
        return user;
    }

//    @Override
//    public List<Rental> createRentalsFor(Customer user) {
//        City city = new City("Mannheim");
//        entityRepository.persist(city);
//        Car car = new Car("Opel", "Meriva", BigDecimal.valueOf(25.24), city, CarType.ECONOMY);
//        entityRepository.persist(car);
//        Date date = DateUtil.currentDateOnMidnight();
//        Rental rental = new Rental(date, date, car, user);
//        entityRepository.persist(rental);
//        List<Rental> rentals = new ArrayList<Rental>();
//        rentals.add(rental);
//        return rentals;
//    }
//
//    @Override
//    public List<City> createCities(String... cityNames) {
//        List<City> cities = new ArrayList<City>();
//        for (String name : cityNames) {
//            City city = new City(name);
//            entityRepository.persist(city);
//            cities.add(city);
//        }
//        return cities;
//    }
//
//    @Override
//    public Car createAvailableCarFor(String cityName, BigDecimal maxPrice) {
//        City city = new City(cityName);
//        entityRepository.persist(city);
//        Car car = new Car("VW", "Golf", maxPrice, city, CarType.ECONOMY);
//        entityRepository.persist(car);
//        return car;
//    }

}
