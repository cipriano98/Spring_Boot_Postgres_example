package com.zeta.postgres.repository;

import java.util.List;

import com.zeta.postgres.model.Answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository < Answer, Long > {
    List < Answer > findByQuestionId( Long questionId );
}