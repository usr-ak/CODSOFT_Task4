package com.codsoft.quizapp;

import java.io.Serializable;
import java.util.List;

public class Quiz implements Serializable {
    private String title;
    private List<Question> questions;

    public Quiz(String title, List<Question> questions) {
        this.title = title;
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
