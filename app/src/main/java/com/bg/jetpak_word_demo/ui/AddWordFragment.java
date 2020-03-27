package com.bg.jetpak_word_demo.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bg.jetpak_word_demo.R;
import com.bg.jetpak_word_demo.db.word.Word;
import com.bg.jetpak_word_demo.ui.viewmodels.WordViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddWordFragment extends Fragment {

    private EditText english, chinese;
    private Button addBtn;

    private WordViewModel viewModel;

    public AddWordFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(WordViewModel.class);
        View view = inflater.inflate(R.layout.fragment_add_word, container, false);
        english = view.findViewById(R.id.english);
        chinese = view.findViewById(R.id.chinese);
        addBtn = view.findViewById(R.id.sure);


        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        english.addTextChangedListener(watcher);
        chinese.addTextChangedListener(watcher);
        addBtn.setEnabled(false);

        // 获取焦点
        english.requestFocus();
        //  控制键盘的  Manager ；
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null)
            imm.showSoftInput(english, 0);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在这里插入数据库；
                String englishT = english.getText().toString().trim();
                String chineseT = chinese.getText().toString().trim();
                Word word = new Word(englishT, chineseT);
                viewModel.insert(word);

                NavController controller = Navigation.findNavController(v);
                // 可以返回上一层 Fragment ;
                controller.navigateUp();
            }
        });
    }


    /**
     * edittext 的监听器
     */
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String englishT = english.getText().toString().trim();
            String chineseT = chinese.getText().toString().trim();
            if (!TextUtils.isEmpty(englishT) && !TextUtils.isEmpty(chineseT)) {
                addBtn.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


}
