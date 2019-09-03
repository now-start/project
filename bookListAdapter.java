package com.example.studyproject;

import android.content.Context;
import android.os.AsyncTask;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class bookListAdapter extends BaseAdapter {
    private Context context;
    private List<book> bookList;
    private Fragment parent;
    private String userID = MainActivity.userID;
    private List<Integer> bookIDList;
    public static int totalBook = 0;


    public bookListAdapter(Context context, List<book> bookList, Fragment parent) {
        this.context = context;
        this.bookList = bookList;
        this.parent = parent;
        bookIDList = new ArrayList<>();
        totalBook = 0;

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
        View v = View.inflate(context, R.layout.book, null);

        /*TextView bookID = v.findViewById(R.id.bookID);*/
        TextView bookTitle = v.findViewById(R.id.bookTitle);
        TextView bookWriter = v.findViewById(R.id.bookWriter);
        TextView bookPublisher = v.findViewById(R.id.bookPublisher);

        /*bookID.setText((bookList.get(position).getBookID())+"");*/
        bookTitle.setText(bookList.get(position).getBookTitle());
        bookWriter.setText(bookList.get(position).getBookWriter());
        bookPublisher.setText(bookList.get(position).getBookPublisher());

        v.setTag(bookList.get(position).getBookID());

        Button addButton = v.findViewById(R.id.addButton);
        new BackgroundTask().execute();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!alreadyIn(bookIDList, bookList.get(position).getBookID())) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                    AlertDialog dialog = builder.setMessage("이미 예약된 책 입니다.")
                            .setPositiveButton("다시 시도", null)
                            .create();
                    dialog.show();
                }else if(bookIDList.contains((Integer)(bookList.get(position).getBookID()))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                    AlertDialog dialog = builder.setMessage("이미 예약된 책 입니다.")
                            .setPositiveButton("다시 시도", null)
                            .create();
                    dialog.show();
                }else {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                    AlertDialog dialog = builder.setMessage("책이 예약 되었습니다.")
                                            .setPositiveButton("확인", null)
                                            .create();
                                    dialog.show();
                                    bookIDList.add(bookList.get(position).getBookID());
                                    totalBook += 1;
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                    AlertDialog dialog = builder.setMessage("책 예약에 실패하였습니다.")
                                            .setNegativeButton("확인", null)
                                            .create();
                                    dialog.show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    AddRequest addRequest = new AddRequest(userID, bookList.get(position).getBookID() + "", responseListener);
                    RequestQueue queue = Volley.newRequestQueue(parent.getActivity());
                    queue.add(addRequest);
                }
            }
        });

        return v;
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {       //검색한 책 검색
        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "http://nowstart.iptime.org/~smart/BookValidate.php";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                int bookID; //책 이름

                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    bookID = object.getInt("bookID");
                    bookIDList.add(bookID);
                    count++;
                }
            } catch (
                    Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean alreadyIn(List<Integer> bookIDList, int item) {
        for (int i = 0; i < bookIDList.size(); i++) {
            if (bookIDList.get(i) == item) {
                return false;
            }
        }
        return true;
    }

}
