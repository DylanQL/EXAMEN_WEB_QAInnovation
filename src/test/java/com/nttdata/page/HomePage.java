package com.nttdata.page;
import org.openqa.selenium.By;

public class HomePage {
    // Localizadores

    public static By lblProductosDestacados = By.xpath("//h2[contains(., 'Productos Destacados')]");

    public static By navbarMenuClothes = By.xpath("//*[@id='category-3']/a");
}
