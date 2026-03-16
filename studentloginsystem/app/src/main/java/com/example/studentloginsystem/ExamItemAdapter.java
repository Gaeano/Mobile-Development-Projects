package com.example.studentloginsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExamItemAdapter extends RecyclerView.Adapter<ExamItemAdapter.MyViewHolder> {

    private Context context;
    private List<ExamItemModel> dataModelList;

    public ExamItemAdapter(Context context, List<ExamItemModel> dataModelList) {
        this.context = context;
        this.dataModelList = dataModelList;
    }

    @NonNull
    @Override
    public ExamItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.exam_card_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamItemAdapter.MyViewHolder holder, int position) {
        ExamItemModel result = dataModelList.get(position);
        holder.examName.setText(result.getName());
        holder.examDate.setText(result.getDate());
        holder.examTime.setText(result.getMessage());
        holder.imageClock.setImageResource(result.getImgclock());
        holder.imageGrade.setImageResource((result.getImggrade()));

    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView examName, examDate, examTime;
        ImageView imageClock, imageGrade;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            examName = itemView.findViewById(R.id.title);
            examDate = itemView.findViewById(R.id.textClock);
            examTime = itemView.findViewById(R.id.textGrading);
            imageClock = itemView.findViewById(R.id.imgClock);
            imageGrade = itemView.findViewById(R.id.imgGrading);



        }
    }
}
