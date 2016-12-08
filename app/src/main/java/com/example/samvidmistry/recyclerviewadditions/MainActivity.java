package com.example.samvidmistry.recyclerviewadditions;

import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.samvidmistry.recyclerviewadditions.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBinding;
    OkHttpClient mOkHttpClient;
    Gson mGson;
    MainListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mBinding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new PostAsyncTask().execute();
            }
        });

        mBinding.textList.addItemDecoration(new TextItemDecoration());

        setSupportActionBar(mBinding.toolbar);

        new PostAsyncTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                if (mAdapter != null) {
                    mAdapter.deleteItems(10);
                }
        }
        return super.onOptionsItemSelected(item);
    }

    private class PostAsyncTask extends AsyncTask<Void, Void, Void> {
        private List<Photo> mPhotos;
        private List<Post> mPosts;

        @Override
        protected void onPreExecute() {
            mBinding.swipeRefresh.setRefreshing(true);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (mOkHttpClient == null) {
                mOkHttpClient = new OkHttpClient();
            }

            Request request = new Request.Builder()
                    .url("https://jsonplaceholder.typicode.com/photos")
                    .build();

            Response response;
            try {
                response = mOkHttpClient.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
                response = null;
            }

            if (response == null) {
                return null;
            }

            if (mGson == null) {
                mGson = new Gson();
            }

            Photo[] photos = new Photo[0];
            try {
                photos = mGson.fromJson(response.body().string(), Photo[].class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (Photo photo : photos) {
                photo.setThumbUrl(photo.getThumbUrl().replace("http", "https"));
                photo.setUrl(photo.getUrl().replace("http", "https"));
            }
            mPhotos = new ArrayList<>(Arrays.asList(photos));

            request = new Request.Builder()
                    .url("https://jsonplaceholder.typicode.com/posts")
                    .build();

            try {
                response = mOkHttpClient.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
                response = null;
            }

            if (response == null) {
                return null;
            }

            Post[] posts = new Post[0];
            try {
                posts = mGson.fromJson(response.body().string(), Post[].class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mPosts = new ArrayList<>(Arrays.asList(posts));

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mBinding.swipeRefresh.setRefreshing(false);
            mBinding.textList.setHasFixedSize(true);
            mAdapter = new MainListAdapter(mPosts, mPhotos);
            mBinding.textList.setAdapter(mAdapter);
        }
    }

}
