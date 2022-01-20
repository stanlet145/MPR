package pl.edu.pjatk.cardictionary.integration;

import io.vavr.control.Try;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.edu.pjatk.cardictionary.controller.CarController;
import pl.edu.pjatk.cardictionary.model.Brand;
import pl.edu.pjatk.cardictionary.model.Car;
import pl.edu.pjatk.cardictionary.service.CarService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class CarControllerTest {


    private MockMvc mockMvc;

    @Mock
    CarService carService;

    @InjectMocks
    private CarController carController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(carController)
                .build();
    }

    @Test
    public void testCarController() throws Exception {
        Car testCar = new Car();
        testCar.setBrand(Brand.BMW);
        testCar.setId(1L);
        testCar.setModel("test");

        when(carService.getAllCars()).thenReturn(Try.of(() -> List.of(testCar)));
        mockMvc.perform(get("/cars").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].brand", Matchers.is(Brand.BMW.name())))
                .andExpect(jsonPath("$[0].model", Matchers.is("test")));
    }
}
