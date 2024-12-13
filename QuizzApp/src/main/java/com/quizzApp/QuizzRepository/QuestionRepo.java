package com.quizzApp.QuizzRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizzApp.Model.Questions;

@Repository
public interface QuestionRepo extends JpaRepository<Questions, Long> {

}
