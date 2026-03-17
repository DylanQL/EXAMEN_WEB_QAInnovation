package com.nttdata.stepsdefinitions;

import com.nttdata.steps.*;
import io.cucumber.java.PendingException;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.nttdata.core.DriverManager.*;

public class ProductStoreStepsDefinition {

    private WebDriver driver;
    private LoginSteps loginSteps;
    private HomeSteps homeSteps;
    private ClothesSteps clothesSteps;
    private FirstProductSteps firstProductSteps;
    private MenSteps menSteps;
    private PopupSteps popupSteps;
    private ShoppingCartSteps shoppingCartSteps;


    private double precioUnitarioProducto;
    private String nombreProductoEsperado;

    @Dado("estoy en la página de la tienda")
    public void estoyEnLaPáginaDeLaTienda() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store/es/iniciar-sesion");
        screenShot();
    }

    @Y("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String email, String password) {
        // Write code here that turns the phrase above into concrete actions
        loginSteps = new LoginSteps(driver);
        loginSteps.ingresoUsuarioYClave(email,password);

        // --- VALIDACIÓN DE AUTENTIFICACIÓN ---
        homeSteps = new HomeSteps(driver);
        String tituloHome = homeSteps.getProductosDestacadosText();

        // Validamos que el texto coincida (ignora mayúsculas por seguridad)
        Assertions.assertTrue(tituloHome.toLowerCase().contains("productos destacados"),
                "La autentificación falló: No se encontró el título de la página principal.");

        screenShot();
    }

    @Cuando("navego a la categoria {string} y subcategoria {string}")
    public void navegoALaCategoriaYSubcategoria(String categoria, String subcategoria) {

        // 1. Validamos que los parámetros del Gherkin sean exactamente los esperados
        Assertions.assertEquals("Clothes", categoria, "La categoría no es la esperada: " + categoria);
        Assertions.assertEquals("Men", subcategoria, "La subcategoría no es la esperada: " + subcategoria);

        // 2. Si las aserciones pasan, inicializamos las clases y hacemos las acciones
        homeSteps = new HomeSteps(driver);
        clothesSteps = new ClothesSteps(driver);

        homeSteps.clickMenuClothes();
        clothesSteps.clickSubcategoryMen();
        screenShot();

    }

    @Y("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(int cantidad) {
        // 1. Primero hacemos clic en el primer producto (en la página de Men)
        menSteps = new MenSteps(driver);
        menSteps.clickFirstProduct();
        // 2. Luego interactuamos con la página de detalle del producto
        firstProductSteps = new FirstProductSteps(driver);
        // Guardamos el precio unitario y nombre de del product en laS variable de clase para usarla luego en las validaciones
        this.precioUnitarioProducto = firstProductSteps.getProductPrice();
        this.nombreProductoEsperado = firstProductSteps.getProductTitle();

        firstProductSteps.setCantidad(cantidad);
        screenShot();
        firstProductSteps.clickAddToCart();
    }

    @Entonces("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmaciónDelProductoAgregado() {
        popupSteps = new PopupSteps(driver);

        // 1. Validar el Nombre del Producto (usando lo que capturamos en la página anterior)
        String nombreReal = popupSteps.getProductName();
        Assertions.assertEquals(this.nombreProductoEsperado.toLowerCase(), nombreReal.toLowerCase(), "El nombre del producto no coincide en el popup");

        // 2. Validar la Cantidad (debe ser 2 según tu escenario)
        int cantidadReal = popupSteps.getQuantity();
        Assertions.assertEquals(2, cantidadReal, "La cantidad mostrada en el popup no es correcta");
        screenShot();
    }

    @Y("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        // Write code here that turns the phrase above into concrete actions
        // 3. Validar el Subtotal (Cálculo matemático: Precio Unitario * Cantidad)
        double subtotalReal = popupSteps.getSubtotal();
        int cantidadReal = popupSteps.getQuantity();
        double subtotalEsperado = this.precioUnitarioProducto * cantidadReal;

        // Usamos un delta para el margen de error posible
        Assertions.assertEquals(subtotalEsperado, subtotalReal, 0.001,  "El subtotal del popup (" + subtotalReal + ") no coincide con el cálculo esperado (" + subtotalEsperado + ")");

    }

    @Cuando("finalizo la compra")
    public void finalizoLaCompra() {
        popupSteps.clickFinalizarCompra();
    }

    @Entonces("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        shoppingCartSteps = new ShoppingCartSteps(driver);

        // Obtenemos el título real de la página
        String tituloActual = shoppingCartSteps.getTitle();

        // Validamos que el título contenga la palabra "Carrito"
        // Usamos toLowerCase() para que no falle si la página lo muestra en mayúsculas
        Assertions.assertTrue(tituloActual.toLowerCase().contains("carrito"),
                "El título de la página no es el esperado. Se encontró: " + tituloActual);
        screenShot();
    }

    @Y("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {

        // Obtenemos el subtotal que muestra la página final (ya convertido a double)
        double subtotalFinal = shoppingCartSteps.getSubtotal();

        // Calculamos lo que esperamos (Precio que capturamos al inicio * 2 unidades)
        // Usamos la variable de clase 'precioUnitarioProducto' que guardamos en el step de agregar al carrito
        double subtotalEsperado = this.precioUnitarioProducto * 2;

        // Validación matemática final con un delta de 0.001 para decimales
        Assertions.assertEquals(subtotalEsperado, subtotalFinal, 0.001,
                "El cálculo de precios en el carrito final es incorrecto. Esperado: " + subtotalEsperado + " - Actual: " + subtotalFinal);
        screenShot();
    }
}
