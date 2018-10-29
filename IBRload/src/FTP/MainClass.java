package FTP;

public class MainClass
{

	public static void main(String[] args) throws Exception
	{
		FtpServer ftp=new FtpServer();
		ftp.propMethod();
		ftp.serverMethod();
		
		
		/*IBRReport report= new IBRReport();
		report.ibrReport();*/
		
		if (ftp.prop.getProperty("UINavigation").equalsIgnoreCase("yes"))
		{
			UINavigation ui = new UINavigation();
			ui.UINavigationMethod();
		}
		
		//verifying the ECMM file
		/*ECMMverification.ecmmVerification();*/
				
		
	}

}
