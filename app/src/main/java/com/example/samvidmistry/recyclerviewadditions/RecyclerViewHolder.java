package com.example.samvidmistry.recyclerviewadditions;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.samvidmistry.recyclerviewadditions.databinding.LayoutHeaderBinding;

/**
 * Created by samvidmistry on 11/19/16.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    LayoutHeaderBinding mBinding;

    public RecyclerViewHolder(LayoutHeaderBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
}
