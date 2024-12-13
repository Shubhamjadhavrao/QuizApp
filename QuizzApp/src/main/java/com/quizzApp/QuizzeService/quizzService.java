package com.quizzApp.QuizzeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizzApp.Model.Questions;
import com.quizzApp.Model.QuizzResult;
import com.quizzApp.Model.QuizzSession;
import com.quizzApp.QuizzRepository.QuestionRepo;

@Service
public class quizzService {
	@Autowired
	private QuestionRepo questionRepo;

	private final Map<Long, QuizzSession> sessions = new HashMap<>();
	private long sessionIdCounter = 1;

	public Long startQuizSession() {
		Long sessionId = sessionIdCounter++;
		sessions.put(sessionId, new QuizzSession(sessionId));
		return sessionId;
	}

	public Questions getRandomQuestion(Long sessionId) {
		validateSession(sessionId);
		QuizzSession session = sessions.get(sessionId);

		List<Questions> questions = questionRepo.findAll();
		List<Questions> unansweredQuestions = new ArrayList<>();
		for (Questions q : questions) {
			if (!session.getAnswerQuestions().contains(q.getId())) {
				unansweredQuestions.add(q);
			}
		}

		if (unansweredQuestions.isEmpty()) {
			throw new RuntimeException("No more questions available");
		}

		return unansweredQuestions.get(new Random().nextInt(unansweredQuestions.size()));
	}

	public boolean submitAnswer(Long sessionId, Long questionId, String answer) {
		validateSession(sessionId);
		Questions question = questionRepo.findById(questionId).get();
		boolean isCorrect = question.getCorrectAnswer().equalsIgnoreCase(answer);
		QuizzSession session = sessions.get(sessionId);
		session.addSubmission(questionId, isCorrect);
		return isCorrect;
	}

	public QuizzResult getQuizResults(Long sessionId) {
		validateSession(sessionId);
		QuizzSession session = sessions.get(sessionId);
		return new QuizzResult(session.getAnswerQuestions(), session.getCorrectAnswer(), session.getIncorrectAnswer());
	}

	private void validateSession(Long sessionId) {
		if (!sessions.containsKey(sessionId)) {
			throw new RuntimeException("Session not found");
		}
	}
}
