package com.project.crm.repository;

import com.project.crm.Dto.FeedbackEmotionsDto;
import com.project.crm.model.Feedback;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface FeedbackJpaRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findAllByCreatedAtAfter(Timestamp timestamp);
    @Modifying
    @Transactional
    @Query("DELETE FROM Feedback f WHERE f.customer.id = :customerId")
    void deleteByCustomerId(@Param("customerId") Integer customerId);
}
