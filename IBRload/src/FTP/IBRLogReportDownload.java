package FTP;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class IBRLogReportDownload 
{


	static Properties prop;
	static FileInputStream fis;
	static JSch jsch;
	static java.util.Properties config;
	static Session session;
	static String file1;
	static String file2;
	static ChannelSftp sftpChannel;
	static String Request_IDReq;

	public static void main(String[] args) throws Exception
	{
		jsch = new JSch();
		config = new java.util.Properties();

		config.put("StrictHostKeyChecking", "no");
		JSch.setConfig(config);
		session = jsch.getSession("ESBRFTPINT", "10.2.1.210", 22);
		session.setPassword("WQX9Tj[V");

		session.connect();	

		Channel channel = session.openChannel("sftp");
		channel.connect();

		sftpChannel = (ChannelSftp) channel;

		System.out.println("Successfully connected to server  ");
		
		/*sftpChannel.get("", dst);*/
		
		Request_IDReq=prop.getProperty("Request_ID");

		
		String logpath="/uhgrenewal/master/response/"+prop.getProperty("Request_IDReq")+"*"+".xlsx";

		sftpChannel.get(logpath, "D:\\New folder");
		
/*		sftpChannel.get("/uhgrenewal/master/response/00000000000KP*.xlsx", "D:\\New folder");
*/		
		System.out.println("Done");
	}

	public static String getLatestFilefromDir() throws SftpException{
		File dir = new File(sftpChannel.get("/uhgrenewal/master/response/").toString());
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
		return lastModifiedFile.getName().toString();

	}
}
