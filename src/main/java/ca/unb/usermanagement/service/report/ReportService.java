package ca.unb.usermanagement.service.report;

import java.io.File;

public interface ReportService {
    File getReportByType(ReportType type);
}
