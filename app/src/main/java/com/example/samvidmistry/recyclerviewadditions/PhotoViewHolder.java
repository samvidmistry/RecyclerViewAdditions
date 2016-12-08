package com.example.samvidmistry.recyclerviewadditions;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.samvidmistry.recyclerviewadditions.databinding.LayoutPhotoBinding;

/**
 * Created by samvidmistry on 11/19/16.
 */

public class PhotoViewHolder extends RecyclerView.ViewHolder {
    LayoutPhotoBinding mBinding;

    public PhotoViewHolder(LayoutPhotoBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
}
