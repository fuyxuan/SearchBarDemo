package com.example.searchbardemob;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	private Context context;
	private String[] textStr;
	private int[] image;
	
	

	public ImageAdapter() {

	}

	public ImageAdapter(Context context, String[] textStr , int[] image) {
		this.context = context;
		this.textStr = textStr;
		this.image = image;
	}

	public ImageAdapter(Context context,ArrayList<String> textList,ArrayList<Integer> imageList) {

		textStr = new String[textList.size()];
		image = new int[imageList.size()];
		this.context = context;

		for (int i = 0; i < textList.size(); i++) {
			textStr[i] = textList.get(i);
			image[i] = imageList.get(i);
		}
	}



	public View getView(int position, View convertView, ViewGroup parent) {


		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater mInflater = LayoutInflater.from(context);
			convertView = mInflater.inflate(R.layout.grid_item, null);
			holder.textView = (TextView) convertView.findViewById(R.id.grid_item_label);
			holder.imageview = (ImageView) convertView.findViewById(R.id.grid_item_image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.textView.setText(textStr[position]);
		holder.imageview.setImageResource(image[position]);
		

		return convertView;
	}

	@Override
	public int getCount() {
		return textStr.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	
	private class ViewHolder {
		ImageView imageview;
		TextView textView;
	}
}