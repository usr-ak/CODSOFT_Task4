package com.codsoft.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Quiz> quizList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quizList = new ArrayList<>();
        quizList.add(new Quiz("General Knowledge", Arrays.asList(
                new Question("What is the capital of France?", Arrays.asList("Berlin", "Madrid", "Paris", "Rome"), 2),
                new Question("What is 2 + 2?", Arrays.asList("3", "4", "5", "6"), 1),
                new Question("What is the capital of Japan?", Arrays.asList("Tokyo", "Beijing", "Seoul", "Bangkok"), 0),
                new Question("Who painted the Mona Lisa?", Arrays.asList("Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Michelangelo"), 1),
                new Question("What is the largest planet in our solar system?", Arrays.asList("Venus", "Earth", "Jupiter", "Saturn"), 2)
        )));
        quizList.add(new Quiz("Science", Arrays.asList(
                new Question("Which planet is known as the Red Planet?", Arrays.asList("Earth", "Mars", "Jupiter", "Saturn"), 1),
                new Question("Which element has the chemical symbol 'O'?", Arrays.asList("Oxygen", "Gold", "Silver", "Helium"), 0),
                new Question("What is the chemical symbol for water?", Arrays.asList("Wa", "Wo", "H2O", "Wa2"), 2),
                new Question("Which gas is most abundant in Earth's atmosphere?", Arrays.asList("Nitrogen", "Oxygen", "Carbon dioxide", "Helium"), 0),
                new Question("What is the smallest bone in the human body?", Arrays.asList("Tibia", "Radius", "Femur", "Stapes"), 3)
        )));

        Button startRandomQuizButton = findViewById(R.id.startRandomQuizButton);
        Button startGeneralKnowledgeQuizButton = findViewById(R.id.startGeneralKnowledgeQuizButton);
        Button startScienceQuizButton = findViewById(R.id.startScienceQuizButton);

        startRandomQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomIndex = (int) (Math.random() * quizList.size());
                Quiz randomQuiz = quizList.get(randomIndex);
                startQuizActivity(randomQuiz);
            }
        });

        startGeneralKnowledgeQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quiz generalKnowledgeQuiz = quizList.get(0);
                startQuizActivity(generalKnowledgeQuiz);
            }
        });

        startScienceQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quiz scienceQuiz = quizList.get(1);
                startQuizActivity(scienceQuiz);
            }
        });
    }

    private void startQuizActivity(Quiz quiz) {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        intent.putExtra("quiz", (Serializable) quiz);
        startActivity(intent);
    }
}
