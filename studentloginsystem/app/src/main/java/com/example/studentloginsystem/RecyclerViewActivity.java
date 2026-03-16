package com.example.studentloginsystem;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private List<ExamItemModel> examItemList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_view);

        populateExamData();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ExamItemAdapter examItemAdapter = new ExamItemAdapter(this, examItemList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(examItemAdapter);



    }

    private void populateExamData() {


        examItemList.add(new ExamItemModel(
                "Mathematics Midterm",
                "March 20, 2026",
                "09:00 AM - 11:00 AM",
                R.drawable.calendar_clock_24px,
                R.drawable.grading_24px
        ));

        examItemList.add(new ExamItemModel(
                "Physics Laboratory Quiz",
                "March 22, 2026",
                "01:00 PM - 02:30 PM",
                R.drawable.calendar_clock_24px,
                R.drawable.grading_24px
        ));

        examItemList.add(new ExamItemModel(
                "Computer Science Final",
                "April 05, 2026",
                "10:00 AM - 01:00 PM",
                R.drawable.calendar_clock_24px,
                R.drawable.grading_24px
        ));

        examItemList.add(new ExamItemModel(
                "History Essay Defense",
                "April 12, 2026",
                "03:00 PM - 04:00 PM",
                R.drawable.calendar_clock_24px,
                R.drawable.grading_24px
        ));

        examItemList.add(new ExamItemModel(
                "Chemistry Practical",
                "April 18, 2026",
                "08:00 AM - 12:00 PM",
                R.drawable.calendar_clock_24px,
                R.drawable.grading_24px
        ));
    }
}