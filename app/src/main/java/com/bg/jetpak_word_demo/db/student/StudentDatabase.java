package com.bg.jetpak_word_demo.db.student;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Student.class, version = 1, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {
    private static StudentDatabase INSTANCE = null;

    public synchronized static StudentDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, StudentDatabase.class, "student_data")
                    .build();
        }
        return INSTANCE;
    }

    public abstract StudentDao getStudentDao();
}
