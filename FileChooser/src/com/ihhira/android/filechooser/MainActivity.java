package com.ihhira.android.filechooser;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ihhira.android.filechooser.FileChooser.DialogType;
import com.ihhira.android.filechooser.FileChooser.FileSelectionCallback;

public class MainActivity extends Activity implements FileSelectionCallback {

	protected static final String TAG = "FileChooser";
	TextView tvStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvStatus = (TextView) findViewById(R.id.tv_status);

		Button bSelectFolder = (Button) findViewById(R.id.b_select_folder);
		bSelectFolder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				FileChooser dialog = new FileChooser(MainActivity.this,
						"Select a folder", DialogType.SELECT_DIRECTORY, null);
				dialog.show(MainActivity.this);
			}
		});

		Button bSelectFile = (Button) findViewById(R.id.b_select_file);
		bSelectFile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				FileChooser dialog = new FileChooser(MainActivity.this,
						"Select a file", DialogType.SELECT_FILE, null);
				dialog.show(MainActivity.this);
			}
		});

		Button bSaveAs = (Button) findViewById(R.id.b_save_as);
		bSaveAs.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				FileChooser dialog = new FileChooser(MainActivity.this,
						"Save...", DialogType.SAVE_AS, null);
				dialog.show(MainActivity.this);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onSelect(File file) {
		String text = ">> " + file.getAbsolutePath();
		Log.d(TAG, text);
		tvStatus.append(text);
	}

}
