package com.bg.jetpak_word_demo.db.word;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * @version 数据库版本，每次改变数据库结构，应该升级version
 * 这里应该把这玩意写成单例
 */
@Database(entities = Word.class, version = 2, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    private static WordDatabase INSTANCE = null;

    public static WordDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, WordDatabase.class, "word_data")
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return INSTANCE;
    }

    public abstract WordDao getWordDao();


    private static Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word ADD COLUMN isShowChinese INTEGER NOT NULL DEFAULT 0");
        }
    };

}
