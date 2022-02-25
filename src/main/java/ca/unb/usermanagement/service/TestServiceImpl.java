package ca.unb.usermanagement.service;

import ca.unb.usermanagement.model.SampleData;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public List<SampleData> getSomeData() {
        return Arrays.asList(
                new SampleData().setId(1).setName("User_A"),
                new SampleData().setId(2).setName("User_B"),
                new SampleData().setId(3).setName("User_C")
        );
    }
}
