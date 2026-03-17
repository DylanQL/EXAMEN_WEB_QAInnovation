package com.nttdata.steps;

import com.nttdata.page.MenPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MenSteps {

    private WebDriver driver;
    private WebDriverWait wait;
    public MenSteps(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Espera a que el enlace del primer producto sea clickeable y hace clic
     */
    public void clickFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(MenPage.firstProductLink)).click();
    }

}
