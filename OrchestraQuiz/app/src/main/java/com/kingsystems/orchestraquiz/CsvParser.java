package com.kingsystems.orchestraquiz;

import android.content.Context;
import android.content.res.AssetManager;

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
     * @param questionList
     */
    public static void createQuizList(Context context, List<Question> questionList) {

        // AssetManagerの呼び出し
        AssetManager assetManager = context.getResources().getAssets();
        try {
            // CSVファイルの読み込み
            InputStream is = assetManager.open("MusicalSymbolQuiz.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String line = "";

            while ((line = bufferReader.readLine()) != null) {
                Question question = new Question();
                StringTokenizer st = new StringTokenizer(line, ",");
                // MusicalSymbolクラスを作成し、4つ1組でQuestionクラスのリストを作成
                for (int i = 0; i < 4; i++) {
                    MusicalSymbol musicalSymbol = new MusicalSymbol(st.nextToken(), st.nextToken());
                    question.addChoices(musicalSymbol);
                }
                questionList.add(question);
            }
            Collections.shuffle(questionList);
            bufferReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}