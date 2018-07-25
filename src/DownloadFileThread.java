/**
 * DownloadFileThread.java
 * 
 * Created by Marcus Levin - June 2018
 * 		this class is an asynchronous implentation of downloading a file from a URL to
 * 		a drive path using a new thread
 */

import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadFileThread extends Thread {
	
	String 	filename;
	String	path;
	URL		url;
	
	public DownloadFileThread(String filename, String path, String url) throws MalformedURLException {
		this.filename 	= filename;
		this.path		= path;
		this.url 		= new URL(url);
	}
	
	@Override
	public void run() {
		try {
			
			String destination 		= path + filename;
			ReadableByteChannel rbc = Channels.newChannel(url.openStream());
			FileOutputStream fos 	= new FileOutputStream(destination);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}