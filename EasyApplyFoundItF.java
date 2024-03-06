import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class EasyApplyFoundItF {

    private final String email;
    private final String password;
    private final String keywords;
    private final String location;
    private final WebDriver driver;

    public EasyApplyFoundItF(String email, String password, String keywords, String location, String driverPath) {
        this.email = email;
        this.password = password;
        this.keywords = keywords;
        this.location = location;
        System.setProperty("webdriver.gecko.driver", driverPath);
        this.driver = new FirefoxDriver();
    }
    public void loginIfNeeded(){
        List<WebElement> signInNameElements = driver.findElements(By.id("signInName"));
        List<WebElement> passwordElements = driver.findElements(By.name("password"));
        if (!signInNameElements.isEmpty() && !passwordElements.isEmpty()) {
            signInNameElements.get(0).sendKeys(email);
            passwordElements.get(0).sendKeys(password);
            driver.findElement(By.id("signInbtn")).click();
        }
    }
    
    public void loginFoundIt() {
        driver.get("https://www.foundit.in/rio/login");
        loginIfNeeded();
    }

    public void jobSearch() {
        driver.get("https://www.foundit.in/srp/results?sort=1&limit=15&query=Software+Developer&locations=india&experienceRanges=0%7E1%2C1%7E2&experience=2&queryDerived=true&salaryRanges=1000000%7E2000000%2C2000000%7E3000000%2C3000000%7E5000000%2C5000000%7E*&quickApplyJobs=true");
    }


    public void filter() {
        boolean hasJobs= true;
        while(hasJobs){
        try {
            TimeUnit.SECONDS.sleep(5); // Wait for 40 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        int length= driver.findElements(By.className("srpResultCardContainer")).size();
        if(length==0){
            hasJobs=false;
            return;
        }
        for(int i=0; i<length;i++){
            driver.get("https://www.foundit.in/srp/results?sort=1&limit=15&query=Software+Developer&locations=india&experienceRanges=0%7E1%2C1%7E2&experience=2&queryDerived=true&salaryRanges=1000000%7E2000000%2C2000000%7E3000000%2C3000000%7E5000000%2C5000000%7E*&quickApplyJobs=true&searchId=4c81c1e7-1284-4fb1-99f9-4429f680cd13");
            try {
                TimeUnit.SECONDS.sleep(5); // Wait for 40 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            length=driver.findElements(By.className("srpResultCardContainer")).size();
            System.out.println(length+ " : In for loop");
            if(length==0){
                hasJobs=false;
                return;
            }
            WebElement jobContainer = driver.findElements(By.className("srpResultCardContainer")).get(0);
            jobContainer.click();
            loginIfNeeded();
            try {
                TimeUnit.SECONDS.sleep(5); // Wait for 40 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // Locate the button element
            driver.findElement(By.id("applyNowBtn")).click();
            loginIfNeeded();
            try {
                TimeUnit.SECONDS.sleep(10); // Wait for 40 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            


        }
    }
    }

    public void closeSession() {
        System.out.println("End of the session, see you later!");
        driver.quit();
    }

    public void apply() {
        driver.manage().window().maximize();
        loginFoundIt();
        jobSearch();
        filter();
        // findOffers();
        closeSession();
    }

    public static void main(String[] args) {
        String email = "yaswanthramanam@gmail.com";
        String password = "Yaswanth@123";
        String keywords = "Software Engineer";
        String location = "India";
        String driverPath = "./geckodriver"; // Update with the path to your ChromeDriver executable

        EasyApplyFoundItF bot = new EasyApplyFoundItF(email, password, keywords, location, driverPath);
        bot.apply();
    }
}
