package com.ihhira.android.filechooser;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

public class FileChooser implements OnClickListener {
	enum DialogType {
		SELECT_FILE, SELECT_DIRECTORY, SAVE_AS
	}

	private static final String TAG = "FileChooserDialog";

	private static final String PARENT_FILE_NAME = "..";

	private File currentFile;
	private ArrayList<String> fileList;

	private FilenameFilter fileFilter = new FilenameFilter() {

		@Override
		public boolean accept(File dir, String filename) {
			File file = new File(dir, filename);
			return !file.isHidden();
		}
	};

	private Context context;
	private String title;

	private Dialog currentDialog;
	private DialogType dialogType = DialogType.SELECT_FILE;

	public FileChooser(Context context, String title,
			DialogType dialogType, File startingFile) {
		this.dialogType = dialogType;
		this.title = title;
		this.context = context;
		if (startingFile == null || !startingFile.exists()) {
			startingFile = Environment.getExternalStorageDirectory();
		}
		if (!startingFile.exists()) {
			startingFile = new File("/");
		}
		if (startingFile.exists()) {
			this.currentFile = startingFile;
		}
	}

	public void show() {
		loadFilelist();
	}

	private void loadFilelist() {
		if (currentDialog != null) {
			currentDialog.dismiss();
		}

		Builder builder = new Builder(context);
		builder.setTitle(title + "\n" + currentFile.getAbsolutePath());

		addAdapter(context, builder);

		if (dialogType == DialogType.SELECT_DIRECTORY
				|| dialogType == DialogType.SAVE_AS) {
			addPositiveButton(context, builder);
		}
		currentDialog = builder.show();
	}

	private void addAdapter(Context context, Builder builder) {
		fileList = new ArrayList<String>();

		if (currentFile.getParentFile() != null) {
			fileList.add(PARENT_FILE_NAME);
		}

		File[] childFiles = currentFile.listFiles(fileFilter);
		if (childFiles != null) {
			Arrays.sort(childFiles, new Comparator<File>() {

				@Override
				public int compare(File lhs, File rhs) {
					if (lhs.isDirectory() && rhs.isFile()) {
						return -1;
					} else if (rhs.isDirectory() && lhs.isFile()) {
						return 1;
					}
					return lhs.getName().compareToIgnoreCase(rhs.getName());
				}
			});
			for (File childFile : childFiles) {
				fileList.add(childFile.getName());
			}
		}

		ListAdapter adapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_1, fileList);
		builder.setAdapter(adapter, this);
	}

	private void addPositiveButton(final Context context, Builder builder) {
		String positiveButtonText = "Ok";

		OnClickListener positiveButtonClickListener = new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				if (dialogType == DialogType.SAVE_AS) {
					loadFilenameDialog("");
				} else {
					completeSelection("");
				}

			}
		};
		builder.setPositiveButton(positiveButtonText,
				positiveButtonClickListener);
	}

	protected void completeSelection(String filename) {
		currentDialog.dismiss();
		Log.d(TAG, "Selected: " + currentFile.getAbsolutePath()
				+ File.pathSeparator + filename);
	}

	protected void loadFilenameDialog(String filename) {
		if (currentDialog != null) {
			currentDialog.dismiss();
		}

		Builder builder = new Builder(context);
		builder.setTitle("Please type a filename");

		final EditText et = new EditText(context);
		et.setHint("filename");
		et.setText(filename);
		builder.setView(et);

		builder.setPositiveButton("Go", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				String filename = et.getText().toString();
				if (filename == null || filename.length() <= 0) {
					Toast.makeText(context, "Invalid name", Toast.LENGTH_SHORT)
							.show();
				} else {
					completeSelection(filename);
				}
			}
		});

		builder.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface arg0) {
				loadFilelist();
			}
		});
		currentDialog = builder.show();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if (fileList.get(which).equals(PARENT_FILE_NAME)) {
			currentFile = currentFile.getParentFile();
			loadFilelist();
		} else {
			File selectedFile = new File(currentFile, fileList.get(which));
			if (selectedFile.isDirectory()) {
				currentFile = selectedFile;
				loadFilelist();
			} else {
				if (dialogType == DialogType.SAVE_AS) {
					loadFilenameDialog(selectedFile.getName());
				} else {
					completeSelection(selectedFile.getName());
				}
			}
		}
	}

}
