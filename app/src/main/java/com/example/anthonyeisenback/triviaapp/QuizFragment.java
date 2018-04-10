package com.example.anthonyeisenback.triviaapp;

import android.app.Fragment;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.anthonyeisenback.triviaapp.MainActivity.QUESTIONS_LIST;

/**
 * Created by anthonyeisenback on 4/4/18.
 */

public class QuizFragment extends android.support.v4.app.Fragment {

    @BindView(R.id.question_quiz_textview)
    protected TextView question;
    @BindView(R.id.question_one_quiz_button)
    protected Button questionOneAnswer;
    @BindView(R.id.question_two_quiz_button)
    protected Button questionTwoAnswer;
    @BindView(R.id.question_three_quiz_button)
    protected Button questionThreeAnswer;
    @BindView(R.id.question_four_quiz_button)
    protected Button questionFourAnswer;
    @BindView(R.id.submit_answer_quiz_button)
    protected Button submitAnswerButton;
    private QuizCallback quizCallback;
    private List<Question> questionsList;
    private Question questiona;
    private int questionPosition = 0;
    private int correctAnswers = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static QuizFragment newInstance() {

        Bundle args = new Bundle();

        QuizFragment fragment = new QuizFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        questionsList = new ArrayList<>();
        questionsList = getArguments().getParcelableArrayList(QUESTIONS_LIST);
        populateQuizContent();
    }

    private void populateQuizContent() {
        questiona = questionsList.get(questionPosition);
        question.setText(questiona.getQuestion());

        List<Button> buttonList = new ArrayList<>();
        buttonList.add(questionOneAnswer);
        buttonList.add(questionTwoAnswer);
        buttonList.add(questionThreeAnswer);
        buttonList.add(questionFourAnswer);

        List<String> possibleAnswers = new ArrayList<>();
        possibleAnswers.add(questiona.getCorrectAnswer());
        possibleAnswers.add(questiona.getIncorrectAnswerOne());
        possibleAnswers.add(questiona.getIncorrectAnswerTwo());
        possibleAnswers.add(questiona.getIncorrectAnswerThree());

        for (Button button : buttonList) {
            int random = (int) (Math.random() * possibleAnswers.size() - 1);

            button.setText(possibleAnswers.get(random));
            possibleAnswers.remove(random);

        }


    }

    //TODO - disable incorrect buttons if button selected is wrong then reenable buttons when next button is clicked
    @OnClick(R.id.question_one_quiz_button)
    protected void buttonOneClicked() {
        checkAnswer(questionOneAnswer.getText().toString());

    }

    @OnClick(R.id.question_two_quiz_button)
    protected void buttonTwoClicked() {
        checkAnswer(questionTwoAnswer.getText().toString());

    }

    @OnClick(R.id.question_three_quiz_button)
    protected void buttonThreeClicked() {
        checkAnswer(questionThreeAnswer.getText().toString());

    }

    @OnClick(R.id.question_four_quiz_button)
    protected void buttonFourClicked() {
        checkAnswer(questionFourAnswer.getText().toString());

    }

    private void checkAnswer(String answer) {
        questionPosition++;

        if (questiona.getCorrectAnswer().equals(answer)) {
            question.setText(R.string.correct);
            correctAnswers++;
        } else {
            question.setText(R.string.wrong_answer+ getString(R.string.correct_answer_teller, questiona.getCorrectAnswer()));
            disableButtons();
        }

    }

    private void nextButtonClicked() {
        enableButtons();

        if (questionPosition <= questionsList.size()-1) {
            populateQuizContent();

        } else {
            quizCallback.quizFinished(correctAnswers);
        }

    }

    private void disableButtons() {
        questionOneAnswer.setEnabled(false);
        questionTwoAnswer.setEnabled(false);
        questionThreeAnswer.setEnabled(false);
        questionFourAnswer.setEnabled(false);
    }

    private void enableButtons() {
        questionOneAnswer.setEnabled(true);
        questionTwoAnswer.setEnabled(true);
        questionThreeAnswer.setEnabled(true);
        questionFourAnswer.setEnabled(true);
    }


    public void attachView(QuizCallback quizCallback) {
        this.quizCallback = quizCallback;

    }

    public interface QuizCallback {
        void quizFinished(int correctAnswers);



    }
}
