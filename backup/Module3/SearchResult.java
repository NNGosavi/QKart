package QKART_SANITY_LOGIN.Module1;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SearchResult {
    WebElement parentElement;

    public SearchResult(WebElement SearchResultElement) {
        this.parentElement = SearchResultElement;
        parentElement.findElements(By.xpath("//*[@class='MuiCardContent-root css-1qw96cp']"));

    }

    /*
     * Return title of the parentElement denoting the card content section of a search result
     */
    public String getTitleofResult() {
        String titleOfSearchResult = "";
        // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
        // Find the element containing the title (product name) of the search result and

        WebElement search_Result = this.parentElement;

        // search_Result.findElement(By.name("search")).sendKeys("yonex", Keys.ENTER);
        titleOfSearchResult = search_Result
                .findElement(
                        By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 css-yg30e6']"))
                .getText();

        // assign the extract title text to titleOfSearchResult
        return titleOfSearchResult;
    }

    /*
     * Return Boolean denoting if the open size chart operation was successful
     */
    public Boolean openSizechart() {
        try {

            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // Find the link of size chart in the parentElement and click on it

            parentElement.findElement(By.xpath("(//button[text()='Size chart'])[1]")).click();
            Thread.sleep(1000);


            return parentElement.findElement(By.xpath("//*[@role='presentation']//p"))
                    .isDisplayed();
        } catch (Exception e) {
            System.out.println("Exception while opening Size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the close size chart operation was successful
     */
    public Boolean closeSizeChart(WebDriver driver) {
        try {
            //Thread.sleep(2000);
            Actions action = new Actions(driver);

            // Clicking on "ESC" key closes the size chart modal
            action.sendKeys(Keys.ESCAPE);
            action.perform();
            //Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while closing the size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean based on if the size chart exists
     */
    public Boolean verifySizeChartExists() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Check if the size chart element exists. If it exists, check if the text of the
             * element is "SIZE CHART". If the text "SIZE CHART" matches for the element, set status
             * = true , else set to false
             */
            WebElement sizeChartElement =
                    parentElement.findElement(By.xpath(".//button[text()='Size chart']"));

            Thread.sleep(1000);
            if (sizeChartElement.isDisplayed()) {
                String expectedText = "Size chart";

                String actualtext = sizeChartElement.getText();

                if (expectedText.equalsIgnoreCase(actualtext)) {
                   return true;
                }
            }

            /*
             * if(parentElement.findElement(By.xpath("(//button[text()='Size chart'])[1]")).
             * isDisplayed()){
             * 
             * if(("SIZE CHART").equalsIgnoreCase(parentElement.findElement(By.
             * xpath("(//button[text()='Size chart'])[1]")).getText())){ status = true; } }
             */
            /*
             * Check if the size chart element exists. If it exists, check if the text of the
             * element is "SIZE CHART". If the text "SIZE CHART" matches for the element, set status
             * = true , else set to false
             */
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if the table headers and body of the size chart matches the expected values
     */
    public Boolean validateSizeChartContents(List<String> expectedTableHeaders,
            List<List<String>> expectedTableBody, WebDriver driver) {
        Boolean status = true;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2

            List<String> sizeH = new ArrayList<>();

            for (int i = 0; i < 4; i++) {
                sizeH.add(driver.findElement(By.xpath("//th[" + (i + 1) + "]")).getText());
            }

            status = expectedTableHeaders.equals(sizeH);

            List<List<String>> tableR1 = new ArrayList<>();

            for (int j = 0; j < 7; j++) {

                List<String> sizeR1 = new ArrayList<>();

                for (int i = 0; i < 4; i++) {

                    sizeR1.add(driver
                            .findElement(
                                    By.xpath("//tbody//tr[" + (j + 1) + "]//td[" + (i + 1) + "]"))
                            .getText());
                }

                tableR1.add(sizeR1);
            }


            status = expectedTableBody.equals(tableR1);
            /*
             * Locate the table element when the size chart modal is open
             * 
             * Validate that the contents of expectedTableHeaders is present as the table header in
             * the same order
             * 
             * Validate that the contents of expectedTableBody are present in the table body in the
             * same order
             */
            return status;

        } catch (Exception e) {
            System.out.println("Error while validating chart contents");
            return false;
        }
    }

    /*
     * Return Boolean based on if the Size drop down exists
     */
    public Boolean verifyExistenceofSizeDropdown(WebDriver driver) {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // If the size dropdown exists and is displayed return true, else return false
            status = parentElement.findElement(By.xpath("(//select)[1]")).isDisplayed();
            return status;
        } catch (Exception e) {
            return status;
        }
    }
}
