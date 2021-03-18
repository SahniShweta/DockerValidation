package remoteTracking.dockerValidation;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class chromeTest1 {
	
	@BeforeTest
	public void startDockerScale() throws IOException, InterruptedException {
		File f = new File("output.txt");
		if(f.delete()) {
			System.out.println("File is deleted succsessfully");
		}
		startDocker s = new startDocker();
		s.startFile();
	}
	
	@AfterTest
	public void stopDockerDeleteFile() throws IOException, InterruptedException {
		stopDocker s = new stopDocker();
		s.stopFile();
	}
	@Test
	public void test1() throws MalformedURLException {
		// TODO Auto-generated method stub
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		URL url = new URL("http://localhost:4444/wd/hub");
		RemoteWebDriver driver =  new RemoteWebDriver(url,cap);
		driver.get("http://google.com");
		System.out.println(driver.getTitle());

	}

}
