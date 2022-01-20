package pl.edu.pjatk.cardictionary.service;

import io.vavr.control.Try;
import pl.edu.pjatk.cardictionary.model.Brand;
import pl.edu.pjatk.cardictionary.model.Car;

import java.util.List;

public interface CarService {
    Try<List<Car>> getCar(Brand brand, String model);

    Try<List<Car>> getAllCars();

    void removeCar(Brand brand, String model);
}
