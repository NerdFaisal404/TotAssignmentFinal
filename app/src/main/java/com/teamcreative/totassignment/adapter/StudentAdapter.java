package com.teamcreative.totassignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.teamcreative.totassignment.R;

import java.util.ArrayList;
import java.util.List;

import com.teamcreative.totassignment.data.model.StudentInfo;
import com.teamcreative.totassignment.ui.activity.StudentDetailsActivity;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private Context mContext;
    private List<StudentInfo> mInfoList = new ArrayList<>();
    private StudentInfo mStudentInfo;

    public StudentAdapter(Context mContext, List<StudentInfo> mInfoList) {
        this.mContext = mContext;
        this.mInfoList = mInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mStudentInfo = mInfoList.get(position);
        holder.mName.setText(mInfoList.get(position).getName());
        holder.mInstitute.setText(mStudentInfo.getInstitution());
    }

    @Override
    public int getItemCount() {
        return mInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mName, mInstitute;

        public ViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.text_name);
            mInstitute = itemView.findViewById(R.id.text_institute);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, StudentDetailsActivity.class);
            intent.putExtra("student", mInfoList.get(getAdapterPosition()));
            mContext.startActivity(intent);
        }
    }
}
