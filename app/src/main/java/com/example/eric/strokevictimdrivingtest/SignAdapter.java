package com.example.eric.strokevictimdrivingtest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class SignAdapter extends BaseAdapter{

        private Context mContext;
        public Integer[] mThumbIds;

        public SignAdapter(Context c, Integer[] mThumbIds) {
            this.mThumbIds = mThumbIds;
            this.mContext = c;
        }

        public int getCount() {

            return 12;
        }

        public Object getItem(int position) {

            return null;
        }

        public long getItemId(int position) {

            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(480, 370));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }
}
