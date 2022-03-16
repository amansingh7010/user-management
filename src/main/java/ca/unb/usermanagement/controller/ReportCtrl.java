package ca.unb.usermanagement.controller;

import ca.unb.usermanagement.service.report.ReportService;
import ca.unb.usermanagement.service.report.ReportType;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController("/api/report")
public class ReportCtrl {
    private final ReportService reportService;

    public ReportCtrl(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/users")
    ResponseEntity<Resource> getUsersReport(@RequestParam(defaultValue = "xlsx") String type) throws IOException {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + UUID.randomUUID() + "." + type)
                .contentType(type.equals("xlsx") ? MediaType.parseMediaType("application/vnd.ms-excel") : MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(reportService.getReportByType(ReportType.valueOf(type))));
    }

}
