package com.example.sentiment;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextRead { //채팅 데이터 분류
    public static String textR(Context context) {
        List<List<String>> TextRead = new ArrayList<List<String>>();
        //List<String> tmpList = new ArrayList<String>();
        InputStream inputStream = context.getResources().openRawResource(R.raw.test2);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String a = null;
        int j = 1;
        int i = 0;
        try {
            while ((line = br.readLine()) != null) {
                int idx = line.indexOf("]");
                String test2 = line.substring(idx + 1);

                int idx2 = test2.indexOf("]");
                String test3 = test2.substring(idx2 + 1);

                List<String> tmpList = new ArrayList<String>();
                String[] array = test3.split(" ");
                tmpList = Arrays.asList(array);
                TextRead.add(tmpList);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }return TextRead.get(10).get(1);
    }

}
