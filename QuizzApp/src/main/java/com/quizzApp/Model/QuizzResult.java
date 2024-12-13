package com.quizzApp.Model;

import java.util.Set;

public class QuizzResult {
	private Set<Long> answerQuestions;
	private int correctAnswers;
	private int incorrectAnswers;
	
	
	public QuizzResult(Set<Long> ansQuestions, int correctAnswers, int incorrectAnswers) {
		super();
		this.answerQuestions = ansQuestions;
		this.correctAnswers = correctAnswers;
		this.incorrectAnswers = incorrectAnswers;
	}
	public Set<Long> getAnsQuestions() {
		return answerQuestions;
	}
	public void setAnsQuestions(Set<Long> ansQuestions) {
		this.answerQuestions = ansQuestions;
	}
	public int getCorrectAnswers() {
		return correctAnswers;
	}
	public void setCorrectAnswers(int correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
	public int getIncorrectAnswers() {
		return incorrectAnswers;
	}
	public void setIncorrectAnswers(int incorrectAnswers) {
		this.incorrectAnswers = incorrectAnswers;
	}
	
	

}
