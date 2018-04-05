package com.example.anthonyeisenback.triviaapp;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements QuestionCreatorFragment.Callback, QuizFragment.QuizCallback {
    public static final String QUESTIONS_LIST = "questions_list";

    private QuizFragment quizFragment;
    private QuestionCreatorFragment questionCreatorFragment;
    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        questionList = new ArrayList<>();
    }

    @OnClick(R.id.add_question_button)
    protected void addQuestionClicked() {

        Fragment fragment = questionCreatorFragment.newInstance();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_holder, fragment).commit();
    }

    @OnClick(R.id.take_quiz_button)
    protected void startQuiz() {
        if (questionList.isEmpty()) {
            Toast.makeText(this, "Make a quiz please", Toast.LENGTH_SHORT).show();
        } else {
            Fragment fragment = quizFragment.newInstance();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_holder, fragment).commit();

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(QUESTIONS_LIST, (ArrayList<? extends Parcelable>) questionList);
            quizFragment.setArguments(bundle);
        }
    }

    @OnClick(R.id.delete_quiz_button)
    protected void deleteQuizClicked() {

        if (questionList.isEmpty()) {
            Toast.makeText(this, "There are no quizzes to delete", Toast.LENGTH_SHORT).show();
        } else {
            //todo alert dialouge to let user see if they want to delete quiz, logic to make it happen, and toast when deletion completes.

        }
    }


    @Override
    public void saveQuestion(Question question) {
        questionList.add(question);
        Toast.makeText(this, "Your Question has been added", Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().remove(questionCreatorFragment).commit();
    }


    @Override
    public void quizFinished(int correctAnswers) {
        //TODO - alert dialogue to tell user their score
    }
}
