package ca.unb.usermanagement.service.report;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * to implement Strategy Pattern
 */
@Service
public interface ReportGenerator {
    File generateReport(List<Object> data);
}
