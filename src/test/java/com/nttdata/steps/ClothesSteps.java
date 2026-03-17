package com.nttdata.steps;

import com.nttdata.page.ClothesPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClothesSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    public ClothesSteps(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Hace clic en la subcategoría "Men" del menú lateral
     */
    public void clickSubcategoryMen() {
        wait.until(ExpectedConditions.elementToBeClickable(ClothesPage.linkMen)).click();
    }

}