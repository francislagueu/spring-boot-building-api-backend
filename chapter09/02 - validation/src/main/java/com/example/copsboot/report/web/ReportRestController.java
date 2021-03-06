package com.example.copsboot.report.web;

import com.example.copsboot.infrastructure.security.ApplicationUserDetails;
import com.example.copsboot.report.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

//tag::class[]
@RestController
@RequestMapping("/api/reports")
public class ReportRestController {
    private final ReportService service;

    public ReportRestController(ReportService service) {
        this.service = service;
    }

    //tag::create-report-method-signature[]
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReportDto createReport(@AuthenticationPrincipal ApplicationUserDetails userDetails,
                                  @Valid CreateReportParameters parameters) {
        //end::create-report-method-signature[]
        return ReportDto.fromReport(service.createReport(userDetails.getUserId(),
                                                         parameters.getDateTime(),
                                                         parameters.getDescription(),
                                                         parameters.getImage()));
    }
}
//end::class[]
