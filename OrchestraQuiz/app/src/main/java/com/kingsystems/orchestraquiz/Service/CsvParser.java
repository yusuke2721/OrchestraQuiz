package com.kingsystems.orchestraquiz.Service;

import android.content.Context;
import android.content.res.AssetManager;

import com.kingsystems.orchestraquiz.Model.MusicalSymbol;
import com.kingsystems.orchestraquiz.Model.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class CsvParser {

    /**
     * CSVファイルを読み込み、Questionクラスのリストを作成する
     *
     * @param context
     * @param questionList 問題リスト
     * @param level 難易度
     */
    public static void createQuizList(Context context, List<Question> questionList, String level) {
        try {
            String fileName = "";
            if(level.equals("初級")) {
                fileName = "MusicalSymbolQuiz_level1.csv";
            } else if(level.equals("中級")) {
                fileName = "MusicalSymbolQuiz_level2.csv";
            } else if(level.equals("上級")) {
                fileName = "MusicalSymbolQuiz_level3.csv";
            }

            // CSVファイルの読み込み
            AssetManager assetManager = context.getResources().getAssets();
            InputStream inputStream = assetManager.open(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String line = "";
            bufferReader.readLine(); // タイトル行を読み捨てる

            while ((line = bufferReader.readLine()) != null) {
                Question question = new Question();
                StringTokenizer st = new StringTokenizer(line, ",");

                //QuestionListを作成
                question.setSymbol(st.nextToken());
                for (int i = 0; i < 4; i++) {
                    question.setMeaning(st.nextToken(),i);
                }
                questionList.add(question);
            }
            Collections.shuffle(questionList);
            bufferReader.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}