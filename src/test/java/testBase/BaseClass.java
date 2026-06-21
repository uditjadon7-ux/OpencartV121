package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"Sanity", "Master", "Regression"})
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {

        // Load config file
        FileReader file = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        String executionEnv = p.getProperty("execution_env");

        System.out.println("Execution Env = " + executionEnv);
        System.out.println("OS = " + os);
        System.out.println("Browser = " + br);

        // Normalize input (IMPORTANT FIX)
        if (os != null) os = os.toLowerCase().trim();
        if (br != null) br = br.toLowerCase().trim();

        // ---------------- REMOTE EXECUTION ----------------
        if (executionEnv.equalsIgnoreCase("remote")) {

            DesiredCapabilities capabilities = new DesiredCapabilities();

            // OS Handling
            switch (os) {
                case "windows":
                    capabilities.setPlatform(Platform.WIN11);
                    break;

                case "mac":
                    capabilities.setPlatform(Platform.MAC);
                    break;

                default:
                    throw new RuntimeException("Invalid OS: " + os);
            }

            // Browser Handling
            switch (br) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;

                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;

                default:
                    throw new RuntimeException("Invalid Browser: " + br);
            }

            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444/wd/hub"),
                    capabilities
            );
        }

        // ---------------- LOCAL EXECUTION ----------------
        else {

            switch (br) {

                case "chrome":
                    driver = new ChromeDriver();
                    break;

                case "edge":
                    driver = new EdgeDriver();
                    break;

                case "firefox":
                    driver = new FirefoxDriver();
                    break;

                default:
                    throw new RuntimeException("Invalid Browser: " + br);
            }
        }

        // Common settings
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get(p.getProperty("appURL"));
    }

    @AfterClass(groups = {"Sanity", "Master", "Regression"})
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    // ---------------- UTILITIES ----------------

    public String randomeString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomeNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomeAlphaNumeric() {
        return RandomStringUtils.randomAlphabetic(3)
                + "@"
                + RandomStringUtils.randomNumeric(3);
    }

    public String captureScreen(String tname) throws IOException {

        if (driver == null) {
            throw new RuntimeException("Driver is NULL");
        }

        String timeStamp = java.time.LocalDateTime.now()
                .toString()
                .replace(":", "-");

        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        String targetPath = System.getProperty("user.dir")
                + "/screenshots/"
                + tname + "_" + timeStamp + ".png";

        File targetFile = new File(targetPath);

        sourceFile.renameTo(targetFile);

        return targetPath;
    }
}