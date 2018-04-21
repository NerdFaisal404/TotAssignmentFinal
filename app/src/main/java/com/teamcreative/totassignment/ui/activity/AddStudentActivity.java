package com.teamcreative.totassignment.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.teamcreative.totassignment.data.database.DBHelper;
import com.teamcreative.totassignment.data.model.StudentInfo;
import com.teamcreative.totassignment.R;

public class AddStudentActivity extends AppCompatActivity {

    private EditText mName, mInstitution, mEmail, mPhone, mGender;
    private Bundle mBundle;
    private boolean isEdit;
    private StudentInfo mStudentInfo;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dbHelper = new DBHelper(this);

        mName = findViewById(R.id.edit_name);
        mInstitution = findViewById(R.id.edit_institution);
        mEmail = findViewById(R.id.edit_email);
        mPhone = findViewById(R.id.edit_phone);
        mGender = findViewById(R.id.edit_gender);

        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                insertUpdate();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        mBundle = getIntent().getExtras();
        isEdit = mBundle.getBoolean("edit", false);
        if (isEdit) {
            mStudentInfo = (StudentInfo) mBundle.getSerializable("student");

            mName.setText(mStudentInfo.getName());
            mInstitution.setText(mStudentInfo.getInstitution());
            mEmail.setText(mStudentInfo.getEmail());
            mPhone.setText(mStudentInfo.getPhone());
            mGender.setText(mStudentInfo.getGender());
        }
    }

    private void insertUpdate() throws NullPointerException {
        String name, institute, email, phone, gender;
        name = mName.getText().toString();
        institute = mInstitution.getText().toString();
        email = mEmail.getText().toString();
        phone = mPhone.getText().toString();
        gender = mGender.getText().toString();

        if (name.isEmpty() || institute.isEmpty()
                || phone.isEmpty()) {
            Toast.makeText(this, "name, institution or phone can't be empty", Toast.LENGTH_SHORT).show();
        } else {
            if(mStudentInfo == null) mStudentInfo = new StudentInfo();
            mStudentInfo.setName(name);
            mStudentInfo.setInstitution(institute);
            mStudentInfo.setEmail(email);
            mStudentInfo.setPhone(phone);
            mStudentInfo.setGender(gender);

            if (isEdit) {
                dbHelper.update(mStudentInfo);
                Toast.makeText(this, "Record updated", Toast.LENGTH_SHORT).show();
            } else {
                dbHelper.addStudent(mStudentInfo);
                Toast.makeText(this, "Record added", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(this, StudentDetailsActivity.class);
            intent.putExtra("student", mStudentInfo);
            startActivity(intent);
            finish();
        }
    }
}
