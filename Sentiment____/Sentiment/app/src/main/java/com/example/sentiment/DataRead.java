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

public class DataRead {  //여긴 노래
    public static List<String> DataRead1(Context context) { //발라드
        List<List<String>> ballad = new ArrayList<List<String>>();
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
                ballad.add(tmpList);
            }
            br.close();
            i = random.nextInt(50);
            Collections.shuffle(ballad);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ballad.get(i);
    }

    public static List<String> DataRead2(Context context) { //댄스
        List<List<String>> dance = new ArrayList<List<String>>();
        Random random = new Random();
        InputStream inputStream = context.getResources().openRawResource(R.raw.dance);
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
                dance.add(tmpList);
            }
            br.close();
            i = random.nextInt(50);
            Collections.shuffle(dance);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dance.get(i);

    }

    public static List<String> DataRead3(Context context) { //발라드
        List<List<String>> indi = new ArrayList<List<String>>();
        Random random = new Random();
        InputStream inputStream = context.getResources().openRawResource(R.raw.indi);
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
                indi.add(tmpList);
            }
            br.close();
            i = random.nextInt(50);
            Collections.shuffle(indi);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indi.get(i);

    }

    public static List<String> DataRead4(Context context) { //발라드
        List<List<String>> rock = new ArrayList<List<String>>();
        Random random = new Random();
        InputStream inputStream = context.getResources().openRawResource(R.raw.rock);
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
                rock.add(tmpList);
            }
            br.close();
            i = random.nextInt(50);
            Collections.shuffle(rock);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rock.get(i);

    }
}

