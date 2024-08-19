package com.project.crm.controller;

import com.project.crm.Dto.FeedbackEmotionsDto;
import com.project.crm.Dto.FeedbackTrendLineDto;
import com.project.crm.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://crm-client-eosin.vercel.app/")
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/get_trends/{period}")
    public ResponseEntity<List<FeedbackTrendLineDto>> getFeedbackTrendLine(@PathVariable("period") String period){
        return feedbackService.getFeedbackTrendLine(period);
    }

    @GetMapping("/statistics")
    public ResponseEntity<Object> getFeedbackStatistics(){
        return feedbackService.getFeedbackStatistics();
    }
}
