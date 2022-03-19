package ca.unb.usermanagement.service.test;

import ca.unb.usermanagement.model.SampleData;
import ca.unb.usermanagement.payload.response.ListResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public ListResponse getSomeData() {
        return new ListResponse(Arrays.asList(
                new SampleData().setId(1).setName("User_A"),
                new SampleData().setId(2).setName("User_B"),
                new SampleData().setId(3).setName("User_C")
        ));
    }
}
