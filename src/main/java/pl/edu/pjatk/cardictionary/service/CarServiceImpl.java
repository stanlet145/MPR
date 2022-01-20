package pl.edu.pjatk.cardictionary.service;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.cardictionary.exception.ResourceNotFoundException;
import pl.edu.pjatk.cardictionary.model.Brand;
import pl.edu.pjatk.cardictionary.model.Car;
import pl.edu.pjatk.cardictionary.repository.CarRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public Try<List<Car>> getCar(Brand brand, String model) {
        return Try.of(() -> carRepository.findCarByBrandAndModelOrderByBrandAsc(brand, model))
                .onFailure(throwable -> log.error(throwable.getMessage()));
    }

    public Try<List<Car>> getAllCars() {
        return Try.of(carRepository::findAll)
                .onFailure(throwable -> log.error(throwable.getMessage()));
    }

    public void removeCar(Brand brand, String model) {
        carRepository.delete(carRepository.findTopByBrandAndModel(brand, model).getOrElseThrow(ResourceNotFoundException::new));
    }
}
