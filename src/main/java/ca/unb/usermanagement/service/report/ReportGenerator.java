package ca.unb.usermanagement.service.report;

import ca.unb.usermanagement.model.User;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

/**
 * to implement Strategy Pattern
 */
@Service
public interface ReportGenerator {
    InputStream generateReport(List<User> data) throws IOException;
}
