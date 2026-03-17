package com.nttdata.page;

import org.openqa.selenium.By;

public class FirstProductPage {

    // Localizador para el input de la cantidad.
    public static By inputQuantity = By.id("quantity_wanted");

    // Localizador para el botón "Añadir al carrito".
    public static By btnAddToCart = By.cssSelector("button[data-button-action='add-to-cart']");

    // Localizador para el título del producto
    public static By lblProductTitle = By.xpath("//h1");

    // Localizador para el precio unitario del producto
    public static By lblProductPrice = By.cssSelector("span.current-price-value");
}
