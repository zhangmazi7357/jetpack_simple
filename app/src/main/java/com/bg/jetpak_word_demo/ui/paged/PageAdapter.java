package com.bg.jetpak_word_demo.ui.paged;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bg.jetpak_word_demo.R;
import com.bg.jetpak_word_demo.db.student.Student;

/**
 * pagingAdapter ；
 */
public class PageAdapter extends PagedListAdapter<Student, PageAdapter.ViewHolder> {

    public PageAdapter() {
        super(new DiffUtil.ItemCallback<Student>() {
            @Override
            public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getId() == newItem.getId();
            }
            @Override
            public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_paging, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student item = getItem(position);
        // 这里 item  可能会为空
        if (item != null) {
            holder.number.setText(String.valueOf(item.getStudent_number()));
        }

    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView number;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number);
        }
    }

}
