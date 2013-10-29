package com.ihhira.android.filechooser;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	protected static final String TAG = "FileChooser";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// FileDialog dialog = new FileDialog(this, Environment
		// .getExternalStorageDirectory().getAbsolutePath());
		// dialog.addFileListener(new FileSelectedListener() {
		//
		// @Override
		// public void fileSelected(File file) {
		// Log.d(TAG, "file: " + file.getAbsolutePath());
		// }
		// });
		// dialog.showDialog();

		((Button) findViewById(R.id.b_select_folder))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						FileChooser dialog = new FileChooser(
								MainActivity.this, "Select a folder",
								FileChooser.DialogType.SELECT_DIRECTORY,
								null);
						dialog.show();
					}
				});

		((Button) findViewById(R.id.b_select_file))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						FileChooser dialog = new FileChooser(
								MainActivity.this, "Select a file",
								FileChooser.DialogType.SELECT_FILE, null);
						dialog.show();
					}
				});

		((Button) findViewById(R.id.b_save_as))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						FileChooser dialog = new FileChooser(
								MainActivity.this, "Save...",
								FileChooser.DialogType.SAVE_AS, null);
						dialog.show();
					}
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
