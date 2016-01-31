package com.example.sakon.orchestraquiz;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CSVParser {

    public static void parse(Context context) {
        System.out.println("CSV読込メソッド開始");

        // AssetManagerの呼び出し
        AssetManager assetManager = context.getResources().getAssets();
        try {
            // CSVファイルの読み込み
            InputStream is = assetManager.open("MusicalSymbolQuiz.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = bufferReader.readLine()) != null) {
                // 各行が","で区切られていて4つの項目があるとする
                StringTokenizer st = new StringTokenizer(line, ",");
                String first = st.nextToken();
                String second = st.nextToken();
                String third = st.nextToken();
                String fourth = st.nextToken();
                String fifth = st.nextToken();

                System.out.println(first + ", " + second + ", " + third + ", " + fourth + ", " + fifth);
            }
            bufferReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("CSV読込メソッド終了");
    }
}