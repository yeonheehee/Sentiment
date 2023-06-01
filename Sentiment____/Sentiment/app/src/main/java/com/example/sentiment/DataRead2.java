package com.example.sentiment;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DataRead2 {  //영화
    public static List<String> drama(Context context) { //발라드
        List<List<String>> drama1 = new ArrayList<List<String>>();
        Random random = new Random();
        InputStream inputStream = context.getResources().openRawResource(R.raw.ballad);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String a= null;
        int j = 1;
        int i= 0;
        try {
            while ((line = br.readLine()) != null) {

                List<String> tmpList = new ArrayList<String>();
                String array[] = line.split(",");
                tmpList = Arrays.asList(array);
                drama1.add(tmpList);
            }
            br.close();
            i = random.nextInt(50);
            Collections.shuffle(drama1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drama1.get(i);
    }

}

