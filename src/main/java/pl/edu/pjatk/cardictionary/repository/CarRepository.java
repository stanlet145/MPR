package pl.edu.pjatk.cardictionary.repository;

import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.cardictionary.model.Brand;
import pl.edu.pjatk.cardictionary.model.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findCarByBrandAndModelOrderByBrandAsc(Brand brand, String model);

    Option<Car> findTopByBrandAndModel(Brand brand, String model);
}