package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.booking.rtlviewpager.RtlViewPager;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CircleView circleView= findViewById(R.id.circleView);
        circleView.initAnimator(4000);
      /*  list = new ArrayList<>();
        list.add("https://slife-prod.oss-cn-shanghai.aliyuncs.com/slifeio/funding/fundingModule/picture-file-1554359083884.jpg");
        list.add("https://slife-prod.oss-cn-shanghai.aliyuncs.com/slifeio/funding/fundingModule/picture-file-1554190726950.jpg");
        list.add("https://slife-prod.oss-cn-shanghai.aliyuncs.com/slifeio/funding/fundingModule/picture-file-1554360828108.jpg");
        RtlViewPager rtlViewPager=findViewById(R.id.rtlViewPager);
        rtlViewPager.setAdapter(new TextViewPagerAdapter());*/
    }

    class TextViewPagerAdapter extends PagerAdapter {


        TextViewPagerAdapter() {
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object instantiateItem(final ViewGroup viewGroup, int position) {
            View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.img_layout, null);
            ImageView img = mView.findViewById(R.id.img);
            Glide.with(MainActivity.this).load(list.get(position)).into(img);
            try {
                viewGroup.addView(mView, ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return mView;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return String.valueOf(position);
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
