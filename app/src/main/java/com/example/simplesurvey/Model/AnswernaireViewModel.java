package com.example.simplesurvey.Model;

/**-----Communications-----*/
/* AnswernaireVM <--- DBactivity
 * AnswernaireVM <--- ConnectionInsert
 */

public class AnswernaireViewModel {

    //Declare db response variables
    private String questionNO; //int value, stored in String
    private String choice;
    private String answer;
    private String username;
    private String comments;

    //Declare outgoing communications
    //--nil--

    //Follows a SINGLETON design pattern
    private static AnswernaireViewModel INSTANCE = null;

    public AnswernaireViewModel(){}

    public static AnswernaireViewModel getInstance(){
        if (INSTANCE == null){
            INSTANCE = new AnswernaireViewModel();
        }
        return INSTANCE;
    }

    /*-----Setters & Getters-----*/
    public String getQuestionNO() {
        return questionNO;
    }

    public void setQuestionNO(String questionNO) {
        this.questionNO = questionNO;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
