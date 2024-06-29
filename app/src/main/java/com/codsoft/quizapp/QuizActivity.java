package com.codsoft.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private Button submitAnswerButton;
    private Button backToHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        submitAnswerButton = findViewById(R.id.submitAnswerButton);
        backToHomeButton = findViewById(R.id.backToHomeButton);

        Quiz quiz = (Quiz) getIntent().getSerializableExtra("quiz");
        if (quiz != null) {
            questions = quiz.getQuestions();
        }

        displayNextQuestion();

        submitAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                if (currentQuestionIndex < questions.size() - 1) {
                    currentQuestionIndex++;
                    displayNextQuestion();
                } else {
                    showResults();
                }
            }
        });

        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void displayNextQuestion() {
        Question question = questions.get(currentQuestionIndex);
        questionTextView.setText(question.getQuestionText());
        optionsRadioGroup.removeAllViews();

        for (int i = 0; i < question.getOptions().size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(question.getOptions().get(i));
            radioButton.setId(i);
            optionsRadioGroup.addView(radioButton);
        }
        optionsRadioGroup.clearCheck();
        submitAnswerButton.setVisibility(View.VISIBLE);
        backToHomeButton.setVisibility(View.GONE);
    }

    private void checkAnswer() {
        int selectedOptionIndex = optionsRadioGroup.getCheckedRadioButtonId();
        Question question = questions.get(currentQuestionIndex);

        if (selectedOptionIndex == question.getCorrectOptionIndex()) {
            score++;
        }
    }

    private void showResults() {
        questionTextView.setText("You scored " + score + " out of " + questions.size());
        optionsRadioGroup.removeAllViews();
        submitAnswerButton.setVisibility(View.GONE);
        backToHomeButton.setVisibility(View.VISIBLE);
    }
}
