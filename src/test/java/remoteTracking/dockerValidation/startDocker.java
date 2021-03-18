package remoteTracking.dockerValidation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.junit.Assert;
import org.testng.annotations.Test;

public class startDocker {
	

	public void startFile() throws IOException, InterruptedException {
		boolean flag = false;
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("cmd /c start dockerup.bat");
		
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
				if(currentLine.contains("registered to the hub and ready to use")) {
				flag=true;
				System.out.println("Found the text");
				break;
				}
				currentLine = reader.readLine();
			}
			reader.close();
		}
		
		Assert.assertTrue(flag);
		runtime.exec("cmd /c start scale.bat");
		Thread.sleep(15000);
		
		
	}

}
