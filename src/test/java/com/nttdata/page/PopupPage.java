package com.nttdata.page;

import org.openqa.selenium.By;

public class PopupPage {

    // Localizador para el título del producto del popup
    public static By lblProductName = By.cssSelector("h6.product-name");

    // Localizador para el valor de la cantidad (apuntamos al <strong> para obtener solo el número)
    public static By lblQuantity = By.cssSelector("span.product-quantity strong");

    // Localizador para el subtotal
    public static By lblSubtotal = By.cssSelector("span.subtotal.value");

    // Localizador para el botón "Finalizar compra"
    public static By btnFinalizarCompra = By.cssSelector(".cart-content-btn a.btn-primary");
}
