package com.example.anthonyeisenback.triviaapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.security.auth.callback.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionCreatorFragment extends Fragment {

    @BindView(R.id.correct_answer_editText)
    protected EditText correctAnswer;
    @BindView(R.id.incorrect1_edittext)
    protected EditText incorrectOne;
    @BindView(R.id.incorrect2_edittext)
    protected EditText incorrectTwo;
    @BindView(R.id.incorrect3_edittext)
    protected EditText incorrectThree;
    @BindView(R.id.the_question_creator_edittext)
    protected EditText theQuestion;
    @BindView(R.id.submit_answer_creator_button)
    protected Button submitQuestion;
    private Callback callback;

    public static QuestionCreatorFragment newInstance() {

        QuestionCreatorFragment fragment = new QuestionCreatorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_question_creator, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.submit_answer_creator_button)
    protected void saveQuestionsClicked() {

        String questionTitle = theQuestion.getText().toString();
        String correctAns = correctAnswer.getText().toString();
        String incorrectAnswer = incorrectOne.getText().toString();
        String incorrectAnswerTwo = incorrectTwo.getText().toString();
        String incorrectAnswerThree = incorrectThree.getText().toString();

        if (TextUtils.isEmpty(questionTitle) || TextUtils.isEmpty(correctAns) || TextUtils.isEmpty(incorrectAnswer) || TextUtils.isEmpty(incorrectAnswerTwo) || TextUtils.isEmpty(incorrectAnswerThree)) {
            Toast.makeText(getContext(), "Please fill out all the fields", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Question question = new Question(questionTitle, correctAns, incorrectAnswer, incorrectAnswerTwo, incorrectAnswerThree);

            callback.saveQuestion(question);

        }

    }

    public interface Callback {
        void saveQuestion(Question question);

    }

    public void attachView(Callback callback) {
        this.callback = callback;

    }


}
