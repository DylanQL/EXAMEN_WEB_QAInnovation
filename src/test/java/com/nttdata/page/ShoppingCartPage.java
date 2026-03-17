package com.nttdata.page;

import org.openqa.selenium.By;

public class ShoppingCartPage {

    // Localizador para el título principal ("Carrito")
    public static By lblCartTitle = By.cssSelector("h1.h1");

    // Localizador para el subtotal
    public static By lblSubtotal = By.cssSelector("div.cart-summary-line#cart-subtotal-products span.value");


}