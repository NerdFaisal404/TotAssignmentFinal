package com.teamcreative.totassignment.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.teamcreative.totassignment.adapter.StudentAdapter;
import com.teamcreative.totassignment.data.database.DBHelper;
import com.teamcreative.totassignment.data.model.StudentInfo;
import com.teamcreative.totassignment.R;
import com.teamcreative.totassignment.util.ItemDecorationUtil;
import com.teamcreative.totassignment.util.ViewUtil;
import com.thefinestartist.finestwebview.FinestWebView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mMessage;
    private FloatingActionButton mFab;
    private RecyclerView mRecyclerView;
    private StudentAdapter mAdapter;

    private List<StudentInfo> mList = new ArrayList<>();
    private DBHelper mDbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMessage = findViewById(R.id.text_message);
        mFab = findViewById(R.id.fab);
        mRecyclerView = findViewById(R.id.recyclerview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new ItemDecorationUtil(ViewUtil.dpToPx(this, 4)));

        mFab.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_webview) {
            setupWebView();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, AddStudentActivity.class);
        intent.putExtra("edit", false);
        startActivity(intent);
    }

    private void loadData() {
        mDbHelper = new DBHelper(this);
        if (mDbHelper.hasData()) {
            mMessage.setVisibility(View.GONE);

            mList = mDbHelper.getStudentList();
            mAdapter = new StudentAdapter(this, mList);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mMessage.setVisibility(View.VISIBLE);
        }
    }


    private void setupWebView(){
        new FinestWebView.Builder(this).theme(R.style.FinestWebViewTheme)
                .titleDefault("Google")
                .showUrl(false)
                .statusBarColorRes(R.color.colorPrimaryDark)
                .toolbarColorRes(R.color.colorPrimary)
                .titleColorRes(R.color.finestWhite)
                .urlColorRes(R.color.colorAccent)
                .iconDefaultColorRes(R.color.finestWhite)
                .progressBarColorRes(R.color.finestWhite)
                .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                .showSwipeRefreshLayout(true)
                .swipeRefreshColorRes(R.color.colorPrimaryDark)
                .menuSelector(R.drawable.selector_light_theme)
                .menuTextGravity(Gravity.CENTER)
                .menuTextPaddingRightRes(R.dimen.defaultMenuTextPaddingLeft)
                .dividerHeight(0)
                .gradientDivider(false)
                .setCustomAnimations(R.anim.slide_left_in, R.anim.hold, R.anim.hold, R.anim.slide_right_out)
                .show(getResources().getString(R.string.web_view_url));
    }
}
