package com.example.sakon.orchestraquiz;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 問題クラス
 * Created by Kazuma on 2016/01/31.
 */
public class Question {
    private ArrayList<MusicalSymbol> choices = new ArrayList<MusicalSymbol>();
    private int ansNumber;

    Question() {
    }

    /**
     * 問題の選択肢をセットするメソッド
     *
     * @param musicalSymbol 選択肢に追加する音楽記号
     */
    public void addChoices(MusicalSymbol musicalSymbol) {
        //既に選択肢が4つ格納されている場合はエラーとする
        try {
            if (this.choices.size() < 4) {
                this.choices.add(musicalSymbol);
            } else {
                //エラー時処理
            }
        } catch (NullPointerException e) {
            //エラー時処理
        }
    }

    public MusicalSymbol getChoice(int indexNumber) {
        try {
            if (this.choices.size() >= indexNumber + 1) {
                return this.choices.get(indexNumber);
            } else {
                //エラー時処理
                return null;
            }
        } catch (NullPointerException e) {
            //エラー時処理
            return null;
        }
    }

    public void setAnsNumber(int ansNumber) {
        this.ansNumber = ansNumber;
    }

    public int getAnsNumber() {
        return this.ansNumber;
    }
}
