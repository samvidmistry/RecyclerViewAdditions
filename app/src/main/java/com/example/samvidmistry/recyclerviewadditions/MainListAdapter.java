package com.example.samvidmistry.recyclerviewadditions;

import android.os.AsyncTask;
import android.support.v4.view.GravityCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.samvidmistry.recyclerviewadditions.databinding.LayoutHeaderBinding;
import com.example.samvidmistry.recyclerviewadditions.databinding.LayoutPostBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samvidmistry on 11/19/16.
 */

public class MainListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Post> mPosts;
    private List<Photo> mPhotos;
    private int mFirstPhoto = 0;

    public MainListAdapter(List<Post> posts, List<Photo> photos) {
        mPosts = posts;
        mPhotos = photos;
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        if (holder instanceof RecyclerViewHolder) {
            ((RecyclerViewHolder) holder).mBinding.photolist.scrollToPosition(mFirstPhoto);
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        if (holder instanceof RecyclerViewHolder) {
            mFirstPhoto = ((LinearLayoutManager) ((RecyclerViewHolder) holder).mBinding.photolist
                    .getLayoutManager()).findFirstVisibleItemPosition();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == R.layout.layout_header) {
            viewHolder = new RecyclerViewHolder(LayoutHeaderBinding.inflate(
                    LayoutInflater.from(parent.getContext()),
                    parent,
                    false
            ));
        } else {
            viewHolder = new PostViewHolder(LayoutPostBinding.inflate(
                    LayoutInflater.from(parent.getContext()), parent, false));
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecyclerViewHolder) {
            if (((RecyclerViewHolder) holder).mBinding.photolist.getAdapter() == null) {
                ((RecyclerViewHolder) holder).mBinding.photolist.setHasFixedSize(true);
                ((RecyclerViewHolder) holder).mBinding.photolist.setAdapter(
                        new PhotoListAdapter(mPhotos)
                );
                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        ((RecyclerViewHolder) holder).mBinding.photolist.getLayoutManager();
                layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
                ((RecyclerViewHolder) holder).mBinding.photolist.
                        addItemDecoration(new PhotoItemDecoration());
                new GravitySnapHelper(GravityCompat.START).
                        attachToRecyclerView(((RecyclerViewHolder) holder).mBinding.photolist);
            }
        } else if (holder instanceof PostViewHolder) {
            ((PostViewHolder) holder).mBinding.setPost(mPosts.get(holder.getAdapterPosition() - 1));
            ((PostViewHolder) holder).mBinding.executePendingBindings();
        }
    }

    public void deleteItems(int count) {
        new DiffTask(count).execute();
    }

    @Override
    public int getItemCount() {
        return mPosts == null ? 0 : mPosts.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return R.layout.layout_header;
        }

        return R.layout.layout_post;
    }

    private class DiffTask extends AsyncTask<Void, Void, DiffUtil.DiffResult> {
        private int mCount;

        private DiffTask(int count) {
            mCount = count;
        }

        @Override
        protected DiffUtil.DiffResult doInBackground(Void... voids) {
            final ArrayList<Post> posts = new ArrayList<>(mPosts);
            for (int i = 0; i < mCount; i++) {
                posts.remove(i);
            }

            return DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mPosts.size();
                }

                @Override
                public int getNewListSize() {
                    return posts.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mPosts.get(oldItemPosition).getTitle().equals(
                            posts.get(newItemPosition).getTitle()
                    );
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    return mPosts.get(oldItemPosition).getBody().equals(
                            posts.get(newItemPosition).getBody()
                    );
                }
            });
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(DiffUtil.DiffResult result) {
            result.dispatchUpdatesTo(MainListAdapter.this);
        }
    }

}
