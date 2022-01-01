package com.weelo.demoqa.practice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.weelo.demoqa.util.Utilities.typeInField;
import static org.junit.Assert.assertEquals;

public class Automationpractice {

    public static WebDriver driver;
    final String productSearch = "Blouse";
    final String productSearchNotFound = "Pijama";

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
    }

    @Test
    public void VerifySearchProductIsDisplayedSuccessfully(){
        typeInField(driver.findElement(By.id("search_query_top")), productSearch);
        driver.findElement(By.name("submit_search")).click();
        assertEquals(productSearch, driver.findElement(By.xpath("//ul[@class='product_list grid row']//h5")).getText());
    }

    @Test
    public void VerifySearchProductNotExists(){
        typeInField(driver.findElement(By.id("search_query_top")), productSearchNotFound);
        driver.findElement(By.name("submit_search")).click();
        assertEquals("No results were found for your search "+ "\""+ productSearchNotFound +"\"",
                driver.findElement(By.xpath("//p[@class='alert alert-warning']")).getText());
    }

    @Test
    public void VerifySearchWithoutCriterion(){
        driver.findElement(By.name("submit_search")).click();
        assertEquals("Please enter a search keyword", driver.findElement(By.xpath("//p[@class='alert alert-warning']")).getText());
    }

    @After
    public void close(){
        driver.quit();
    }
}