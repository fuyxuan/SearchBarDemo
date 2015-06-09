package com.example.searchbardemob;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;
 
public class MainActivity extends Activity {
 
	private GridView gridView;
	private EditText edittext;
	private int textlength = 0;
	private Context context;
	private HashMap<Integer, Integer> hashMap;
	private String[] text = new String[] { "台北", "桃園","新竹","苗栗","台中","彰化", "台南",
											"台北", "桃園","新竹","苗栗","台中","彰化", "台南",
											"台北", "桃園","新竹","苗栗","台中","彰化","台南",
											"台北", "桃園","新竹","苗栗","台中","彰化" };
	private int[] image = { 
					R.drawable.image01, R.drawable.image02,
					R.drawable.image03, R.drawable.image04,
					R.drawable.image05, R.drawable.image06,
					R.drawable.image07, R.drawable.image08,
					R.drawable.image09, R.drawable.image10,
					R.drawable.image11, R.drawable.image12,
					R.drawable.image13, R.drawable.image14,
					R.drawable.image15, R.drawable.image01,
					R.drawable.image01, R.drawable.image02,
					R.drawable.image03, R.drawable.image04,
					R.drawable.image05, R.drawable.image06,
					R.drawable.image07, R.drawable.image08,
					R.drawable.image09, R.drawable.image10,
					R.drawable.image11, R.drawable.image12,
					R.drawable.image13, R.drawable.image14,
					R.drawable.image15, R.drawable.image01, 
					};
	private ArrayList<String> text_sort = new ArrayList<String>();
	private ArrayList<Integer> image_sort = new ArrayList<Integer>();
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.context = this;
		hashMap = new HashMap<Integer,Integer>();
		
		
		for(int i=0;i<text.length;i++){
			text_sort.add(text[i]);
			image_sort.add(image[i]);
			hashMap.put(i, image[i]);
		}
		
		gridView = (GridView) findViewById(R.id.gridView1);
		edittext = (EditText) findViewById(R.id.editText);
		ImageAdapter  imageAdapter = new ImageAdapter(this, text_sort,image_sort);
		imageAdapter.notifyDataSetChanged();
		gridView.setAdapter(imageAdapter);
		
		
		edittext.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count,int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,int count) {
				textlength = edittext.getText().length();
				
				text_sort.clear();
				image_sort.clear();
				hashMap.clear();
				for (int i = 0; i < text.length; i++) {
					if (textlength <= text[i].length()) { //文字搜尋
						if(text[i].indexOf(edittext.getText().toString()) > 0){
//						if (edittext.getText().toString().equalsIgnoreCase((String) text[i].subSequence(0,textlength))) {
							text_sort.add(text[i]);
							image_sort.add(image[i]);
							
							
						}
					}
				}
				
				Log.i("msg","image_sort:"+image_sort);
				
				
				
				hashMap.clear();
				ImageAdapter imageAdapter = new ImageAdapter(context, text_sort , image_sort);
				Log.i("msg", "imageAdapter:"+imageAdapter);
				imageAdapter.notifyDataSetChanged();
				gridView.setAdapter(imageAdapter);
				
				for(int i=0;i<image_sort.size();i++){
					hashMap.put(i, image_sort.get(i));
				}
				
			}
		});
		
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@SuppressLint("NewApi") @Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) {
				
				Log.i("msg","grid:"+position);
				Toast.makeText(getApplicationContext(),((TextView) view.findViewById(R.id.grid_item_label)).getText(), Toast.LENGTH_SHORT).show();
				
				String str = ((TextView) view.findViewById(R.id.grid_item_label)).getText().toString();
				Intent intent = new Intent();
				intent.setClass(MainActivity.this , ContentActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("content_city",str);
				intent.putExtra("content_position",position); 
				bundle.putSerializable("HashMap",hashMap);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
 
	}
 
}