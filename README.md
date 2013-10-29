android-file-chooser
====================

This is a file chooser for android platform.



Sample code:
============

    FileChooser fileChooser = new FileChooser(context, title, DialogType.SELECT_FILE, null);
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


