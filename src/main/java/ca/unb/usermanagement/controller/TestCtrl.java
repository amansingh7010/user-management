package ca.unb.usermanagement.controller;

import ca.unb.usermanagement.model.SampleData;
import ca.unb.usermanagement.service.test.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestCtrl {

    private final TestService testService;

    public TestCtrl(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test")
    List<SampleData> test() {
        return testService.getSomeData();
    }

}
