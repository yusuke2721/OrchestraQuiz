package com.kingsystems.orchestraquiz.Model;

import com.kingsystems.orchestraquiz.Model.MusicalSymbol;

import java.util.ArrayList;

/**
 * 問題クラス
 */
public class Question {
    private String symbol;
    private String[] meaning = new String[4];

    private ArrayList<MusicalSymbol> choices = new ArrayList<MusicalSymbol>();
    private int ansNumber;

    public Question() {
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setMeaning(String meaning, int index) {
        if (index < this.meaning.length) {
            this.meaning[index] = meaning;
        } else {
            //エラー処理
            System.out.println("存在しないindexへのset");
        }
    }

    public String getMeaning(int index) {
        if (index < this.meaning.length) {
            return this.meaning[index];
        } else {
            //エラー時処理
            System.out.println("存在しないindexからのget");
            return null;
        }
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
