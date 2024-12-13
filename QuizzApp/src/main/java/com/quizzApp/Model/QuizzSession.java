package com.quizzApp.Model;

import java.util.HashSet;
import java.util.Set;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;

public class QuizzSession<Questions> {
	private Long sessionId;
	private Set<Long> answerQuestions = new HashSet();
	private int correctAnswer = 0;
	private int incorrectAnswer = 0;

	public QuizzSession(Long sessionId) {
		super();
		this.sessionId = sessionId;
	}

	public void addQuestion(Questions questions) {
		answerQuestions.add(sessionId);
	}

	public void addSubmission(Long questionId, boolean isCorrect) {
		if (isCorrect) {
			correctAnswer++;
		} else {
			incorrectAnswer++;
		}

	}

	public Set<Long> getAnswerQuestions() {
		return answerQuestions;
	}

	public void setAnswerQuestions(Set<Long> answerQuestions) {
		this.answerQuestions = answerQuestions;
	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public int getIncorrectAnswer() {
		return incorrectAnswer;
	}

}
