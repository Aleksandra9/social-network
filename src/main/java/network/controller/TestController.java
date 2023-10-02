package network.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import network.entity.TestData;
import network.repository.TestDataRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/test")
public class TestController {
    private final TestDataRepository testDataRepository;

    @GetMapping
    public ResponseEntity<TestData> getUserInfo() {
        TestData entity = new TestData();
        entity.setCharValue("Test " + Calendar.getInstance().getTimeInMillis());

        return new ResponseEntity<>(testDataRepository.saveAndFlush(entity), HttpStatus.OK);
    }
}
