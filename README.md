android-file-chooser
====================

A Simple file chooser for android platform. No more extra activity is required. It'll use in activity dialog to show the contents. Read installation details for further use.

Features:
=========
1. Easy file selection
2. Directory selection
3. Save as dialog to save new file
4. New file will be created if it doesn't exist
5. While saving new file, if there exists a file with the same name, it'll ask for permission to overwrite.
6. Callback for handling file selection event.



Sample code:
============

    FileChooser fileChooser = new FileChooser(YOUR_ACTIVITY.this, title, DialogType.SELECT_FILE, null);
    FileSelectionCallback callback = new FileSelectionCallback() {
    	
        @Override
        public void onSelect(File file) {
            //Do something with the selected file
        }
    };
    fileChooser.show(callback);

There are three types of dialogs:

    DialogType.SELECT_FILE, 
    DialogType.SELECT_DIRECTORY, 
    DialogType.SAVE_AS
    

In SAVE_AS dialog if any existing file is selected then it will also ask for overwrite permission.


Installation:
=============

1. Include the FileChooser library jar ( "/bin/FileChooser.jar" ) to your projects libs ( "PROJECT_ROOT/libs" ) directory. 
2. Link the library jar file from java build path.
3. Tada, You may try now the sample code from above to check if it is working.


