package pl.edu.pjatk.cardictionary.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjatk.cardictionary.model.Brand;
import pl.edu.pjatk.cardictionary.model.Car;
import pl.edu.pjatk.cardictionary.service.CarService;

import java.util.List;
import java.util.function.Supplier;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returns cars by brand and model"),
            @ApiResponse(responseCode = "500", description = "something went wrong")
    })
    public ResponseEntity<List<Car>> getCar(@RequestParam(name = "brand") Brand brand, @RequestParam(name = "model") String model) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(carService.getCar(brand, model)
                        .getOrElseThrow((Supplier<RuntimeException>) RuntimeException::new));
    }

    @GetMapping("/cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returns list of cars"),
            @ApiResponse(responseCode = "500", description = "something went wrong")
    })
    public ResponseEntity<List<Car>> getCars() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(carService.getAllCars()
                        .getOrElseThrow((Supplier<RuntimeException>) RuntimeException::new));
    }

    @DeleteMapping("car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "deletes car by brand and model when found")
    })
    public ResponseEntity<?> removeCar(@RequestParam(name = "brand") Brand brand, @RequestParam(name = "model") String model) {
        carService.removeCar(brand, model);
        return ResponseEntity.ok().build();
    }
}
