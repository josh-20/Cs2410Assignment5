package com.example.contactsapp.components;

import android.content.Context;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.contactsapp.R;

public class ImageSelector extends FrameLayout {
    String imageUri = "";
    AppCompatImageView imageView;
    public interface ImageSelectorClickListener{
        public void onClick();
    }
    public ImageSelector(Context context,ImageSelectorClickListener listener){
        this(context,  listener,"");
    }
    public ImageSelector(Context context,ImageSelectorClickListener listener, String imageUri){
        super(context);
        this.imageUri = imageUri;
        imageView = new AppCompatImageView(context);
        setImageUri(imageUri);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,480);
        imageView.setLayoutParams(params);
        setOnClickListener(view ->{
            listener.onClick();
        });
        addView(imageView);

    }
    public void setImageUri(String imageUri){
        this.imageUri = imageUri;
        if(imageUri.equals("")) {
            imageView.setImageResource(R.drawable.ic_baseline_add_a_photo_240);
        }else{
            imageView.setImageURI(Uri.parse(imageUri));
        }
    }
    public String getImageUri(){
        return imageUri;
    }
}
