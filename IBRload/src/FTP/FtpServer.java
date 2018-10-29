package FTP;

import java.io.FileInputStream;
import java.util.Properties;

import com.jcraft.jsch.*;

public class FtpServer 
{
	static Properties prop;
	static FileInputStream fis;
	static JSch jsch;
	static java.util.Properties config;
	static Session session;
	static String file1;
	static String file2;
	
	public void propMethod() throws Exception
	{
		prop = new Properties();
		fis= new FileInputStream(".//IBR.properties");
		prop.load(fis);
	}
	
	public void serverMethod() throws JSchException, SftpException, Exception 
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

		ChannelSftp sftpChannel = (ChannelSftp) channel;
		
		System.out.println("Successfully connected to server");
		
		file1 = prop.getProperty("file_1");
		file2 = prop.getProperty("file_2");
		
		if (prop.getProperty("Environment").equalsIgnoreCase("QA01") || prop.getProperty("Environment").equalsIgnoreCase("QA1"))
		{
			sftpChannel.put(file1, "/uhgrenewal/qaload/request");
			sftpChannel.put(file2, "/uhgrenewal/qaload/request");
		}
		
		else if (prop.getProperty("Environment").equalsIgnoreCase("QA04") || prop.getProperty("Environment").equalsIgnoreCase("QA4"))
		{
			sftpChannel.put(file1, "/uhgrenewal/master/request");
			sftpChannel.put(file2, "/uhgrenewal/master/request");
		}
		
		else
		{
			System.out.println("Please enter correct environment");
		}

		sftpChannel.exit();
		session.disconnect();
		System.out.println("File is copied to the server successfully");
		
	}
}
