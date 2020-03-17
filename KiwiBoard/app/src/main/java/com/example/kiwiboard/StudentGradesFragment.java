package com.example.kiwiboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentGradesFragment extends Fragment {
    @Nullable
@Override
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_student_grades,container,false);

        int courseindex = ProfData.getCurrentcourse();
        Course currentcourse = ProfData.getCourses().get(courseindex);
        ArrayList<Question> questions = currentcourse.getQuestions();
        Question question;
        double pointsreceived, percentage;
        int maxpoints;
        ArrayList<Double> averages = currentcourse.calculateAverages();

        // Class Average
        TextView avgTotal=(TextView)rootView.findViewById(R.id.txtStuClassavg);
        double Classaverage = currentcourse.calculateClassAverage();
        avgTotal.setText(Classaverage+"%");
        ProgressBar avgProgress = (ProgressBar) rootView.findViewById(R.id.prgStuClassavg);
        avgProgress.setProgress((int) Classaverage);

        // Table layout

        TableLayout gradesTable;
        gradesTable = (TableLayout) rootView.findViewById(R.id.tblStudentGrades);



        for (int i = 0; i < questions.size(); i++) {

            TableRow tr = new TableRow(getActivity());

            tr.setId(i);
            //tr.setBackgroundResource(R.color.colorPrimaryDark);
            tr.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

            question = questions.get(i);
            pointsreceived = question.calculateScore();
            maxpoints = question.getMaxpoints();
            percentage = question.calculatePercentage();

            //TEXTVIEWS********
            TextView qnum = new TextView(getActivity());
            qnum.setText("" + question.getQuestionnumber());
            qnum.setId(i);
            //qnum.setTextColor(Color.WHITE);
            qnum.setTextSize(20);
            qnum.setPadding(5, 5, 80, 5);
            tr.addView(qnum);

            TextView numpoints = new TextView(getActivity());
            numpoints.setText("" + pointsreceived);
            numpoints.setId(i+i);
            //numpoints.setTextColor(Color.WHITE);
            numpoints.setTextSize(20);
            numpoints.setPadding(10, 5, 80, 0);
            tr.addView(numpoints);

            TextView totalpoints = new TextView(getActivity());
            totalpoints.setText("" + maxpoints);
            totalpoints.setId(i+i);
            //totalpoints.setTextColor(Color.WHITE);
            totalpoints.setTextSize(20);
            totalpoints.setPadding(10, 5, 80, 0);
            tr.addView(totalpoints);

            TextView percent = new TextView(getActivity());
            percent.setText("" + percentage);
            percent.setId(i+i);
            //percent.setTextColor(Color.WHITE);
            percent.setTextSize(20);
            percent.setPadding(10, 5, 80, 0);
            tr.addView(percent);


            ProgressBar tv3 = new ProgressBar(new ContextThemeWrapper(getActivity(), R.style.horizontalProgressSmall), null,0);
            tv3.setProgress((int)percentage);
            tv3.setId(i+i+i);
            tv3.setMinimumWidth(50);
            tv3.setPadding(25, 20, 5, 10);
            tr.addView(tv3);

            gradesTable.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        }


        return rootView;
}
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Variable initializations here, excluding View objects
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        // Initialise View objects here
    }
}