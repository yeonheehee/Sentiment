package com.example.sentiment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class Sentiment3 extends AppCompatActivity {
    //private ArrayList<String> hellist = new ArrayList<String>();

    private ImageButton btn_move;
    private TextView main_top;
    private TextView http_test;
    private BottomNavigationView bottomNavigationView2;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //앱 실행
        List<List<String>> test = new ArrayList<List<String>>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentiment3);

        main_top = findViewById(R.id.main_top);

        btn_move = findViewById(R.id.btn_move);
        bottomNavigationView2 = findViewById(R.id.bottom_navi);
        bottomNavigationView2.setItemIconTintList(null);


        btn_move.setOnClickListener(new View.OnClickListener() { //음악으로
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sentiment3.this, Sentiment2.class);
//
//                //String value1 = ed_test.getText().toString();  //여기 대신에 값 가져오는거
//
//                OkHttpClient okHttpClient = new OkHttpClient();
//                RequestBody formbody = new FormBody.Builder().add("ed_test", value1).build();
//                try {
//                    Request request = new Request.Builder().url("http://192.168.117.1:5000/test").post(formbody).build();
//                    okHttpClient.newCall(request).enqueue(new Callback() {
//                        @Override
//                        public void onFailure(@NotNull Call call, @NotNull IOException e) { //응답 실패했을경우
//                            Toast.makeText(Sentiment3.this, "network not found", Toast.LENGTH_LONG).show();
//                        }
//
//                        @Override
//                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException { //응답 성공
//                            http_test = findViewById(R.id.http_test);
//
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    try {
//                                        http_test.setText(response.body().string());
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            });
//
//                        }
//                    });
//                } catch (
//                        Exception e) {
//
//                }


                //intent.putExtra("list", hello_txt.getText(DataRead.DataRead1().toString()));
                startActivity(intent);  //액티비티 이동(현재액티비티,이동할 액티비티)
            }
        });

    }
}
