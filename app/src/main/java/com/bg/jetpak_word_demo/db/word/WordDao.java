package com.bg.jetpak_word_demo.db.word;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {

    @Insert
    void insert(Word... words);

    @Delete
    void delete(Word... words);

    /**
     * 清空
     */
    @Query("DELETE FROM WORD")
    void clearWords();

    @Update
    void update(Word... words);

    /**
     * @return
     * @Query("SELECT * FROM WORD")
     * List<Word> queryAll();
     */
    @Query("SELECT * FROM WORD ORDER BY ID DESC")
    LiveData<List<Word>> queryAll();
}
