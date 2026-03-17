package com.nttdata.steps;

import com.nttdata.page.FirstProductPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FirstProductSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    public FirstProductSteps(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Borra el contenido actual del input y escribe la nueva cantidad
     * @param cantidad el número de unidades a agregar
     */
    public void setCantidad(int cantidad) {
        WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(FirstProductPage.inputQuantity));

        //Seleccionamos tdo el texto y lo reemplazamos por el nuevo número
        inputElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        inputElement.sendKeys(String.valueOf(cantidad));
    }

    /**
     * Hace clic en el botón de añadir al carrito
     */
    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(FirstProductPage.btnAddToCart)).click();
    }

    public double getProductPrice() {
        // 1. Obtenemos el texto del precio
        String priceText = wait.until(ExpectedConditions.visibilityOfElementLocated(FirstProductPage.lblProductPrice)).getText();
        // 2. Limpiamos el texto dejando solo los números y la coma, y luego cambiamos la coma por punto
        String priceClean = priceText.replaceAll("[^0-9,]", "").replace(",", ".");
        // 3. Convertimos a double y lo retornamos
        return Double.parseDouble(priceClean);
    }

    /**
     * Obtiene el título del producto desde la página de detalle
     * @return el nombre del producto como String
     */
    public String getProductTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(FirstProductPage.lblProductTitle)).getText();
    }


}
