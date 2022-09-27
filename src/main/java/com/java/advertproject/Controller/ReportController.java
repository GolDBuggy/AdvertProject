package com.java.advertproject.Controller;

import com.java.advertproject.Dto.ReportDto;
import com.java.advertproject.Model.Report;
import com.java.advertproject.Service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/all")
    public ResponseEntity<List<ReportDto>> getAll(){
        return ResponseEntity.ok(reportService.all());
    }
}
