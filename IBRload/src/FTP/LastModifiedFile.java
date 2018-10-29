package FTP;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

	public class LastModifiedFile 
	{
	  public static void main(String[] args) throws UnknownHostException
	  {
			InetAddress myHost = InetAddress.getLocalHost();
		  String file1=getLatestFilefromDir("C:\\Users\\"+ myHost.getHostName().toLowerCase() +"\\Downloads");
		  System.out.println(file1);

	     // Date d = new Date(file1.lastModified());
		 // System.out.println(d);

	  }
	 public static String getLatestFilefromDir(String dirPath){
		    File dir = new File(dirPath);
		    File[] files = dir.listFiles();
		    if (files == null || files.length == 0) 
		    {
		        return null;
		    }

		    File lastModifiedFile = files[0];
		    for (int i = 2; i < files.length; i++) 
		    {
		       if (lastModifiedFile.lastModified() < files[i].lastModified()) 
		       {
		           lastModifiedFile = files[i];
		       }
		    }
		    return lastModifiedFile.toString();
		}
	}


