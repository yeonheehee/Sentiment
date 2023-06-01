package com.example.sentiment;

import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Sentiment2 extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ChatData> ChatList;
    private ImageView http_img;
    private TextView http_test;
    private TextView http_test2;
    private TextView http_test3;
    private DatabaseReference myRef;
    private RecyclerView.Adapter mAdapter;
    private String nick = "nick1";
    private String a;
    private Object b;
    private ArrayList<Object> aa = new ArrayList<>();
    private ArrayList<Object> bb = new ArrayList<>();
    private List<String> s = new ArrayList<>();
    private List<String> s1 = new ArrayList<>();
    private List<String> s2 = new ArrayList<>();
    private List<String> s3 = new ArrayList<>();
    private List<String> result = new ArrayList<>();
    private BottomNavigationView bottomNavigationView;
    private String url;
    private Intent intent;
    private TextView http_link;


    //private TextView sent_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentiment2);
        bottomNavigationView = findViewById(R.id.bottom_navi);
        bottomNavigationView.setItemIconTintList(null);
        intent = getIntent();  //main에서 날라온 intent값 받음
        String str = intent.getStringExtra("str"); //string형태의 데이터를 받아옴




        // *********데이터값 받아오는거************
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        mRecyclerView = findViewById(R.id.my_recycler);
//        mRecyclerView.setHasFixedSize(true); //동일한 크기의 아이템 항목을 사용자에게 리스트로 보여주기 위해서
        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);

        // Write a message to the database
        ChatList = new ArrayList<>(); //초기값이 없기때문에
        mAdapter = new ChatAdapter(ChatList, Sentiment2.this, nick); //어댑터의 초기값
//        mRecyclerView.setAdapter(mAdapter);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
//                    String msg = snapshot.getKey();
//                    aa.add(msg);
//                    mAdapter.notifyItemInserted(aa.size() - 1)
                b = map.values();
                a = String.valueOf(b);
                String ar[] = a.split(",");

                for (int i = 0; i < ar.length; i++) {
                    if (i % 2 == 0) {
                        bb.add(ar[i].replaceAll("[^ㄱ-ㅎ가-힣]", ""));
                    }
                }



//********다음은 파이썬으로 값 보내기************


                OkHttpClient okHttpClient = new OkHttpClient();
                for (int j = 0; j < bb.size(); j ++) {
                    String value1 = bb.get(j).toString();
                    RequestBody formbody = new FormBody.Builder().add("ed_test", value1).build();
                    try {
                        Request request = new Request.Builder().url("http://192.168.117.1:5000/test").post(formbody).build();
                        okHttpClient.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(@NotNull Call call, @NotNull IOException e) { //응답 실패했을경우
                                Toast.makeText(Sentiment2.this, "network not found", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException { //응답 성공
                                http_test = findViewById(R.id.http_test);
                                http_test2 = findViewById(R.id.http_test2);
                                http_test3 = findViewById(R.id.http_test3);
                                http_img = findViewById(R.id.http_img);
                                http_link = findViewById(R.id.http_link);
                                result.add(response.body().string());

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            String i = result.get(result.size()-1); //result에 가져온 라벨 저장
                                            if(result.get(result.size()-1) == i) { //값을 다 가져오면
                                                Map<String, Integer> map2 = new HashMap<String, Integer>(); //통계
                                                for (int k = 0; k < result.size(); k++) {
                                                    if(map2.containsKey(result.get(k))){
                                                        int count = map2.get(result.get(k));
                                                        map2.put(result.get(k),count+1);
                                                    }else{
                                                        map2.put(result.get(k),1);
                                                    }
                                                }
                                                int Maxvalue = Collections.max(map2.values());  //라벨 갯수 카운트해서 가장 많이 나온 감정 판단
                                                for(Map.Entry<String, Integer> entry : map2.entrySet()) {
                                                    if(Maxvalue == entry.getValue()){
                                                        int finalvalue = Integer.parseInt(entry.getKey());
//                                                        http_test.setText(entry.getKey());
                                                        if(finalvalue == 0){ //분노
                                                            s = DataRead.DataRead4(Sentiment2.this);
                                                            String sent = s.get(0);
                                                            String sent1 = s.get(1);
                                                            String sent2 = s.get(2);
                                                            String sent3 = s.get(3);
                                                            Glide.with(Sentiment2.this).load(sent2).into(http_img);
                                                            http_test.setText("많이 화나셨군요! 이 노래는 어떠세요?"); //발라드
                                                            http_test2.setText(sent);
                                                            http_test3.setText(sent1);
                                                            http_link.setText("음악 들으러가기!");
                                                            Linkify.TransformFilter mTransform = new Linkify.TransformFilter()
                                                            {
                                                                @Override public String transformUrl(Matcher match, String url)
                                                                { return ""; } };
                                                            Pattern pattern1 = Pattern.compile("음악 들으러가기!");
                                                            Linkify.addLinks(http_link, pattern1,sent3, null,mTransform);
                                                        }


                                                        else if (finalvalue == 1) {  //슬픔
                                                            s1 = DataRead.DataRead1(Sentiment2.this);
                                                            String sent = s1.get(0);
                                                            String sent1 = s1.get(1);
                                                            String sent2 = s1.get(2);
                                                            String sent3 = s1.get(3);
                                                            Glide.with(Sentiment2.this).load(sent2).into(http_img);
                                                            http_test.setText("슬퍼하지 말아요~"); //발라드
                                                            http_test2.setText(sent);
                                                            http_test3.setText(sent1);
                                                            http_link.setText("음악 들으러가기!");
                                                            Linkify.TransformFilter mTransform = new Linkify.TransformFilter()
                                                            {
                                                                @Override public String transformUrl(Matcher match, String url)
                                                                { return ""; } };
                                                            Pattern pattern1 = Pattern.compile("음악 들으러가기!");
                                                            Linkify.addLinks(http_link, pattern1,sent3, null,mTransform);
                                                        }
                                                        else if(finalvalue == 2) {   //걱정
                                                            s2 = DataRead.DataRead3(Sentiment2.this);
                                                            String sent = s2.get(0);
                                                            String sent1 = s2.get(1);
                                                            String sent2 = s2.get(2);
                                                            String sent3 = s2.get(3);
                                                            Glide.with(Sentiment2.this).load(sent2).into(http_img);
                                                            http_test.setText("이 노래를 듣고 걱정은 날려버리기!"); //발라드
                                                            http_test2.setText(sent);
                                                            http_test3.setText(sent1);
                                                            http_link.setText("음악 들으러가기!");
                                                            Linkify.TransformFilter mTransform = new Linkify.TransformFilter()
                                                            {
                                                                @Override public String transformUrl(Matcher match, String url)
                                                                { return ""; } };
                                                            Pattern pattern1 = Pattern.compile("음악 들으러가기!");
                                                            Linkify.addLinks(http_link, pattern1,sent3, null,mTransform);
                                                        }
                                                        else if(finalvalue ==3){  //신나는 노래
                                                            s3 = DataRead.DataRead2(Sentiment2.this);
                                                            String sent = s3.get(0);
                                                            String sent1 = s3.get(1);
                                                            String sent2 = s3.get(2);
                                                            String sent3 = s3.get(3);
                                                            Glide.with(Sentiment2.this).load(sent2).into(http_img);
                                                            http_test.setText("더 신나게!"); //발라드
                                                            http_test2.setText(sent);
                                                            http_test3.setText(sent1);
                                                            http_link.setText("음악 들으러가기!");
                                                            Linkify.TransformFilter mTransform = new Linkify.TransformFilter()
                                                            {
                                                                @Override public String transformUrl(Matcher match, String url)
                                                                { return ""; } };
                                                            Pattern pattern1 = Pattern.compile("음악 들으러가기!");
                                                            Linkify.addLinks(http_link, pattern1,sent3, null,mTransform);
                                                        }
                                                    }
                                                }
                                            }

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });

                            }

                        });
                    } catch (
                            Exception e) {

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });







        }



        //tv_sent.setText(str);
//        tv_sent.setText(DataRead.DataRead1(this).toString());


}