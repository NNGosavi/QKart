package QKART_SANITY_LOGIN.Module1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app";

    public Home(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHome() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    public Boolean PerformLogout() throws InterruptedException {
        try {
            // Find and click on the Logout Button
            WebElement logout_button = driver.findElement(By.className("MuiButton-text"));
            logout_button.click();

            // Wait for Logout to Complete
            Thread.sleep(3000);

            return true;
        } catch (Exception e) {
            // Error while logout
            return false;
        }
    }

    /*
     * Returns Boolean if searching for the given product name occurs without any errors
     */
    public Boolean searchForProduct(String product) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Clear the contents of the search box and Enter the product name in the search
            // box
            driver.findElement(By.name("search")).clear();
            Thread.sleep(1000);
            driver.findElement(By.name("search")).sendKeys(product, Keys.ENTER);

            return true;
        } catch (Exception e) {
            System.out.println("Error while searching for a product: " + e.getMessage());
            return false;
        }
    }

    /*
     * Returns Array of Web Elements that are search results and return the same
     */
    public List<WebElement> getSearchResults() {
        List<WebElement> searchResults = new ArrayList<WebElement>() {};
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Find all webelements corresponding to the card content section of each of
            // search results
            
            searchResults = driver.findElements(By.xpath("//div[@class='MuiCardContent-root css-1qw96cp']"));

            // driver.findElement(By.xpath("//p[contains(text(),'YONEX')]"));
            return searchResults;
        } catch (Exception e) {
            System.out.println("There were no search results: " + e.getMessage());
            return searchResults;

        }
    }

    /*
     * Returns Boolean based on if the "No products found" text is displayed
     */
    public Boolean isNoResultFound() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Check the presence of "No products found" text in the web page. Assign status
            // = true if the element is *displayed* else set status = false
            driver.findElement(By.name("search")).clear();
            Thread.sleep(1000);
            driver.findElement(By.name("search")).sendKeys("Gesundheit", Keys.ENTER);

            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if add product to cart is successful
     */
    public Boolean addProductToCart(String productName) {
        boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Iterate through each product on the page to find the WebElement corresponding to the
             * matching productName
             * 
             * Click on the "ADD TO CART" button for that element
             * 
             * Return true if these operations succeeds
             */
            // add the product to cart by clicking on add to cart of the required product.
            if (!status) {
                driver.findElement(By.xpath("//p[text()='" + productName
                        + "']//parent::div//following-sibling::div//button")).click();
                return true;
            }
            System.out.println("Unable to add product to cart");
            return status;

        } catch (Exception e) {
            System.out.println("Exception while performing add to cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting the status of clicking on the checkout button
     */
    public Boolean clickCheckout() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find and click on the the Checkout button

            if (!status) {

                driver.findElement(By.xpath(
                        "//*[@class='cart MuiBox-root css-0']//button/text()//ancestor::button"))
                        .click();

                return (!status);
            }

            return status;
        } catch (Exception e) {
            System.out.println("Exception while clicking on Checkout: " + e.getMessage());
            return status;
        }
    }

    /*
     * Return Boolean denoting the status of change quantity of product in cart operation
     */
    public Boolean changeProductQuantityinCart(String productName, int quantity) {
        boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 06: MILESTONE 5

            // Find the item on the cart with the matching productName

            // Increment or decrement the quantity of the matching product until the current
            // quantity is reached (Note: Keep a look out when then input quantity is 0,
            // here we need to remove the item completely from the cart)

            List<String> productName1 = new ArrayList<>();

            productName1.add(driver
                    .findElement(By.xpath("//*[@class='MuiBox-root css-1gjj37g']/child::div[1]"))
                    .getText()+" ");
            System.out.println(productName1);

            if (!status) {
                System.out.println(productName1.size());
                // quantity = 0;

                for (int i = 0; i < quantity; i++) {

                    if (!(productName1.contains(productName))) {
                        break;
                    } else {
                        String z = driver
                                .findElement(By.xpath("//*[text()='" + productName
                                        + "']/parent::div//div[@class='MuiBox-root css-olyig7']"))
                                .getText();

                        int b = Integer.parseInt(z);

                        if (b == quantity) {
                            break;
                        } else if (b > quantity) {
                            // click on minus "-"
                            for (int j = 0; j < quantity; j++) {
                                driver.findElement(By.xpath(
                                        "//div[@class='cart MuiBox-root css-0']//div[text()='"
                                                + productName + "']/parent::div//button[1]"))
                                        .click();
                                b--;
                                Thread.sleep(2000);
                                if (b == 0) {
                                    productName1.remove(productName);
                                    break;
                                } else if (quantity == b) {
                                    break;
                                }

                            }
                        } else if (b < quantity) {

                            // click on add "+"
                            for (int j = 0; j < quantity; j++) {
                                driver.findElement(By.xpath(
                                        "//div[@class='cart MuiBox-root css-0']//div[text()='"
                                                + productName + "']/parent::div//button[2]"))
                                        .click();
                                b++;
                                Thread.sleep(2000);
                                if (quantity == b) {
                                    break;
                                }
                            }

                        }

                    }
                }

            }
            return status;
        } catch (Exception e) {
            if (quantity == 0)
                return true;
            System.out.println("exception occurred when updating cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the cart contains items as expected
     */
    public Boolean verifyCartContents(List<String> expectedCartContents) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 07: MILESTONE 6

            // Get all the cart items as an array of webelements

            // Iterate through expectedCartContents and check if item with matching product
            // name is present in the cart

            List<String> productInCart = new ArrayList<>();

            for (int i = 0; i < expectedCartContents.size(); i++) {

                productInCart.add(driver.findElement(By
                        .xpath("//*[@class='MuiBox-root css-1gjj37g']/child::div[" + (i + 1) + "]"))
                        .getText());

            }

            expectedCartContents.equals(productInCart);

            return true;

        } catch (Exception e) {
            System.out.println("Exception while verifying cart contents: " + e.getMessage());
            return false;
        }
    }
}
