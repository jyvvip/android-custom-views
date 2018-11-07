package com.android.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.ui.R;
import com.android.ui.chapterfive.FiveActivity;
import com.android.ui.chapterfour.FourActivity;
import com.android.ui.chapterone.OneActivity;
import com.android.ui.chapterseven.SevenActivity;
import com.android.ui.chaptersix.SixActivity;
import com.android.ui.chapterthree.ThreeActivity;
import com.android.ui.chaptertwo.TwoActivity;

public class HomeActivity extends Activity {
    private RecyclerView mRecyclerView;
    //    private List<String> mDatas;
    private HomeAdapter mAdapter;
    private String lessons[];
    private Class lessonClasses[] = new Class[]{
            OneActivity.class,
            TwoActivity.class,
            ThreeActivity.class,
            FourActivity.class,
            FiveActivity.class,
            SixActivity.class,
            SevenActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));

    }

    protected void initData() {
//        mDatas = new ArrayList<String>();
//        for (int i = 'A'; i < 'z'; i++) {
//            mDatas.add("" + (char) i);
//        }
        lessons = getResources().getStringArray(R.array.lessons);
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    HomeActivity.this).inflate(R.layout.item_home, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
//            holder.tv.setText(mDatas.get(position));
            holder.tv.setText(lessons[position]);
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position < lessonClasses.length) {
                        Intent i = new Intent(HomeActivity.this, lessonClasses[position]);
                        startActivity(i);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return lessons.length;
        }

        class MyViewHolder extends ViewHolder {
            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }

}
