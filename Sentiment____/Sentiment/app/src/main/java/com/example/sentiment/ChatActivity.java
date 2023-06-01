package com.example.sentiment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter ;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ChatData> ChatList;
    private String nick = "nick1";
    private DatabaseReference myRef;
    private BottomNavigationView bottomNavigationView;
    private EditText chat_ed;
    private Button chat_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent intent = getIntent();

        bottomNavigationView = findViewById(R.id.bottom_navi);
        bottomNavigationView.setItemIconTintList(null);

        chat_ed = findViewById(R.id.chat_ed);
        chat_btn = findViewById(R.id.chat_btn);

        chat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = chat_ed.getText().toString();

                if(msg != null){
                    ChatData chat = new ChatData();
                    chat.setNickname(nick);
                    chat.setMsg(msg);
                    myRef.push().setValue(chat);
                }
            }
        });

        mRecyclerView = findViewById(R.id.my_recycler);
        mRecyclerView.setHasFixedSize(true); //동일한 크기의 아이템 항목을 사용자에게 리스트로 보여주기 위해서
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Write a message to the database
        ChatList = new ArrayList<>(); //초기값이 없기때문에
        mAdapter = new ChatAdapter(ChatList,ChatActivity.this,nick); //어댑터의 초기값
        mRecyclerView.setAdapter(mAdapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        //


        //주의사항!!! ------ 여러가지 클래스 넣으명 안됌

        myRef.addChildEventListener(new ChildEventListener() { //데이터베이스의ㅣ 메시지
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { //datasnapshot 채팅 데이터 담고있는 변수
                ChatData chat = snapshot.getValue(ChatData.class); //자동으로 chatdata의 클래스로 가서 값을 받아옴
                ((ChatAdapter)mAdapter).addChat(chat);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}