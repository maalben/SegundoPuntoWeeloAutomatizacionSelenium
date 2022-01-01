package com.weelo.demoqa.practice;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static com.weelo.demoqa.util.Utilities.*;

public class Toolsqa {

    public static WebDriver driver;
    final String name = "Pedro";
    final String lastName = "Pablo";
    final String email = "pedro@correo.com";
    final String gender = "Male";
    final String mobileNumber = "3121234567";
    final String dateBirth = "30-04-2010";
    final String subjects = "Physics, Maths, Hindi";
    final String hobbies = "Sports, Music";
    final String file = "original.png";
    final String filePath = "/src/test/resources/image/"+file;
    final String currentAddress = "Calle 32 #12-12 Las Brisas – Medellin";
    final String state = "Haryana";
    final String city = "Karnal";
    final String titleValidation = "Thanks for submitting the form";
    final String invalidFieldColor = "rgba(220, 53, 69, 1)";

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/automation-practice-form");
    }

    @Test
    public void validateSuccessfulCompletionFormFields(){
        driver.findElement(By.id("firstName")).sendKeys(name);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("userEmail")).sendKeys(email);
        selectRadio(driver.findElements(By.xpath("//div[@id='genterWrapper']/div[2]/div")), gender);
        moveScreen(driver, "up");
        driver.findElement(By.id("userNumber")).sendKeys(mobileNumber);
        driver.findElement(By.xpath("//div[@class='react-datepicker__input-container']")).click();
        selectDate(driver, dateBirth);
        selectSubjects(driver.findElement(By.id("subjectsInput")), subjects);
        selectCheckBox(driver, hobbies, ",", "//div[@id='hobbiesWrapper']/div[2]/div");
        selectFile(driver.findElement(By.id("uploadPicture")), filePath);
        driver.findElement(By.id("currentAddress")).sendKeys(currentAddress);
        selectElementList(driver, "react-select-3-input", state);
        selectElementList(driver, "react-select-4-input", city);
        driver.findElement(By.id("submit")).click();
        assertEquals(titleValidation, driver.findElement(By.id("example-modal-sizes-title-lg")).getText());
    }

    @Test
    public void validateSuccessfulCompletionFormFieldsAndVerificationValuesFields(){
        driver.findElement(By.id("firstName")).sendKeys(name);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("userEmail")).sendKeys(email);
        selectRadio(driver.findElements(By.xpath("//div[@id='genterWrapper']/div[2]/div")), gender);
        moveScreen(driver, "up");
        driver.findElement(By.id("userNumber")).sendKeys(mobileNumber);
        driver.findElement(By.xpath("//div[@class='react-datepicker__input-container']")).click();
        selectDate(driver, dateBirth);
        selectSubjects(driver.findElement(By.id("subjectsInput")), subjects);
        selectCheckBox(driver, hobbies, ",", "//div[@id='hobbiesWrapper']/div[2]/div");
        selectFile(driver.findElement(By.id("uploadPicture")), filePath);
        driver.findElement(By.id("currentAddress")).sendKeys(currentAddress);
        selectElementList(driver, "react-select-3-input", state);
        selectElementList(driver, "react-select-4-input", city);
        driver.findElement(By.id("submit")).click();
        assertEquals(titleValidation, driver.findElement(By.id("example-modal-sizes-title-lg")).getText());
        String relativeRute = "//div[@class='modal-content']//table/tbody";
        assertEquals(name + " " + lastName, driver.findElement(By.xpath(relativeRute+"/tr[1]/td[2]")).getText());
        assertEquals(email, driver.findElement(By.xpath(relativeRute+"/tr[2]/td[2]")).getText());
        assertEquals(gender, driver.findElement(By.xpath(relativeRute+"/tr[3]/td[2]")).getText());
        assertEquals(mobileNumber, driver.findElement(By.xpath(relativeRute+"/tr[4]/td[2]")).getText());
        assertEquals(dateValidation(dateBirth), driver.findElement(By.xpath(relativeRute+"/tr[5]/td[2]")).getText());
        assertEquals(subjects, driver.findElement(By.xpath(relativeRute+"/tr[6]/td[2]")).getText());
        assertEquals(hobbies, driver.findElement(By.xpath(relativeRute+"/tr[7]/td[2]")).getText());
        assertEquals(file, driver.findElement(By.xpath(relativeRute+"/tr[8]/td[2]")).getText());
        assertEquals(currentAddress, driver.findElement(By.xpath(relativeRute+"/tr[9]/td[2]")).getText());
        assertEquals(state + " " + city, driver.findElement(By.xpath(relativeRute+"/tr[10]/td[2]")).getText());
    }

    @Test
    public void validateSuccessCompletionFormFieldsRequired(){
        driver.findElement(By.id("firstName")).sendKeys(name);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        selectRadio(driver.findElements(By.xpath("//div[@id='genterWrapper']/div[2]/div")), gender);
        moveScreen(driver, "up");
        driver.findElement(By.id("userNumber")).sendKeys(mobileNumber);
        driver.findElement(By.id("submit")).click();
        assertEquals(titleValidation, driver.findElement(By.id("example-modal-sizes-title-lg")).getText());
        String relativeRute = "//div[@class='modal-content']//table/tbody";
        assertEquals(name + " " + lastName, driver.findElement(By.xpath(relativeRute+"/tr[1]/td[2]")).getText());
        assertEquals(gender, driver.findElement(By.xpath(relativeRute+"/tr[3]/td[2]")).getText());
        assertEquals(mobileNumber, driver.findElement(By.xpath(relativeRute+"/tr[4]/td[2]")).getText());
    }

    @Test
    public void validateFormFieldsRequiredWithoutCompletionSend(){
        driver.findElement(By.id("userEmail")).sendKeys(email);
        moveScreen(driver, "up");
        driver.findElement(By.xpath("//div[@class='react-datepicker__input-container']")).click();
        selectDate(driver, dateBirth);
        selectSubjects(driver.findElement(By.id("subjectsInput")), subjects);
        selectCheckBox(driver, hobbies, ",", "//div[@id='hobbiesWrapper']/div[2]/div");
        selectFile(driver.findElement(By.id("uploadPicture")), filePath);
        driver.findElement(By.id("currentAddress")).sendKeys(currentAddress);
        selectElementList(driver, "react-select-3-input", state);
        selectElementList(driver, "react-select-4-input", city);
        driver.findElement(By.id("submit")).click();
        moveScreen(driver, "down");
        waitOwn(50);
        assertEquals(invalidFieldColor, driver.findElement(By.id("firstName")).getCssValue("border-top-color"));
        assertEquals(invalidFieldColor, driver.findElement(By.id("lastName")).getCssValue("border-top-color"));
        assertEquals(invalidFieldColor, driver.findElement(By.xpath("//label[@for='gender-radio-1']")).getCssValue("color"));
        assertEquals(invalidFieldColor, driver.findElement(By.xpath("//label[@for='gender-radio-2']")).getCssValue("color"));
        assertEquals(invalidFieldColor, driver.findElement(By.xpath("//label[@for='gender-radio-3']")).getCssValue("color"));
        assertEquals(invalidFieldColor, driver.findElement(By.id("userNumber")).getCssValue("border-top-color"));
    }

    @After
    public void close(){
        driver.quit();
    }
}