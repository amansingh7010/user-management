package ca.unb.usermanagement.service.report;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface ReportService {
    InputStream getReportByType(ReportType type) throws IOException;
}
