package view;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class MapFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		String extension = getExtension(f);
		if (f.isDirectory()) {
            return true;
        }
        if (extension != null) {
            if (extension.equals("map") ||
                extension.equals("txt")){
                    return true;
            } else {
                return false;
            }
        }

        return false;
	}
	
	/*
     * Get the extension of a file.
     */  
    private static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
