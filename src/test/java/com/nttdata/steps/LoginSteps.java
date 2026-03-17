package com.nttdata.steps;

import com.nttdata.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginSteps(WebDriver driver){
        this.driver = driver;
        // Inicializamos el wait con un máximo de 10 segundos (40 es demasiado)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void typeEmail(String email) {
        // Esperamos a que el campo sea visible antes de escribir
        WebElement txtEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.inputEmailUser));
        txtEmail.sendKeys(email);
    }

    public void typePassword(String password) {
        // Esperamos a que el campo sea visible antes de escribir
        WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.inputPasswordUser));
        txtPassword.sendKeys(password);
    }

    public void ingresoUsuarioYClave(String email, String password) {
        typeEmail(email);
        typePassword(password);

        // Esperamos a que el botón sea "clickeable" antes de hacer clic
        WebElement btnLogin = wait.until(ExpectedConditions.elementToBeClickable(LoginPage.btnLogin));
        btnLogin.click();
    }
}
