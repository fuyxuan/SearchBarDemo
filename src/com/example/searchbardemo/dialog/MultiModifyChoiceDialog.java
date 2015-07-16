package com.example.searchbardemo.dialog;

import com.example.searchbardemo.BaseActivity.ViewsInterface;
import com.example.searchbardemo.MainActivity;
import com.example.searchbardemo.MainActivity.MultiModifyChoiceStyleCallback;
import com.example.searchbardemo.R;
import com.example.searchbardemo.helper.SharedPreferencesHelper;

import android.R.dimen;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

//多選修改Dialog
public class MultiModifyChoiceDialog extends Dialog {

	private Views views = new Views();
	private Context context;
	private MultiModifyChoiceStyleCallback multiModifyChoiceStyleCallback;
	private SharedPreferencesHelper config;

	public MultiModifyChoiceDialog(Context context, SharedPreferencesHelper config,MultiModifyChoiceStyleCallback multiModifyChoiceStyleCallback) {
		super(context);
		this.context = context;
		setContentView(R.layout.dialog_multi_modify_choice);
		this.multiModifyChoiceStyleCallback = multiModifyChoiceStyleCallback;
		this.config= config;
		views.viewsInit();

	}

	class Views implements ViewsInterface {

		RadioGroup rgTextSize, rgBGColor, rgTextColor;
		RadioGroup rg;
		Button btnSubmit, btnDefault, btnCancel;
		Editor editor;
		RadioButton rbDefaultTextColor,
					rbDefaultTextSize,
					rbDefaultBGColor;
		int idTextSize,idBGColor,idTextColor;

		@Override
		public void viewsInit() {
			findViews();
			setViews();
			setActions();
		}

		@Override
		public void findViews() {
			rgTextSize = (RadioGroup) findViewById(R.id.rg_text_size);
			rgBGColor = (RadioGroup) findViewById(R.id.rg_bg_color);
			rgTextColor = (RadioGroup) findViewById(R.id.rg_text_color);
			btnSubmit = (Button) findViewById(R.id.btn_multi_submit);
			btnDefault = (Button) findViewById(R.id.btn_multi_default);
			btnCancel = (Button) findViewById(R.id.btn_multi_cancel);
			idTextSize = config.getInt("RD_ID_TEXT_SIZE",  R.id.rb_multi_text_size_20);
			idBGColor = config.getInt("RD_ID_BG_COLOR",R.id.rb_multi_bg_color_green);
			idTextColor = config.getInt("RD_ID_TEXT_COLOR", R.id.rb_multi_text_color_blue);
			rbDefaultTextColor = (RadioButton) findViewById(idTextColor);
			rbDefaultTextSize = (RadioButton) findViewById(idTextSize);
			rbDefaultBGColor = (RadioButton) findViewById(idBGColor);

		}

		@Override
		public void setViews() {
			rbDefaultTextColor.setChecked(true);
			rbDefaultTextSize.setChecked(true);
			rbDefaultBGColor.setChecked(true);
		}

		@Override
		public void setActions() {
			//預設值
			btnDefault.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					idTextSize = R.id.rb_multi_text_size_20;
					idBGColor = R.id.rb_multi_bg_color_green;
					idTextColor = R.id.rb_multi_text_color_blue;
					rbDefaultTextColor = (RadioButton) findViewById(idTextColor);
					rbDefaultTextSize = (RadioButton) findViewById(idTextSize);
					rbDefaultBGColor = (RadioButton) findViewById(idBGColor);
					config.setInt( "RD_ID_TEXT_SIZE",idTextSize);
					config.setInt( "RD_ID_BG_COLOR",idBGColor);
					config.setInt( "RD_ID_TEXT_COLOR",idTextColor);
					config.setString("TEXT_SIZE", "20");
					config.setString("BG_COLOR", "#00FF00");
					config.setString("TEXT_COLOR", "#0000FF");
					rbDefaultTextColor.setChecked(true);
					rbDefaultTextSize.setChecked(true);
					rbDefaultBGColor.setChecked(true);
					multiModifyChoiceStyleCallback.modifyStyle("#00FF00", "#0000FF", "20");
					dismiss();
				}

			});
			
		

			
			btnSubmit.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					rg = (RadioGroup) findViewById(R.id.rg_text_size);
					 for(int i = 0;i < rg.getChildCount();i++){
		                    RadioButton rd = (RadioButton) rg.getChildAt(i);  
		                    if(rd.isChecked()){  
		                    	int checkedId = rd.getId();
		                    	config.setInt( "RD_ID_TEXT_SIZE",checkedId);
		                    	switch (checkedId) {
		    					
								case R.id.rb_multi_text_size_15:
									multiModifyChoiceStyleCallback.modifyStyle(config.getString("TEXT_COLOR", "#00FF00"), config.getString("TEXT_COLOR", "#0000FF"), "15");
									config.setString("TEXT_SIZE", "15");
									
									break;
								case R.id.rb_multi_text_size_20:
									multiModifyChoiceStyleCallback.modifyStyle(config.getString("TEXT_COLOR", "#00FF00"), config.getString("TEXT_COLOR", "#0000FF"),   "20");
									config.setString("TEXT_SIZE", "20");
									break;
								case R.id.rb_multi_text_size_25:
									multiModifyChoiceStyleCallback.modifyStyle(config.getString("TEXT_COLOR", "#00FF00"), config.getString("TEXT_COLOR", "#0000FF"),   "25");
									config.setString("TEXT_SIZE", "25");
									break;
								}
		                    }  
		                }  
					
					rg = (RadioGroup) findViewById(R.id.rg_bg_color);
					for (int i = 0; i < rg.getChildCount(); i++) {
						RadioButton rd = (RadioButton) rg.getChildAt(i);
						if (rd.isChecked()) {
							int checkedId = rd.getId();
							config.setInt( "RD_ID_BG_COLOR",checkedId);
							switch (checkedId) {
							case R.id.rb_multi_bg_color_blue:
								multiModifyChoiceStyleCallback.modifyStyle("#0000FF", config.getString("TEXT_COLOR", "#0000FF"),config.getString("TEXT_SIZE", "20"));
								config.setString("BG_COLOR", "#0000FF");
								break;
							case R.id.rb_multi_bg_color_green:
								multiModifyChoiceStyleCallback.modifyStyle("#00FF00", config.getString("TEXT_COLOR", "#0000FF"),config.getString("TEXT_SIZE", "20"));
								config.setString("BG_COLOR", "#00FF00");
								break;
							case R.id.rb_multi_bg_color_red:
								multiModifyChoiceStyleCallback.modifyStyle("#FF0000", config.getString("TEXT_COLOR", "#0000FF"),config.getString("TEXT_SIZE", "20"));
								config.setString("BG_COLOR", "#FF0000");
								break;
							}
						}
					}
					
					rg = (RadioGroup) findViewById(R.id.rg_text_color);
					for (int i = 0; i < rg.getChildCount(); i++) {
						RadioButton rd = (RadioButton) rg.getChildAt(i);
						if (rd.isChecked()) {
							int checkedId = rd.getId();
							config.setInt( "RD_ID_TEXT_COLOR",checkedId);
							switch (checkedId) {
							case R.id.rb_multi_text_color_blue:
								multiModifyChoiceStyleCallback.modifyStyle(config.getString("BG_COLOR","#00FF00"),  "#0000FF", config.getString("TEXT_SIZE", "20"));
								config.setString("TEXT_COLOR", "#0000FF");
								break;
							case R.id.rb_multi_text_color_green:
								multiModifyChoiceStyleCallback.modifyStyle( config.getString("BG_COLOR", "#00FF00"),"#00FF00",  config.getString("TEXT_SIZE", "20"));
								config.setString("TEXT_COLOR", "#00FF00");
								break;
							case R.id.rb_multi_text_color_red:
								multiModifyChoiceStyleCallback.modifyStyle( config.getString("BG_COLOR", "#00FF00"),"#FF0000",  config.getString("TEXT_SIZE", "20"));
								config.setString("TEXT_COLOR", "#FF0000");
								break;
							}
						}
					}
					

					dismiss();
				}

			});
			
			//取消
			btnCancel.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					
					rbDefaultTextColor = (RadioButton) findViewById(config.getInt("RD_ID_TEXT_COLOR",R.id.rb_multi_bg_color_blue));
					rbDefaultTextSize = (RadioButton) findViewById(config.getInt("RD_ID_TEXT_SIZE",R.id.rb_multi_text_size_20));
					rbDefaultBGColor = (RadioButton) findViewById(config.getInt("RD_ID_BG_COLOR",R.id.rb_multi_bg_color_green));
					rbDefaultTextColor.setChecked(true);
					rbDefaultTextSize.setChecked(true);
					rbDefaultBGColor.setChecked(true);
					dismiss();
				}

			});

		}

	}

}
