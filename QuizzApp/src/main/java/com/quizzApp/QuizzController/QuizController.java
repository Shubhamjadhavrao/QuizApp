package com.quizzApp.QuizzController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quizzApp.Model.Questions;
import com.quizzApp.Model.QuizzResult;
import com.quizzApp.QuizzeService.quizzService;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
	@Autowired
	private quizzService quizService;

	// 1. Start a new quiz session
	@PostMapping("/start")
	public ResponseEntity<Long> startQuizSession() {
		Long sessionId = quizService.startQuizSession();
		return ResponseEntity.ok(sessionId);
	}

	// 2. Get a random multiple-choice question
	@GetMapping("/questions")
	public ResponseEntity<Questions> getRandomQuestion(@RequestParam Long sessionId) {
		Questions question = quizService.getRandomQuestion(sessionId);
		return ResponseEntity.ok(question);
	}

	// 3. Submit an answer
	@PostMapping("/submit")
	public ResponseEntity<String> submitAnswer(@RequestParam Long sessionId, @RequestParam Long questionId,
			@RequestParam String answer) {
		boolean isCorrect = quizService.submitAnswer(sessionId, questionId, answer);
		return ResponseEntity.ok(isCorrect ? "Correct!" : "Incorrect!");
	}

	// 4. Get the total questions answered with details
	@GetMapping("/results")
	public ResponseEntity<QuizzResult> getQuizResults(@RequestParam Long sessionId) {
		QuizzResult results = quizService.getQuizResults(sessionId);
		return ResponseEntity.ok(results);
	}
}
