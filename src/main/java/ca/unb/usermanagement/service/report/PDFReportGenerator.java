package ca.unb.usermanagement.service.report;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class PDFReportGenerator implements ReportGenerator {
    @Override
    public File generateReport(List<Object> data) {
        return null;
    }
}
