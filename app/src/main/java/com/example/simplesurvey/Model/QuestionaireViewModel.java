package com.example.simplesurvey.Model;

import android.util.Log;

/**-----Communications-----*/
/*QuestionaireVM ---> QnRepository
* QuestionaireVM <--> DBactivity
*/

public class QuestionaireViewModel {
    //Declaring variables for each question set
    private int questionNo;
    private int totalQn;
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String time;
    private String Description;

    private static String[] questionSet;

    /*-----Constructors-----*/
    public QuestionaireViewModel(){

    }

    public QuestionaireViewModel(String[] questionSet){
        this.questionSet = questionSet;
    }

    /*-----Setters-----*/
    public void setQuestionNo(int index){
        //Getting a part (questionNO) of the string, via substring, at respective index of questionSet
        int indexEnd = questionSet[index].indexOf('.');
        questionNo = Integer.parseInt(questionSet[index].substring(0,indexEnd));
    }

    public void setQn(int index) {
        //Getting a part (question) of the string, via substring, at respective index of questionSet
        int indexStart = questionSet[index].indexOf(" ");
        int indexEnd = questionSet[index].indexOf("A.");
        question = questionSet[index].substring(indexStart, indexEnd);
    }

    public void setAnswerA(int index) {
        //Getting a part (answerA) of the string, via substring, at respective index of questionSet
        int indexStart = questionSet[index].indexOf("A.");
        int indexEnd = questionSet[index].indexOf("B.");
        answerA = questionSet[index].substring(indexStart, indexEnd);
    }

    public void setAnswerB(int index) {
        //Getting a part (answerB) of the string, via substring, at respective index of questionSet
        int indexStart = questionSet[index].indexOf("B.");
        int indexEnd = questionSet[index].indexOf("C.");
        answerB = questionSet[index].substring(indexStart, indexEnd);
    }

    public void setAnswerC(int index) {
        //Getting a part (answerC) of the string, via substring, at respective index of questionSet
        int indexStart = questionSet[index].indexOf("C.");
        int indexEnd = questionSet[index].indexOf("D.");
        answerC = questionSet[index].substring(indexStart, indexEnd);
    }

    public void setAnswerD(int index) {
        //Getting a part (answerD) of the string, via substring, at respective index of questionSet
        int indexStart = questionSet[index].indexOf("D.");
        int indexEnd = questionSet[index].indexOf("Time:");
        answerD = questionSet[index].substring(indexStart, indexEnd);
    }

    /*-----Getters-----*/
    public int getQuestionNo(){
        return questionNo;
    }

    public String getQuestion(){
        return question;
    }

    public String getAnswerA(){
        return answerA;
    }

    public String getAnswerB(){
        return answerB;
    }

    public String getAnswerC(){
        return answerC;
    }

    public String getAnswerD(){
        return answerD;
    }

    public int getTotalQns(){
        totalQn = questionSet.length;
        return totalQn;
    }

    public void logQuestionSet(){
        for(String qn:questionSet) {
            Log.i("QuestionSet", qn);
        }
    }
}
