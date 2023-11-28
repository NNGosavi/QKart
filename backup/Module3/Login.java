package QKART_SANITY_LOGIN.Module1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Login {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/login";

    public Login(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToLoginPage() {
        if (!driver.getCurrentUrl().equals(url)) {
            driver.get(url);
        }
    }

    public Boolean PerformLogin(String Username, String Password) throws InterruptedException {

        Thread.sleep(1000);
        // Find the Username Text Box
        driver.findElement(By.id("username")).sendKeys(Username);

        // Wait for user name to be entered
       Thread.sleep(1000);

        // Find the password Text Box
        driver.findElement(By.id("password")).sendKeys(Password);

        Thread.sleep(1000);
        // Click the login Button
        driver.findElement(By.xpath("//*[text()='Login to QKart']")).click();

        // SLEEP_STMT_13: Wait for Login to Complete
        // Wait for Login action to complete
        Thread.sleep(1000);
      
        return VerifyUserLoggedIn(Username);
    }

    public Boolean VerifyUserLoggedIn(String Username) {
        try {
            // Find the username label (present on the top right of the page)
            WebElement username_label;
            // username_label = this.driver.findElement(By.className("username-text"));
            username_label = driver.findElement(By.className("username-text"));
            return username_label.getText().equals(Username);
        } catch (Exception e) {
            return false;
        }

    }

}
