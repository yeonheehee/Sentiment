package com.example.sentiment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RecommendList extends AppCompatActivity {
    private ImageButton drama_bt2;
    private ImageButton movie_bt;
    private ImageButton music_bt;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //앱 실행
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_list);
        Intent intent = getIntent();

        drama_bt2 = findViewById(R.id.drama_bt2);
        movie_bt = findViewById(R.id.movie_bt2);
        music_bt = findViewById(R.id.music_bt2);

        bottomNavigationView = findViewById(R.id.bottom_navi);
        bottomNavigationView.setItemIconTintList(null);

        drama_bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecommendList.this, Sentiment3.class);
                startActivity(intent);
            }
        });

        movie_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecommendList.this, Sentiment3.class);
                startActivity(intent);
            }
        });

        music_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecommendList.this, Sentiment3.class);
                startActivity(intent);
            }
        });

    }
}




