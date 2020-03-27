package com.bg.jetpak_word_demo.ui.paged;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bg.jetpak_word_demo.R;
import com.bg.jetpak_word_demo.db.student.Student;
import com.bg.jetpak_word_demo.db.student.StudentDao;
import com.bg.jetpak_word_demo.db.student.StudentDatabase;
import com.bg.jetpak_word_demo.db.word.Word;

import java.util.List;

/**
 * 模拟 Paging
 */
public class PagingFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button insert, delete;

        private PageAdapter adapter;
    private StudentDao studentDao;
    private LiveData<PagedList<Student>> allStudents;


    public PagingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity activity = requireActivity();
        recyclerView = activity.findViewById(R.id.pagRecyclerView);
        insert = activity.findViewById(R.id.insert);
        delete = activity.findViewById(R.id.delete);


        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        adapter = new PageAdapter();

        recyclerView.setAdapter(adapter);
        studentDao = StudentDatabase.getInstance(activity).getStudentDao();

        // 两个参数 是数据来源 和 分页的个数
        allStudents = new LivePagedListBuilder<>(studentDao.queryAll(), 2)
                .build();
        allStudents.observe(getViewLifecycleOwner(), new Observer<PagedList<Student>>() {
            @Override
            public void onChanged(PagedList<Student> students) {
                // 刷新
                adapter.submitList(students);
            }
        });


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student[] students = new Student[1000];
                for (int i = 0; i < 1000; i++) {
                    Student s = new Student(i);
                    students[i] = s;
                }
                new InsertAsyncTask(studentDao).execute(students);
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentDao.clearStudents();
            }
        });
    }


    static class InsertAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDao studentDao;

        public InsertAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insert(students);
            return null;
        }
    }


}
