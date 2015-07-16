package com.example.searchbardemo;

import com.example.searchbardemo.helper.SharedPreferencesHelper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class BaseActivity extends Activity{
	
	SharedPreferencesHelper config ; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		config = new SharedPreferencesHelper(this);
	}
	
	
	public interface ViewsInterface{
		public void viewsInit();
		public void findViews();
		public void setViews();
		public void setActions();
	}

    @Override  
    protected void onRestart() {  
        super.onRestart();  
    }
    
    @Override  
    protected void onPause() {  
        super.onPause();  
    }
}
