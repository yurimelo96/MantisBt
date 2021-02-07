package core;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;
import util.EvidenceManager;

public class BaseTest {
	public static WebDriver driver;
	public static Integer evidId = 1;
	public static long currentExecutionId;
	public static String scenarioName;
	public static Scenario sc;

	@Before()
	public static void before(Scenario scenario) {
		System.out.println("<=== cenario iniciado");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
		sc = scenario;
		scenarioName = scenario.getName();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		currentExecutionId = System.currentTimeMillis();
		new EvidenceManager();
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}

	@After
	public static void after() {
		driver.quit();
		EvidenceManager.generateDoc();
	}

}
