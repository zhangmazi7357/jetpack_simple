package com.bg.jetpak_word_demo.db.student;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao {


    @Insert
    void insert(Student... students);


    @Query("SELECT * FROM STUDENT")
    DataSource.Factory<Integer, Student> queryAll();




    /**
     * 清空
     */
    @Query("DELETE FROM STUDENT")
    void clearStudents();

}
