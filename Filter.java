import java.io.*;

class Filter implements FilenameFilter {
  	private String name; // file name
	private String extension; // file extension

	public Filter(String name, String extension) {
    		this.name = name; // current file name
    		this.extension = extension; // current file extension
  	}

  	public boolean accept(File directory, String filename) {
    		boolean ok = true;

    		if (name != null) {  // check if filename matches name
      			ok &= filename.startsWith(name);
    		}

    		if (extension != null) { // check if file extension matches extension
      			ok &= filename.endsWith('.' + extension);
    		}
    		return ok;
  	}
}