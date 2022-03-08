package ca.unb.usermanagement.controller;

import ca.unb.usermanagement.service.report.ReportService;
import ca.unb.usermanagement.service.report.ReportType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController("/api/report")
public class ReportCtrl {
    private final ReportService reportService;

    public ReportCtrl(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/users")
    File getUsersReport(@RequestParam(defaultValue = "xlsx") String type) {
        return reportService.getReportByType(ReportType.valueOf(type));
    }

}
