package ca.unb.usermanagement.service.report;

import ca.unb.usermanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ReportServiceImpl implements ReportService {

    private final UserRepository userRepository;

    public ReportServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public InputStream getReportByType(ReportType type) throws IOException {
        ReportGenerator reportGenerator;
        if (type == ReportType.PDF) {
            reportGenerator = new PDFReportGenerator();
        } else if (type == ReportType.XLSX) {
            reportGenerator = new XLSXReportGenerator();
        } else {
            return null;
        }

        return reportGenerator.generateReport(userRepository.findAll());
    }
}
