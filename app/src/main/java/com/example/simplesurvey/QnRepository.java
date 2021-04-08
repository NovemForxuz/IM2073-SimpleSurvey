/*-----DATA LAYER-----*/
//Where UI data will be stored

package com.example.simplesurvey;

import com.example.simplesurvey.Model.QuestionaireViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**-----Communications-----*/
/*QnRepository ---> ConnectionHelper
* QnRepository <--- QuestionaireVM
*/

public class QnRepository {

    private static QnRepository INSTANCE = null;

    private String[] questionaire;      //store question queries
    private String[] answernaire;

    private QuestionaireViewModel qvm;

    public void UIViewModel(){}

    //Follows a SINGLETON design pattern, only one instance of QnRepository class
    public static QnRepository getInstance(){
        if(INSTANCE == null)
            INSTANCE = new QnRepository();

        return INSTANCE;
    }

    //Reformat questions query (JSON) into String (for readability, debug)
    public void setQuestionaire(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] questionaire = new String[jsonArray.length()];
        for (int i=0; i< jsonArray.length(); i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            //Local db format
            /*questionaire[i] = obj.getString("questionNO") + ". "
                    + obj.getString("question")
                    + "\nA. " + obj.getString("answerA")
                    + "\nB. " + obj.getString("answerB")
                    + "\nC. " + obj.getString("answerC")
                    + "\nD. " + obj.getString("answerD")
                    + "\nTime: " + obj.getString("time")
                    + "\n" + obj.getString("Description");*/

            //JQ db format
            questionaire[i] = obj.getString("questionNO") + ". "
                    + obj.getString("question")
                    + "\nA. " + obj.getString("anwsera")
                    + "\nB. " + obj.getString("anwserb")
                    + "\nC. " + obj.getString("anwserc")
                    + "\nD. " + obj.getString("anwserd")
                    + "\nTime: " + obj.getString("time")
                    + "\nDesc: " + obj.getString("Description");
        }
        this.questionaire = questionaire;
        qvm = new QuestionaireViewModel(questionaire);
    }

    public String[] getQuestionaire() {
        return questionaire;
    }

    public void setQuestionaire(String[] questionaire) {
        this.questionaire = questionaire;
    }

    public String[] getAnswernaire() {
        return answernaire;
    }

    public void setAnswernaire(String[] answernaire) {
        this.answernaire = answernaire;
    }


}
