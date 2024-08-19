package com.project.crm.service;

import com.project.crm.Dto.FeedbackEmotionsDto;
import com.project.crm.Dto.FeedbackTrendLineDto;
import com.project.crm.model.Feedback;
import com.project.crm.repository.FeedbackJpaRepository;
import com.project.crm.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.project.crm.utils.ValidationUtils.roundToTwoDecimal;

@Service
public class FeedbackService implements FeedbackRepository {

    @Autowired
    private FeedbackJpaRepository feedbackJpaRepository;

    private LocalDate getStartDate(String period) {
        LocalDate now = LocalDate.now();
        return switch (period) {
            case "6-months" -> now.minusMonths(6);
            case "1-year" -> now.minusYears(1);
            case "2-year" -> now.minusYears(2);
            case "3-year" -> now.minusYears(3);
            default -> throw new IllegalArgumentException("Invalid period");
        };
    }

    private String formatTimePeriod(LocalDate date, String period) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
        return date.format(formatter);
    }

    @Override
    public ResponseEntity<List<FeedbackTrendLineDto>> getFeedbackTrendLine(String period) {

        try {
            LocalDate startDate = getStartDate(period);
            Timestamp startTimestamp = Timestamp.valueOf(startDate.atStartOfDay());
            List<FeedbackTrendLineDto> feedbackTrendList = feedbackJpaRepository.findAllByCreatedAtAfter(startTimestamp)
                    .stream()
                    .collect(Collectors.groupingBy(
                            feedback -> formatTimePeriod(feedback.getCreatedAt().toLocalDateTime().toLocalDate(), period),
                            Collectors.averagingDouble(Feedback::getRating)))
                    .entrySet()
                    .stream()
                    .map(entry -> new FeedbackTrendLineDto(entry.getKey(), roundToTwoDecimal(entry.getValue())))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(feedbackTrendList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of());
        }
    }

    @Override
    public ResponseEntity<Object> getFeedbackStatistics() {
        try {
            List<Feedback> feedbacksList = feedbackJpaRepository.findAll();

            if(feedbacksList.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No feedback data found.");
            }

            long feedbackSize = feedbacksList.size();

            long goodCount = feedbacksList.stream().filter(f -> f.getRating() > 4).count();
            long badCount = feedbacksList.stream().filter(f -> f.getRating() < 3).count();
            long averageCount = feedbackSize - goodCount - badCount;

            double goodPercentage = (double) goodCount / feedbackSize * 100;
            double badPercentage = (double) badCount / feedbackSize * 100;
            double averagePercentage = (double) averageCount / feedbackSize * 100;

            return ResponseEntity.ok(new FeedbackEmotionsDto(roundToTwoDecimal(goodPercentage), roundToTwoDecimal(badPercentage), roundToTwoDecimal(averagePercentage)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
        }
    }
}
