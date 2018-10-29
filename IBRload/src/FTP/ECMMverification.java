package FTP;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ECMMverification {

	static Properties prop;
	static WebDriver driver;

	public static void ecmmVerification() throws Exception
	{

		System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", "C:\\Users\\prasannaan\\Downloads");
		chromePrefs.put("safebrowsing.enabled", "true"); 
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		WebDriver driver = new ChromeDriver(cap);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait=new WebDriverWait(driver, 30);

		driver.get("http://10.1.2.24:8080/#/search/advanced");

		driver.findElement(By.name("j_username")).sendKeys("user");
		driver.findElement(By.name("j_password")).sendKeys("Connecture");
		driver.findElement(By.xpath("//input[@class='btn btn-lg btn-primary btn-block']")).click();

		driver.findElement(By.xpath("//input[@class='form-control ng-pristine ng-invalid ng-invalid-required']")).sendKeys("999999999");
		driver.findElement(By.xpath("//button[text()='Search']")).click();

		/*for (int i=0; i<=5; i++)
		{
			try
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//td[contains(text(),'FILE_BASED_DELIVERY')])[1]/..//a[contains(text(),'.csv')]"))).click();
				Thread.sleep(2000);
				break;
			}
			catch(Exception e)
			{
				driver.findElement(By.xpath("//button[text()='Search']")).click();
			}
		}*/

		for (int i=0; i<=5; i++)
		{
			try
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='HTTP_DELIVERY']/..//td[1]/a[contains(text(),'UHG_GroupECMM')]"))).click();
				Thread.sleep(2000);
				break;
			}
			catch(Exception e)
			{
				driver.findElement(By.xpath("//button[text()='Search']")).click();
			}
		}
		InetAddress myHost = InetAddress.getLocalHost();

		try {

			File fXmlFile = new File(LastModifiedFile.getLatestFilefromDir("C:\\Users\\"+ myHost.getHostName().toLowerCase() +"\\Downloads"));
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("urn:SAMGroupECMMRequest");

			for (int temp = 0; temp < nList.getLength(); temp++) 
			{

				Node nNode = nList.item(temp);						

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println(eElement.getElementsByTagName("GroupName").item(0).getTextContent());

					System.out.println(eElement.getElementsByTagName("ConnectureID").item(0).getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.quit();
	}

}
