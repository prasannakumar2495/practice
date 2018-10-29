package FTP;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class IBRReport 
{
	static WebDriver driver;
	static String env;
	static String[] split1;
	static String[] split2;

	public void ibrReport() throws Exception
	{
		FtpServer server=new FtpServer();
		server.propMethod();

		driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		if (server.prop.getProperty("Environment").equalsIgnoreCase("QA01") || server.prop.getProperty("Environment").equalsIgnoreCase("QA1"))
		{
			driver.get("http://vuhgqa01a.ct.com/exemplar/user/LoginEdit.action;jsessionid=nwyz3nq1gASo+uVoHuLMP26v.wa03_qa01#4");
			env="QA01";
		}

		else if (server.prop.getProperty("Environment").equalsIgnoreCase("QA04") || server.prop.getProperty("Environment").equalsIgnoreCase("QA4"))
		{
			driver.get("http://uhgatlsgqa04.ct.com:8180/exemplar/user/LoginEdit.action#4");
			env="QA04";
		}

		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("sallysales");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Password1");
		driver.findElement(By.xpath("//button[@type='button']")).click();

		split1=server.prop.getProperty("file_1").split("\\\\");

		split2 = split1[split1.length-1].split("\\.");

		if (env=="QA01")
		{
			driver.get("http://vuhgqa01a.ct.com/exemplar/rest/renewals/ibrReport/rnwlpId/"+ split2[0]);
		}

		else if (env=="QA04")
		{
			driver.get("http://uhgatlsgqa04.ct.com:8180/exemplar/rest/renewals/ibrReport/rnwlpId/"+ split2[0]);
		}

		for (int i=0; i<=60 ; i++)
		{
			try
			{
				driver.findElement(By.xpath("//body[contains(text(),'Successfully')]")).getText().equalsIgnoreCase("IBR Report Exported Successfully");
				System.out.println("IBR successfully Uploaded in "+ env +" environment");

				break;
			}
			catch(Exception e)
			{
				Thread.sleep(2000);
				driver.navigate().refresh();

			}

		}
		driver.quit();
	}

}
