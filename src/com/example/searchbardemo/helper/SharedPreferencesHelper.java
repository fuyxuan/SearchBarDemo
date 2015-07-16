package com.example.searchbardemo.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SharedPreferencesHelper {
	Context context;
	public SharedPreferencesHelper(Context context){
		this.context = context;
	}


	public void setInt(String key,int value) {
		SharedPreferences preferences = context.getSharedPreferences("searchbardemo", 0);
		Editor edit = preferences.edit();
		edit.putInt(key, value);
		edit.commit();
	}

	public void setString(String key,String val) {
		SharedPreferences preferences = context.getSharedPreferences("searchbardemo", 0);
		Editor edit = preferences.edit();
		edit.putString(key, val);
		edit.commit();
	}
	
	public String getString(String key, String _default) {
		SharedPreferences preferences = context.getSharedPreferences("searchbardemo", 0);
		return preferences.getString(key, _default);
	}

	public int getInt(String key,int _default) {
		SharedPreferences preferences = context.getSharedPreferences("searchbardemo", 0);
		return preferences.getInt(key, _default);
	}

	public void setBoolean(String key, boolean val){
	    SharedPreferences preferences=context.getSharedPreferences("searchbardemo", 0);
		Editor edit = preferences.edit();
		edit.putBoolean(key, val);
		edit.commit();
	}
	
	public boolean getBoolean(String key, boolean _default){
		SharedPreferences preferences = context.getSharedPreferences("searchbardemo", 0);
		return preferences.getBoolean(key, _default);
	}
}
