package com.teamcreative.totassignment.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.teamcreative.totassignment.data.database.DBHelper;
import com.teamcreative.totassignment.data.model.StudentInfo;
import com.teamcreative.totassignment.R;

public class StudentDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mInstitution, mEmail, mPhone, mGender;
    private FloatingActionButton mEdit;
    private Bundle mBundle;
    private StudentInfo mStudentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        android.support.v7.widget.Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mBundle = getIntent().getExtras();
        mStudentInfo = (StudentInfo) mBundle.getSerializable("student");

        setTitle(mStudentInfo.getName());

        mInstitution = findViewById(R.id.text_institution);
        mEmail = findViewById(R.id.text_email);
        mPhone = findViewById(R.id.text_institute);
        mGender = findViewById(R.id.text_gender);

        mEdit = findViewById(R.id.fab);
        mEdit.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                new DBHelper(this).deleteStudent(mStudentInfo.getId());
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        mInstitution.setText(mStudentInfo.getInstitution());
        mEmail.setText(mStudentInfo.getEmail());
        mPhone.setText(mStudentInfo.getPhone());
        mGender.setText(mStudentInfo.getGender());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, AddStudentActivity.class);
        intent.putExtra("edit", true);
        intent.putExtra("student", mStudentInfo);
        startActivity(intent);
        finish();
    }
}
