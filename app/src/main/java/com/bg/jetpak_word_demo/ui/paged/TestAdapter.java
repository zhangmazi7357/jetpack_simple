package com.bg.jetpak_word_demo.ui.paged;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bg.jetpak_word_demo.R;
import com.bg.jetpak_word_demo.db.student.Student;

public class TestAdapter extends ListAdapter<Student, TestAdapter.ViewHolder> {


    public TestAdapter() {
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
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_paging, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student item = getItem(position);
        holder.t.setText(String.valueOf(item.getStudent_number()));

    }



    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView t;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            t = itemView.findViewById(R.id.number);
        }
    }

}
