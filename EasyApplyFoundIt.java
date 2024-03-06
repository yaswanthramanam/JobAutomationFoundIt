// import org.openqa.selenium.By;
// import org.openqa.selenium.JavascriptExecutor;
// import org.openqa.selenium.Keys;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.firefox.FirefoxDriver;
// import org.openqa.selenium.interactions.Actions;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;

// import java.time.Duration;
// import java.util.Iterator;
// import java.util.List;

// public class EasyApplyFoundIt {

//     private final String email;
//     private final String password;
//     private final String keywords;
//     private final String location;
//     private final WebDriver driver;

//     public EasyApplyFoundIt(String email, String password, String keywords, String location, String driverPath) {
//         this.email = email;
//         this.password = password;
//         this.keywords = keywords;
//         this.location = location;
//         System.setProperty("webdriver.gecko.driver", driverPath);
//         this.driver = new FirefoxDriver();
//     }

//     public void loginIfNeeded() {
//         List<WebElement> signInNameElements = driver.findElements(By.id("signInName"));
//         List<WebElement> passwordElements = driver.findElements(By.name("password"));
//         if (!signInNameElements.isEmpty() && !passwordElements.isEmpty()) {
//             signInNameElements.get(0).sendKeys(email);
//             passwordElements.get(0).sendKeys(password);
//             driver.findElement(By.id("signInbtn")).click();
//         }
//     }

//     public void loginFoundIt() {
//         driver.get("https://www.foundit.in/rio/login");
//         loginIfNeeded();
//     }

//     public void jobSearch() {
//         System.out.println("search started");
//         driver.get("https://www.foundit.in/srp/results?sort=1&limit=15&query=Java+Developer&locations=India&experienceRanges=2~2&experience=2&queryDerived=true&quickApplyJobs=true");
//     }

//     public void filter() {
//     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//     wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("srpResultCardContainer")));

//     List<WebElement> jobContainers = driver.findElements(By.className("srpResultCardContainer"));

//     for (WebElement jobContainer : jobContainers) {
//         jobContainer.click();
//         loginIfNeeded();
//         WebElement applyNowBtn;
//         try {
//             applyNowBtn = driver.findElement(By.id("applyNowBtn"));
//         } catch (Exception e) {
//             System.out.println("Apply button not found for this job. Skipping...");
//             continue;
//         }

//         // Get the URL of the apply now button
//         String applyUrl = applyNowBtn.getAttribute("href");
//         if (applyUrl == null || applyUrl.isEmpty()) {
//             System.out.println("Apply button URL not found for this job. Skipping...");
//             continue;
//         }

//         // Open the apply URL in a new tab using JavaScript
//         ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", applyUrl);
//         driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
//         loginIfNeeded();
//         driver.close();
//         driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
//     }
// }


//     public void closeSession() {
//         System.out.println("End of the session, see you later!");
//         driver.quit();
//     }

//     public void apply() {
//         driver.manage().window().maximize();
//         loginFoundIt();
//         jobSearch();
//         filter();
//         closeSession();
//     }

//     public static void main(String[] args) {
//         String email = "yaswanthramanam@gmail.com";
//         String password = "Yaswanth@123";
//         String keywords = "Software Engineer";
//         String location = "India";
//         String driverPath = "./geckodriver"; // Update with the path to your Firefox GeckoDriver executable

//         EasyApplyFoundIt bot = new EasyApplyFoundIt(email, password, keywords, location, driverPath);
//         bot.apply();
//     }
// }
