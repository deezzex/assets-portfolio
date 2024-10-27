package com.deezzex.fiat.ingress.controller;

import com.deezzex.fiat.dto.report.CreateReportDto;
import com.deezzex.fiat.dto.report.GetReportDto;
import com.deezzex.fiat.entity.Report;
import com.deezzex.fiat.mapper.ReportMapper;
import com.deezzex.fiat.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;
    private final ReportMapper reportMapper;

    @GetMapping("/{reportId}")
    public ResponseEntity<GetReportDto> getReport(@PathVariable Integer reportId) {
        Report report = reportService.getReport(reportId);
        GetReportDto reportDto = reportMapper.toGetReportDto(report);

        return ResponseEntity.ok(reportDto);
    }

    @PostMapping
    public ResponseEntity<GetReportDto> createReport(@RequestBody CreateReportDto createReportDto) {
        Report report = reportService.createReport(createReportDto);
        GetReportDto reportDto = reportMapper.toGetReportDto(report);

        return ResponseEntity.status(HttpStatus.CREATED).body(reportDto);
    }
}
