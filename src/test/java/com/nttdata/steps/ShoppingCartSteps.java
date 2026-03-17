package com.nttdata.steps;

import com.nttdata.page.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    public ShoppingCartSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    /**
     * Obtiene el título de la página del carrito
     * @return texto del encabezado (ej. "CARRITO")
     */
    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(ShoppingCartPage.lblCartTitle)).getText();
    }

    /**
     * Obtiene el subtotal de los productos en el carrito y lo convierte a decimal
     * @return valor numérico del subtotal
     */
    public double getSubtotal() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(ShoppingCartPage.lblSubtotal)).getText();

        // Limpieza de la cadena: eliminamos tdo lo que no sea número o coma, y cambiamos la coma por punto
        String cleanText = text.replaceAll("[^0-9,]", "").replace(",", ".");

        return Double.parseDouble(cleanText);
    }

}
