package remoteTracking.dockerValidation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.junit.Assert;
import org.testng.annotations.Test;

public class stopDocker {
	@Test
	public void stopFile() throws IOException, InterruptedException {
		boolean flag = false;
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("cmd /c start dockerDown.bat");
		
		String file  = "output.txt";
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 45);
		long stopNow = cal.getTimeInMillis();
		Thread.sleep(3000);
		while(System.currentTimeMillis()<stopNow) {
			if(flag) {
				break;
			}
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String currentLine = reader.readLine();
			while(currentLine!=null && !flag) {
				if(currentLine.contains("selenium-hub exited")) {
				flag=true;
				System.out.println("Found the text");
				break;
				}
				currentLine = reader.readLine();
			}
			reader.close();
		}
		
		Assert.assertTrue(flag);
		//runtime.exec("cmd /c start scale.bat");
		Thread.sleep(15000);
		File f = new File("output.txt");
		if(f.delete()) {
			System.out.println("File is deleted succsessfully");
		}
		
		
	}


}
