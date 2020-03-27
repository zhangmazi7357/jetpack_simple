package com.bg.jetpak_word_demo.db.word;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * 用来专门操作数据库的类；
 */
public class WordRepository {

    private static WordDao wordDao;
    private LiveData<List<Word>> allWords;

    public WordRepository(Context context) {
        WordDatabase database = WordDatabase.getInstance(context);
        wordDao = database.getWordDao();
        allWords = wordDao.queryAll();
    }

    public LiveData<List<Word>> getAllWords() {
        return allWords;
    }

    public void insert(Word... words) {
        new InsertAsyncTask().execute(words);
    }

    public void delete(Word... words) {
        new DeleteAsyncTask().execute(words);
    }

    public void clearAll() {
        new ClearAsyncTask().execute();
    }

    public void update(Word... words) {
        new UpdateAsyncTask().execute(words);
    }

    /**
     * insert 的任务
     */
    static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insert(words);
            return null;
        }
    }

    /**
     * delete 的任务
     */
    static class DeleteAsyncTask extends AsyncTask<Word, Void, Void> {
        @Override
        protected Void doInBackground(Word... words) {
            wordDao.delete(words);
            return null;
        }
    }


    /**
     * clearAll 的任务；
     */
    static class ClearAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.clearWords();
            return null;
        }
    }


    /**
     * update 的任务
     */
    static class UpdateAsyncTask extends AsyncTask<Word, Void, Void> {
        @Override
        protected Void doInBackground(Word... words) {
            wordDao.update(words);
            return null;
        }
    }


}
