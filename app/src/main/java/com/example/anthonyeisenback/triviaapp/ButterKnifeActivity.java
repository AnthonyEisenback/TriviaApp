package com.example.anthonyeisenback.triviaapp;

import android.os.Bundle;

import butterknife.OnClick;


/**
 * Created by anthonyeisenback on 3/23/18.
 */

public class ButterKnifeActivity extends MainActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @OnClick(R.id.add_question_button)
    protected void onAddButtonClicked() {

    }

    @OnClick(R.id.delete_quiz_button)
    protected void onDeleteButtonClicked() {

    }

    @OnClick(R.id.take_quiz_button)
    protected void onTakeButtonClicked() {

    }

    @OnClick(R.id.submit_answer_creator_button)
    protected void onSubButtonClicked() {


    }
}