package com.example.sakon.orchestraquiz;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CSVParser {

    public static void parse(Context context, List<Question> questionList) {
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
                Question question = new Question();
                for (int i = 0; i < 4; i++) {
                    MusicalSymbol musicalSymbol = new MusicalSymbol(st.nextToken(), st.nextToken());
                    question.addChoices(musicalSymbol);
                }
                questionList.add(question);
            }
            bufferReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("CSV読込メソッド終了");
    }
}