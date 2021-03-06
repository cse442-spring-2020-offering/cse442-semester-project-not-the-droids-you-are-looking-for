package com.example.kiwiboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentSAQuestionView extends AppCompatActivity {
    private TextView answer;
    private TextView description;
    private Question question;
    private Toolbar toolbar;
    private TextView getAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sa_question_view);
        question = getQuestion();

        answer = (TextView) findViewById(R.id.Stud_SA_Quest_View_Answer_TXT);
        if(question.getTextresponse() == null)
            answer.setText("You never answered this question.");
        else
            answer.setText(question.getTextresponse());

        toolbar = (Toolbar)findViewById(R.id.Stud_SA_QuestView_Toolbar);
        toolbar.setTitle("Question: " + (StudentData.getLastclickedquestion() + 1));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });

        description = (TextView) findViewById(R.id.Stud_SA_Quest_View_Desp_TXT);
        description.setText(question.getDescription());
        getAnswer = (TextView) findViewById(R.id.stud_SA_Quest_View_Answer_CorrectTXT);
        getAnswer.setText( question.getTextanswer());
    }
    private Question getQuestion(){
        int courseindex = StudentData.getCurrentcourse();
        if(courseindex < 0) {
            return null;
        }
        Course currentcourse = StudentData.getCourses().get(courseindex);

        int qindex = StudentData.getLastclickedquestion();
        if (qindex < 0)
            return null;
        ArrayList<Question> questions = currentcourse.getQuestions();
        return questions.get(qindex);
    }
    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        //ActionBar actionBar = getActionBar();
        //actionBar.hide();
    }
}
