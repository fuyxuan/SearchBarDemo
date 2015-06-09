package com.example.searchbardemob;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ContentActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content);

		TextView textView = (TextView) findViewById(R.id.tv_content);
		AssetManager assets = getAssets();

		Intent intent = getIntent();
		Bundle bundle = getIntent().getExtras();
		String content_city = bundle.getString("content_city");
		int content_position = intent.getIntExtra("content_position", 1);
		//int content_position = bundle.getInt("content_position");
		HashMap<Integer, Integer> hashMap = (HashMap<Integer, Integer>) bundle.getSerializable("HashMap");
		Log.i("msg", "content_city:" + content_city);
		try {
			if (content_city.equals("台北")) {
				textView.setText(readStream(assets.open("content_taipei.txt")));
			} else if (content_city.equals("桃園")) {
				textView.setText(readStream(assets.open("content_taoyuan.txt")));
			} else if (content_city.equals("新竹")) {
				textView.setText(readStream(assets.open("content_hsinchu.txt")));
			} else if (content_city.equals("苗栗")) {
				textView.setText(readStream(assets.open("content_moli.txt")));
			} else if (content_city.equals("台中")) {
				textView.setText(readStream(assets.open("content_taichung.txt")));
			} else if (content_city.equals("彰化")) {
				textView.setText(readStream(assets.open("content_changhua.txt")));
			} else if (content_city.equals("台南")) {
				textView.setText(readStream(assets.open("content_tainan.txt")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		textView.setMovementMethod(ScrollingMovementMethod.getInstance());
		ImageView imageview = (ImageView) findViewById(R.id.iv_content);
		imageview.setImageResource(hashMap.get(content_position));
		Log.i("msg",content_position+"content_position:"+(hashMap.get(content_position)==null));
		//imageview.setImageResource((R.drawable.image04));

	}

	private String readStream(InputStream is) {
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			int i = is.read();
			while (i != -1) {
				bo.write(i);
				i = is.read();
			}
			return bo.toString();
		} catch (IOException e) {
			return "";
		}
	}
}
