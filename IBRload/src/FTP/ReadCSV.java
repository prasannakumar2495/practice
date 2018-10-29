package FTP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import au.com.bytecode.opencsv.CSVReader;

public class ReadCSV 
{
	public static void main(String[] args) throws Exception 
	{
		ReadCSV.readCsv();
		
	}
	public static void readCsv() throws Exception
	{
		try (
			/*	File file= new File("D:\\Test.csv");
				FileReader reader = new FileReader(file);
				ReadCSV reader = new ReadCSV();*/
				Reader reader = Files.newBufferedReader(Paths.get("D:\\RenewalsMain\\20180820000003_sam_daily_cancelled_renewals.csv"));
				CSVReader csvReader = new CSVReader(reader);
				) 
		{
			// Read CSV Records One by One into a String array
			String[] nextRecord;
			while ((nextRecord = csvReader.readNext()) != null) 
			{
				// Print the records
				System.out.println(nextRecord[0]);
				System.out.println( nextRecord[1]);
				System.out.println( nextRecord[2]);
				System.out.println( nextRecord[3]);
				System.out.println("==========================");
				break;
			}
		}
	}

}

//delete an employee in the UI,get that employee name
