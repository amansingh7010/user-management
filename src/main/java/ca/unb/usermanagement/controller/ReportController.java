package ca.unb.usermanagement.controller;

import ca.unb.usermanagement.service.report.ReportService;
import ca.unb.usermanagement.service.report.ReportType;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/report")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    ResponseEntity<Resource> getUsersReport(@RequestParam(defaultValue = "XLSX") String type) throws IOException {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + UUID.randomUUID() + "." + type)
                .contentType(type.equals("XLSX") ? MediaType.parseMediaType("application/vnd.ms-excel") : MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(reportService.getReportByType(ReportType.valueOf(type))));
    }

}
