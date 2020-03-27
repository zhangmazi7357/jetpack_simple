package com.bg.jetpak_word_demo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bg.jetpak_word_demo.R;
import com.bg.jetpak_word_demo.db.word.Word;
import com.bg.jetpak_word_demo.ui.viewmodels.WordViewModel;

/**
 * 专门针对列表 的Adapter
 */
public class WordAdapter extends ListAdapter<Word, WordAdapter.ViewHolder> {

    private WordViewModel wordViewModel;

    public WordAdapter(WordViewModel viewModel) {
        super(new DiffUtil.ItemCallback<Word>() {
            @Override
            public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
                return oldItem.equals(newItem);
            }
        });
        this.wordViewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_word, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        // SwitchButton 的选中事件, 在创建ViewHolder 的同时 绑定点击事件;
        holder.isShowChineseBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Word word = (Word) holder.itemView.getTag(R.id.word_holder_view);

                // 刷新数据库 ;

                if (isChecked) {
                    holder.chinese.setVisibility(View.GONE);
                } else {
                    holder.chinese.setVisibility(View.VISIBLE);
                }
                word.setShowChinese(isChecked);
                wordViewModel.update(word);


            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Word item = getItem(position);
        holder.itemView.setTag(R.id.word_holder_view, item);
        // 暂时
        holder.index.setText(String.valueOf(position + 1));

        holder.chinese.setText(item.getChinese());
        holder.english.setText(item.getEnglish());

        if (item.isShowChinese()) {
            holder.isShowChineseBtn.setChecked(true);
            holder.chinese.setVisibility(View.GONE);
        } else {
            holder.isShowChineseBtn.setChecked(false);
            holder.chinese.setVisibility(View.VISIBLE);
        }


    }

    /**
     * 当 holder重新出现在屏幕上 ...
     */
    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);

        holder.index.setText(String.valueOf(holder.getAdapterPosition() + 1));

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView index;
        TextView english, chinese;
        Switch isShowChineseBtn;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            index = itemView.findViewById(R.id.index);
            english = itemView.findViewById(R.id.english);
            chinese = itemView.findViewById(R.id.chinese);
            isShowChineseBtn = itemView.findViewById(R.id.isShowChineseBtn);
        }
    }
}
