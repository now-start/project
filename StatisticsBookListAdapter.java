package com.example.studyproject;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

public class StatisticsBookListAdapter extends BaseAdapter {
    private Context context;
    private List<book> bookList;
    private Fragment parent;
    private String userID = MainActivity.userID;

    public StatisticsBookListAdapter(Context context, List<book> noticeList, Fragment parent) {
        this.context = context;
        this.bookList = noticeList;
        this.parent = parent;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.statistics, null);
        TextView bookTitle = v.findViewById(R.id.bookTitle);
        TextView bookWriter = v.findViewById(R.id.bookWriter);
        TextView bookPublisher = v.findViewById(R.id.bookPublisher);

        bookTitle.setText(bookList.get(position).getBookTitle());
        bookWriter.setText(bookList.get(position).getBookWriter());
        bookPublisher.setText(bookList.get(position).getBookPublisher());

        v.setTag(bookList.get(position).getBookID());

        Button deleteButton = v.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                AlertDialog dialog = builder.setMessage("예약이 취소 되었습니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                StatisticsFragment.totalBook -= 1;
                                StatisticsFragment.Book.setText(StatisticsFragment.totalBook + "권");
                                bookList.remove(position);
                                notifyDataSetChanged();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                AlertDialog dialog = builder.setMessage("예약 취소에 실패하였습니다.")
                                        .setNegativeButton("다시 시도", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                DeleteRequest deleteRequest = new DeleteRequest(userID, bookList.get(position).getBookID() + "", responseListener);
                RequestQueue queue = Volley.newRequestQueue(parent.getActivity());
                queue.add(deleteRequest);
            }
        });

        return v;
    }
}