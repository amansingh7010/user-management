package ca.unb.usermanagement.service.report;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ReportServiceImpl implements ReportService {

    public ReportServiceImpl() {
    }

    @Override
    public File getReportByType(ReportType type) {
        ReportGenerator reportGenerator;
        if (type == ReportType.PDF) {
            reportGenerator = new PDFReportGenerator();
        } else if (type == ReportType.XLSX) {
            reportGenerator = new XLSXReportGenerator();
        } else {
            return null;
        }

        return reportGenerator.generateReport(null);
    }
}
