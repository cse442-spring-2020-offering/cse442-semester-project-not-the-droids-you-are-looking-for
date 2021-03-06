package com.example.kiwiboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfHomeFragment extends Fragment {

    private RecyclerView activeRecyclerView;
    private RecyclerView queueRecyclerView;
    private QuestionAdapter activeAdapter;
    private QuestionAdapter queueAdapter;
    private ArrayList<Question> questions;
    private ArrayList<Question> activeQuestions;
    private ArrayList<Question> queueQuestions;
    private View professorView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        professorView =  inflater.inflate(R.layout.fragment_professor_home, container,false); // Inflate student_home fragment into StudentMain activity

        Button btn = (Button) professorView.findViewById(R.id.btnCreateQuestion);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfMain profmain = (ProfMain) getActivity();
                assert profmain != null;
                profmain.createQuestionMenu(v);
            }
        });

        // Find views from within root activity StudentMain.java
        activeRecyclerView = (RecyclerView) professorView.findViewById(R.id.rcyProfActiveQuestions);
        queueRecyclerView = (RecyclerView) professorView.findViewById(R.id.rcyQuestionQueue);

        activeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        queueRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        int courseindex = ProfData.getCurrentcourse();
        if (courseindex < 0){
            return professorView;
        }

        Course currentcourse = ProfData.getCourses().get(courseindex);
        currentcourse.refreshQuestionNumbers();
        questions = currentcourse.getQuestions();
        queueQuestions = currentcourse.getQueue();

        activeQuestions = new ArrayList<>();

        activeAdapter = new QuestionAdapter(getActivity(), activeQuestions);
        queueAdapter = new QuestionAdapter(getActivity(), queueQuestions);

        activeRecyclerView.setAdapter(activeAdapter);
        queueRecyclerView.setAdapter(queueAdapter);

        generateActiveQuestionList();
        activeAdapter.notifyDataSetChanged();
        queueAdapter.notifyDataSetChanged();

        return professorView;
    }

    @Override
    public void onResume() {
        super.onResume();

        int courseindex = ProfData.getCurrentcourse();
        if (courseindex < 0){
            activeQuestions = new ArrayList<>();
            queueQuestions = new ArrayList<>();

            activeAdapter = new QuestionAdapter(getActivity(), activeQuestions);
            queueAdapter = new QuestionAdapter(getActivity(), queueQuestions);

            activeRecyclerView.setAdapter(activeAdapter);
            queueRecyclerView.setAdapter(queueAdapter);

            return;
        }

        Course currentcourse = ProfData.getCourses().get(courseindex);
        currentcourse.refreshQuestionNumbers();
        questions = currentcourse.getQuestions();
        queueQuestions = currentcourse.getQueue();

        activeQuestions = new ArrayList<>();

        activeAdapter = new QuestionAdapter(getActivity(), activeQuestions);
        queueAdapter = new QuestionAdapter(getActivity(), queueQuestions);

        activeRecyclerView.setAdapter(activeAdapter);
        queueRecyclerView.setAdapter(queueAdapter);

        generateActiveQuestionList();
        activeAdapter.notifyDataSetChanged();
        queueAdapter.notifyDataSetChanged();
    }

    private void generateActiveQuestionList() {
        // Get the current active question(s)
        for (Question question : questions){
            if (question.isActive())
            activeQuestions.add(question);
        }
    }

}
