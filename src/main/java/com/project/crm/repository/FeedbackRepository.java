package com.project.crm.repository;

import com.project.crm.Dto.FeedbackEmotionsDto;
import com.project.crm.Dto.FeedbackTrendLineDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository {
    ResponseEntity<List<FeedbackTrendLineDto>> getFeedbackTrendLine(String period);
    ResponseEntity<Object> getFeedbackStatistics();
}
