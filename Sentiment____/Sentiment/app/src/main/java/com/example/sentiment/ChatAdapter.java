package com.example.sentiment;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>{
    private List<ChatData> mData;
    private String Mynickname;

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public static class ChatViewHolder extends RecyclerView.ViewHolder{
        public TextView chat_nick;
        public TextView chat_msg;
        public View rootView;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            chat_nick = itemView.findViewById(R.id.chat_nick);
            chat_msg = itemView.findViewById(R.id.chat_msg);
            rootView = itemView;
        }
    }
    public ChatAdapter(List<ChatData> myDataset, Context context, String Mynickname){
        mData = myDataset;
        this.Mynickname = Mynickname; //닉네임, 채팅 데이터 받아오기
    }

    //뷰 홀더 한줄
    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_chat, parent, false);
        ChatViewHolder vh = new ChatViewHolder(v);
        return vh;
    }


    // onBindViewHolder : position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    //한 줄에 들어가는 요소 정함
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatData chat = mData.get(position);

        holder.chat_nick.setText(chat.getNickname()); //갖고온 닉네임 chatnick에 저장
        holder.chat_msg.setText(chat.getMsg()); //갖고온 메시지 chatmsg에 저장
        //chat.getNickname(); 닉네임 갖고오기
        //chat.getMsg(); //메시지 내용 갖고오기기 DTO역할

        //받아온 닉네임 비교해서 왼쪽 오른쪽 위치에 놓기
        if(chat.getNickname().equals(this.Mynickname)){
            //받아온게 내 닉넴이면
            holder.chat_nick.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            holder.chat_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        }
        else{
            holder.chat_nick.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.chat_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        }
    }

    // getItemCount : 전체 데이터의 개수를 리턴
    @Override
    public int getItemCount() {

        return mData == null ? 0: mData.size();
    }

    public ChatData getChat(int position){

        return mData !=null ? mData.get(position) : null;
    }

    public String addChat(ChatData chat){ //데이터 갱신
        mData.add(chat);
        notifyItemInserted(mData.size()-1); //데이터 삽입 변화
        return mData.toString();
    }
    public List<ChatData> Test(ChatData chat){
        mData.add(chat);
        notifyItemInserted(mData.size()-1);
        return mData;
    }
}
