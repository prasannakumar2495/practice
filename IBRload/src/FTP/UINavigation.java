package FTP;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Split;

class UINavigation
{
	static Properties prop;
	static WebDriver driver;
	static WebDriverWait wait;
	long millis=System.currentTimeMillis();  
	java.sql.Date date=new java.sql.Date(millis);  
	String[] current_date= date.toString().split("-");
	
	public void UINavigationMethod() 
	{
		try {

			FtpServer server=new FtpServer();
			server.propMethod();
			driver = new ChromeDriver();
			wait=new WebDriverWait(driver, 30);
			System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			if (server.prop.getProperty("Environment").equalsIgnoreCase("QA01") || server.prop.getProperty("Environment").equalsIgnoreCase("QA1"))
			{
				driver.get("http://vuhgqa01a.ct.com/exemplar/user/LoginEdit.action;jsessionid=nwyz3nq1gASo+uVoHuLMP26v.wa03_qa01#4");

			}

			else if (server.prop.getProperty("Environment").equalsIgnoreCase("QA04") || server.prop.getProperty("Environment").equalsIgnoreCase("QA4"))
			{
				driver.get("http://uhgatlsgqa04.ct.com:8180/exemplar/user/LoginEdit.action#4");

			}

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//input[@type='text']")).sendKeys("sallysales");
			driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Password1");
			driver.findElement(By.xpath("//button[@type='button']")).click();
			
			//entering into the dash board
			driver.findElement(By.xpath("//a[text()='Advanced Search ']")).click();
			//scrolling until SEARCH button
			driver.findElement(By.xpath("//span[@class='fa fa-search']"));
			System.out.println("scrolled until Search button");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='activitySearch'][@name='activitySearch'])[1]"))).click();
			WebElement dropdown=driver.findElement(By.xpath("//select[@name='selectedActivity']"));
			Select sel = new Select(dropdown);
			sel.selectByVisibleText("Renewals (SG)");
			WebElement status=driver.findElement(By.xpath("//select[@name='selectRenewal']"));
			Select sel1 = new Select(status);

			sel1.selectByVisibleText("Ready");
			driver.findElement(By.xpath("//span[@class='fa fa-search']"));
			WebElement renwalPeriod=driver.findElement(By.xpath("//select[@id='GroupSearch_renewalPeriod']"));
			
			/*System.out.println(current_date[1]);*/
			
			if(server.prop.getProperty("Environment").equalsIgnoreCase("QA01") || server.prop.getProperty("Environment").equalsIgnoreCase("QA1"))
			{
				Select sel2 = new Select(renwalPeriod);
				sel2.selectByVisibleText(IBRReport.split2[0]+current_date[1]+current_date[2]);
			}
			else if(server.prop.getProperty("Environment").equalsIgnoreCase("QA04") || server.prop.getProperty("Environment").equalsIgnoreCase("QA4"))
			{
				Select sel2 = new Select(renwalPeriod);
				sel2.selectByVisibleText(IBRReport.split2[0]);
			}

			Thread.sleep(5000);
			driver.findElement(By.xpath("//span[@class='fa fa-search']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='GroupSearchResult_4']"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@name='quotes']"))).click();
			//scrolling until bottom
			driver.findElement(By.xpath("//div[@class='footer-line3']"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'RNQ')]/../td[12]"))).click();
			System.out.println("clicked on copy of RNQ");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='renewalsQuotes']/tbody/tr[1]/td[11]"))).click();
			System.out.println("clicked on the view of RNA");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@class='component--button c-6e92dfc9-2cc0-4fec-8f28-297319a08486 btn--primary btn']")).click();
			driver.findElement(By.xpath("//button[@class='component--button c-8db6e668-baa6-40e3-9f31-4f82d18f950b btn--primary btn']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='component--button c-33b0f037-64a7-4704-90ea-9de943a12620 pull-right btn--primary btn']"))).click();
			
			// in to the RMP
			String RMP_URL=driver.getCurrentUrl();
			System.out.println(RMP_URL);
			driver.findElement(By.xpath("//button[@class='component--button c-30aae9a9-f5f1-41b5-95dd-ebcacfc0440f btn--primary btn']")).click();
			//clicks in renewal manager page
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='component--button c-66fa332d-7056-4be5-a50a-d2a4ddfb57b7 spaceTop15 btn--primary btn']"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='component--button c-7296d1eb-2901-4c36-b235-238d69cfe5db btn--primary btn']"))).click();
			//clicking on action
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@class='dropdown-toggle component--button btn--reset'])[2]"))).click();
			//remove employee
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@class='component--button c-d3034f57-97d3-43ca-98e2-f9334d38831e spaceLeft0 fontWeightNormal btn--link btn'])[2]"))).click();
			//yes or no
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='component--button c-bd2b5525-2051-4d06-b991-f567518408b5 btn--primary btn']"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='component--button c-c6da410a-2349-4e67-932e-f61d9592bedb btn--primary btn']"))).click();
		
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='component--button c-c62e2217-cd04-4b9d-b11d-eec53182ccf1 btn--primary btn']"))).click();

			String federaltaxID=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='form-value'])[4]"))).getText();
			System.out.println(federaltaxID);
			
			//entering to the employer info page directly 
			/*driver.get("http://vuhgqa01a.ct.com/exemplar/apps/renewals-enrollment/index.html#/enrollment/98e7e147-10b7-479b-b75f-eddbe2dfe524/RenewalManager/Summary");*/
			
			//1st click on another plan and again click on the plan required
			driver.findElement(By.xpath("//div[@class='row component--row c-7432d30b-6cc1-4c1a-aa2a-b2a95f920053']/div[1]/div[1]/div[2]/div[1]/div[1]/a")).click();
			driver.findElement(By.xpath("//ul[@class='dropdown__menu item-count-4']/li[3]")).click();
			driver.findElement(By.xpath("//div[@class='row component--row c-7432d30b-6cc1-4c1a-aa2a-b2a95f920053']/div[1]/div[1]/div[2]/div[1]/div[1]/a")).click();
			driver.findElement(By.xpath("//ul[@class='dropdown__menu item-count-4']/li[1]")).click();
		
			driver.findElement(By.xpath("//button[@class='component--button c-724727bf-02b9-4382-bfcf-0ed5481412dc pull-right btn--primary btn']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='component--button c-fe1d80c2-7f96-48ee-ac5d-48a6502647a4 btn--primary btn']"))).click();
		
		/*	//uploading the file
			WebElement upload=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='c-6c5727f6-d367-4ded-a424-66750d2d58ee requiredUpload form-control']")));
			//upload.click();
			upload.sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg");*/
			
			Thread.sleep(60000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='component--col c-c0f36bd3-4de0-4431-bd31-6f6d901483b9 text-right col-md-2']"))).click();
			System.out.println("clicked on finalize button");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='component--button c-acd99360-9f0f-42c9-b7d0-5f05d467385e pull-right spaceLeft15 btn--primary btn']"))).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button)[2]"))).click();
		
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}


