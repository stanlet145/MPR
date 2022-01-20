package pl.edu.pjatk.cardictionary.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.pjatk.cardictionary.exception.ResourceNotFoundException;
import pl.edu.pjatk.cardictionary.model.Brand;
import pl.edu.pjatk.cardictionary.model.Car;
import pl.edu.pjatk.cardictionary.repository.CarRepository;
import pl.edu.pjatk.cardictionary.service.CarService;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CarServiceTest {

    @MockBean
    CarRepository carRepository;
    @Autowired
    CarService carService;

    @Test
    public void giveEmptyModelUseFindByBrand() {
        Car testCar = new Car();
        testCar.setBrand(Brand.BMW);
        testCar.setId(1L);
        testCar.setModel("test");


        when(carRepository.findCarByBrand(any())).thenReturn(List.of(testCar));

        List<Car> cars = carService.getCar(Brand.BMW, "");

        assertThat(cars.get(0).getBrand().name())
                .isEqualTo(Brand.BMW.name());
    }

    @Test
    public void giveEmptyModelUseFindByBrandAndModel() {
        Car testCar = new Car();
        testCar.setBrand(Brand.TOYOTA);
        testCar.setId(1L);
        testCar.setModel("Corolla");


        when(carRepository.findCarByBrandAndModelOrderByBrandAsc(any(), any())).thenReturn(List.of(testCar));

        List<Car> cars = carService.getCar(Brand.TOYOTA, "Corolla");

        assertThat(cars.get(0).getBrand().name())
                .isEqualTo(Brand.TOYOTA.name());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void giveDataThatDoesNotExistThrowResourceNotFoundException() {
        Car testCar = new Car();
        testCar.setBrand(Brand.TOYOTA);
        testCar.setId(1L);
        testCar.setModel("Corolla");

        when(carRepository.findCarByBrandAndModelOrderByBrandAsc(any(), any())).thenThrow(new ResourceNotFoundException());

        carService.getCar(Brand.TOYOTA, "Corolla");
    }
}
