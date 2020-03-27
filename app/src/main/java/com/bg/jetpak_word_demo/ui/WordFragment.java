package com.bg.jetpak_word_demo.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bg.jetpak_word_demo.R;
import com.bg.jetpak_word_demo.databinding.FragmentWordBinding;
import com.bg.jetpak_word_demo.db.word.Word;
import com.bg.jetpak_word_demo.ui.adapter.WordAdapter;
import com.bg.jetpak_word_demo.ui.viewmodels.WordViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WordFragment extends Fragment {

    private WordViewModel viewModel;
    private FragmentWordBinding binding;
    private WordAdapter adapter;

    public WordFragment() {
        // 不加这一句不显示 menu ;
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 这里很奇怪啊；
        viewModel = new ViewModelProvider(this).get(WordViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_word, container, false);
        return binding.getRoot();
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear:
                viewModel.clearAll();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOptionsMenuClosed(@NonNull Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new WordAdapter(viewModel);
        binding.recyclerView.setAdapter(adapter);

        // 监听 ListAdapter 自带动画效果的完成后，改变 position, 只截取屏幕可见范围内的部分 .
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator() {
            @Override
            public void onAnimationFinished(@NonNull RecyclerView.ViewHolder viewHolder) {
                super.onAnimationFinished(viewHolder);
                LinearLayoutManager layoutManager = (LinearLayoutManager) binding.recyclerView.getLayoutManager();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {

                    WordAdapter.ViewHolder holder = (WordAdapter.ViewHolder) binding.recyclerView.findViewHolderForAdapterPosition(i);
                    if (holder != null)
                        holder.index.setText(String.valueOf(i + 1));
                }
            }
        });
        //全靠这里监控呢 ;
        viewModel.getAllWords().observe(getViewLifecycleOwner(), new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                adapter.submitList(words);
            }
        });


        binding.wordAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(binding.getRoot());
//                navController.navigate(R.id.action_wordFragment_to_addWordFragment);
                navController.navigate(R.id.action_wordFragment_to_testFragment);
            }
        });

    }


}
