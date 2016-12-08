package com.example.samvidmistry.recyclerviewadditions;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.samvidmistry.recyclerviewadditions.databinding.LayoutPostBinding;

/**
 * Created by samvidmistry on 11/19/16.
 */

public class PostViewHolder extends RecyclerView.ViewHolder {
    LayoutPostBinding mBinding;

    public PostViewHolder(LayoutPostBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
}
