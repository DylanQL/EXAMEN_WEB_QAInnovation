package com.nttdata.steps;

import com.nttdata.page.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomeSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    public HomeSteps(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Hace clic en el menú de la categoría "Clothes"
     */
    public void clickMenuClothes() {
        // Usamos ExpectedConditions para asegurar que el elemento esté visible y se le pueda hacer clic
        wait.until(ExpectedConditions.elementToBeClickable(HomePage.navbarMenuClothes)).click();
    }

    /**
     * Obtiene el texto del título de Productos Destacados para validar la carga de la página principal
     * @return El texto del encabezado
     */
    public String getProductosDestacadosText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(HomePage.lblProductosDestacados)).getText();
    }


}
