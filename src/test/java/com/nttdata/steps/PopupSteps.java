package com.nttdata.steps;

import com.nttdata.page.PopupPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PopupSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    public PopupSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    /**
     * Obtiene el nombre del producto en el popup
     */
    public String getProductName() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(PopupPage.lblProductName));
        return element.getText();
    }

    /**
     * Obtiene la cantidad de productos en el popup y lo convierte a entero
     */
    public int getQuantity() {
        String cantidad = wait.until(ExpectedConditions.visibilityOfElementLocated(PopupPage.lblQuantity)).getText();
        return Integer.parseInt(cantidad);
    }

    /**
     * Obtiene el subtotal en string y lo convierte a un valor decimal
     */
    public double getSubtotal() {
        // 1. Obtenemos el texto original
        String subtotalText = wait.until(ExpectedConditions.visibilityOfElementLocated(PopupPage.lblSubtotal)).getText();
        // 2. Limpiamos el texto:
        String subtotalLimpio = subtotalText.replaceAll("[^0-9,]", "").replace(",", ".");
        // 3. Lo convertimos a número decimal
        return Double.parseDouble(subtotalLimpio);
    }
    /**
     * Hace clic en el botón de finalizar compra
     */
    public void clickFinalizarCompra() {
        wait.until(ExpectedConditions.elementToBeClickable(PopupPage.btnFinalizarCompra)).click();
    }

}
