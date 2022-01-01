package com.weelo.demoqa.util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.lang.Thread.sleep;

public class Utilities {

    private static WebDriverWait wait;

    public static void selectRadio(WebDriver driver, List<WebElement> radios, String value) {
        for(int i=0; i<radios.size(); i++){
            if(radios.get(i).findElement(By.tagName("input")).getAttribute("value").equals(value)){
                radios.get(i).findElement(By.tagName("label")).click();
                break;
            }
        }
    }

    public static void waitOwn(int segundos){
        long time = segundos * 100;
        try {
            sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void typeInField(WebElement field, String value){
        field.click();
        field.clear();
        for (int i = 0; i < value.length(); i++){
            char c = value.charAt(i);
            String s = new StringBuilder().append(c).toString();
            field.sendKeys(s);
            waitOwn(1);
        }
    }

    public static void selectDate(WebDriver driver, String date){
        String[] splitDate = date.split("-");
        String day = splitDate[0];
        int value = Integer.parseInt(day);
        day = String.valueOf(value);
        String month = splitDate[1];
        String year = splitDate[2];

        switch (month){
            case "01":
                month = "January";
                break;

            case "02":
                month = "February";
                break;

            case "03":
                month = "March";
                break;

            case "04":
                month = "April";
                break;

            case "05":
                month = "May";
                break;

            case "06":
                month = "June";
                break;

            case "07":
                month = "July";
                break;

            case "08":
                month = "August";
                break;

            case "09":
                month = "September";
                break;

            case "10":
                month = "October";
                break;

            case "11":
                month = "November";
                break;

            case "12":
                month = "December";
                break;
        }

        selectElementListDate(driver, "//select[@class='react-datepicker__year-select']/option", year);
        selectElementListDate(driver, "//select[@class='react-datepicker__month-select']/option", month);

        List<WebElement> horizontalDay = driver.findElements(By.xpath("//div[@class='react-datepicker__month-container']/div[2]/div/div"));
        for(int horizontal=0; horizontal<horizontalDay.size(); horizontal++){
            if(horizontal < 11 && horizontalDay.get(horizontal).getText().equals(day) && Integer.parseInt(day) > 10){
                continue;
            }else{
                if(horizontalDay.get(horizontal).getText().equals(day)){
                    horizontalDay.get(horizontal).click();
                    break;
                }
            }
        }
    }

    public static void selectSubjects(WebElement element, String subjects) {
        String[] convertedSubjectsArray = subjects.split(",");
        element.click();
        for (String data : convertedSubjectsArray) {
            //typeInField(element, data.trim());
            element.sendKeys(data.trim());
            element.sendKeys(Keys.ENTER);
            }
        }

    public static void selectCheckBox(WebDriver driver, String list, String regex, String locatorList) {
        List<String> userList = new ArrayList<>();
        userList.addAll(Arrays.asList(list.split(regex)));
        List<WebElement> checkBoxs = driver.findElements(By.xpath(locatorList));
        for(int i = 0; i < checkBoxs.size(); i++){
            for(int j=0; j < userList.size(); j++){
                if(driver.findElement(By.xpath(locatorList+"["+(i+1)+"]/label")).getText().equals(userList.get(j).trim())){
                    driver.findElement(By.xpath(locatorList+"["+(i+1)+"]/label")).click();
                    break;
                }
            }
        }
    }

    public static void selectFile(WebElement element, String filePath) {
        String directory = System.getProperty("user.dir");
        String fileRute = directory + filePath;
        element.sendKeys(fileRute);
    }

    public static void selectElementListDate(WebDriver driver, String locator, String value) {
        List<WebElement> list = driver.findElements(By.xpath(locator));
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getText().equals(value)){
                list.get(i).click();
                break;
            }
        }
    }

    public static void selectElementList(WebDriver driver, String locator, String value) {
        WebElement stt = driver.findElement(By.id(locator));
        stt.sendKeys(value);
        stt.sendKeys(Keys.TAB);
    }

    public static void moveScreen(WebDriver driver, String direction){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        switch (direction){
            case "up":
                js.executeScript("javascript:window.scrollBy(0,500)");
                break;

            case "down":
                js.executeScript("javascript:window.scrollBy(0,-500)");
                break;
        }
    }

    public static String dateValidation(String date){
        String[] splitDate = date.split("-");
        String day = splitDate[0];
        int value = Integer.parseInt(day);
        day = String.valueOf(value);
        String month = splitDate[1];
        String year = splitDate[2];

        switch (month){
            case "01":
                month = "January";
                break;

            case "02":
                month = "February";
                break;

            case "03":
                month = "March";
                break;

            case "04":
                month = "April";
                break;

            case "05":
                month = "May";
                break;

            case "06":
                month = "June";
                break;

            case "07":
                month = "July";
                break;

            case "08":
                month = "August";
                break;

            case "09":
                month = "September";
                break;

            case "10":
                month = "October";
                break;

            case "11":
                month = "November";
                break;

            case "12":
                month = "December";
                break;
        }

        return splitDate[0]+" "+month+","+year;
    }

}
