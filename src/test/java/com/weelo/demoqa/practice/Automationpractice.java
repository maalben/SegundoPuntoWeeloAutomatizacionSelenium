package com.weelo.demoqa.practice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Automationpractice {

    public static WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
    }

    @Test
    public void VerifySearchProductIsDisplayedSuccessfully(){
        final String productSearch = "Blouse";
        driver.findElement(By.id("search_query_top")).sendKeys(productSearch);
        driver.findElement(By.name("submit_search")).click();
        wait.until(visibilityOfElementLocated(By.xpath("//ul[@class='product_list grid row']//h5")));
        assertEquals(productSearch, driver.findElement(By.xpath("//ul[@class='product_list grid row']//h5")).getText());
    }

    @Test
    public void VerifySearchProductNotExists(){
        final String productSearchNotFound = "Pijama";
        driver.findElement(By.id("search_query_top")).sendKeys(productSearchNotFound);
        driver.findElement(By.name("submit_search")).click();
        wait.until(visibilityOfElementLocated(By.xpath("//p[@class='alert alert-warning']")));
        assertEquals("No results were found for your search "+ "\""+ productSearchNotFound +"\"",
                driver.findElement(By.xpath("//p[@class='alert alert-warning']")).getText());
    }

    @Test
    public void VerifySearchWithoutCriterion(){
        driver.findElement(By.name("submit_search")).click();
        wait.until(visibilityOfElementLocated(By.xpath("//p[@class='alert alert-warning']")));
        assertEquals("Please enter a search keyword", driver.findElement(By.xpath("//p[@class='alert alert-warning']")).getText());
    }

    @Test
    public void VerifySearchWithMoreOneProduct(){
        final String productSearch = "Printed";
        final int quantityProductExpected = 5;
        driver.findElement(By.id("search_query_top")).sendKeys(productSearch);
        driver.findElement(By.name("submit_search")).click();
        wait.until(visibilityOfElementLocated(By.xpath("//ul[@class='product_list grid row']/li")));
        assertEquals(quantityProductExpected, driver.findElements(By.xpath("//ul[@class='product_list grid row']/li")).size());
    }

    @After
    public void close(){
        driver.quit();
    }
}