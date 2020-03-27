package com.bg.jetpak_word_demo.db.word;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Word {

    @PrimaryKey(autoGenerate = true)
    private int id;


    private String english;

    private String chinese;

    private boolean isShowChinese;


    public Word(String english, String chinese) {
        this.english = english;
        this.chinese = chinese;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public boolean isShowChinese() {
        return isShowChinese;
    }

    public void setShowChinese(boolean showChinese) {
        isShowChinese = showChinese;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word = (Word) o;
        return id == word.id &&
                isShowChinese == word.isShowChinese &&
                Objects.equals(english, word.english) &&
                Objects.equals(chinese, word.chinese);
    }


}
