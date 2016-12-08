package com.example.samvidmistry.recyclerviewadditions;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by samvidmistry on 11/19/16.
 */

public class BindingAdapters {

    @BindingAdapter("imageUrl")
    public static void setImageFromUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).asBitmap().into(imageView);
    }

}
