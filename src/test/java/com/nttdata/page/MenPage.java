package com.nttdata.page;
import org.openqa.selenium.By;

public class MenPage {

    // Localizadores

    // Localizador para el título del PRIMER producto de la lista
    public static By firstProductLink = By.xpath("//article[contains(@class, 'product-miniature')][1]//h2[contains(@class, 'product-title')]/a");
}
