package com.bg.jetpak_word_demo.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bg.jetpak_word_demo.db.word.Word;
import com.bg.jetpak_word_demo.db.word.WordRepository;

import java.util.List;

/**
 * WordViewModel
 */
public class WordViewModel extends AndroidViewModel {

    private LiveData<List<Word>> allWords;
    private WordRepository repository;

    public WordViewModel(@NonNull Application application) {
        super(application);
        repository = new WordRepository(application.getApplicationContext());

        allWords = repository.getAllWords();   // LiveData 就是在子线程中执行的。
    }

    public LiveData<List<Word>> getAllWords() {
        return allWords;
    }


    public void insert(Word... words) {
        repository.insert(words);
    }

    public void delete(Word... words) {
        repository.delete(words);
    }

    public void clearAll() {
        repository.clearAll();
    }

    public void update(Word... words) {
        repository.update(words);
    }


}
