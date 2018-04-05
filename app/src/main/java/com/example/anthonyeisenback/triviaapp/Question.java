package com.example.anthonyeisenback.triviaapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anthonyeisenback on 4/2/18.
 */

public class Question implements Parcelable{
    private String question;
    private String correctAnswer;
    private String incorrectAnswerOne;
    private String incorrectAnswerTwo;
    private String incorrectAnswerThree;

    public Question(String question, String correctAnswer, String incorrectAnswerOne, String incorrectAnswerTwo, String incorrectAnswerThree) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswerOne = incorrectAnswerOne;
        this.incorrectAnswerTwo = incorrectAnswerTwo;
        this.incorrectAnswerThree = incorrectAnswerThree;
    }

    protected Question(Parcel in) {
        question = in.readString();
        correctAnswer = in.readString();
        incorrectAnswerOne = in.readString();
        incorrectAnswerTwo = in.readString();
        incorrectAnswerThree = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getIncorrectAnswerOne() {
        return incorrectAnswerOne;
    }

    public String getIncorrectAnswerTwo() {
        return incorrectAnswerTwo;
    }

    public String getIncorrectAnswerThree() {
        return incorrectAnswerThree;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(correctAnswer);
        dest.writeString(incorrectAnswerOne);
        dest.writeString(incorrectAnswerTwo);
        dest.writeString(incorrectAnswerThree);
    }
}