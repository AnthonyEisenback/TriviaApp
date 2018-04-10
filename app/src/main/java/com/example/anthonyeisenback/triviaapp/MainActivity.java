package com.example.anthonyeisenback.triviaapp;

import android.content.DialogInterface;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

        questionCreatorFragment = questionCreatorFragment.newInstance();
        questionCreatorFragment.attachView(this);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_holder, questionCreatorFragment).addToBackStack("add_questions").commit();
    }

    @OnClick(R.id.take_quiz_button)
    protected void startQuiz() {
        if (questionList.isEmpty()) {
            Toast.makeText(this, "Make a quiz please", Toast.LENGTH_SHORT).show();
        } else {

            quizFragment = quizFragment.newInstance();
            quizFragment.attachView(this);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_holder, quizFragment).addToBackStack("start_quiz").commit();

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
            AlertDialog.Builder bye = new AlertDialog.Builder(this);
            bye.setMessage("Would you like to delete this quiz?");
            bye.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    questionList.clear();
                    Toast.makeText(MainActivity.this, "Your questions have been cleared", Toast.LENGTH_SHORT).show();
                }
            });
            bye.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            bye.create();
            bye.show();

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

        getSupportFragmentManager().beginTransaction().remove(quizFragment).commit();
        AlertDialog.Builder correctDialogue = new AlertDialog.Builder(this);
        correctDialogue.setMessage(getString(R.string.correct_questions, correctAnswers));
        correctDialogue.setTitle("Thanks for playing");
        correctDialogue.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = correctDialogue.create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder bye = new AlertDialog.Builder(this);
        bye.setMessage("Would you like to exit?");
        bye.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        bye.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        bye.setNeutralButton("Go back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStackImmediate();
                }

            }


        });
        bye.create();
        bye.show();


    }
}
