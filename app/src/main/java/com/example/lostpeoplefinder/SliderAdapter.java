package com.example.lostpeoplefinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
public class SliderAdapter extends PagerAdapter {
    Context context ;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public  int[] sliderImages ={R.drawable.ic_email,R.drawable.ic_email,R.drawable.ic_email};
    public String[] sliderTitles={"Welcome","Find Lost People","Help Others"};
    public String[] sliderDesc={"Welcome to Finder, where hope meets action!","Together, we can bring missing loved ones back home.","Join our community of caring hearts and make a difference."};

    @Override
    public int getCount() {
        return sliderImages.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_on_board,container,false);
        ImageView sliderImage = view.findViewById(R.id.onBoardImage);
        TextView sliderTitle =view.findViewById(R.id.onBoardTitle);
        TextView sliderInfo =view.findViewById(R.id.onBoardInfo);
        sliderImage.setImageResource(sliderImages[position]);
        sliderTitle.setText(sliderTitles[position]);
        sliderInfo.setText(sliderDesc[position]);

        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
