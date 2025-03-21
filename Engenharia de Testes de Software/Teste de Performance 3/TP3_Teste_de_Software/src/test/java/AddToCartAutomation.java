import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class AddToCartAutomation {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        WebDriverWait aguarde = new WebDriverWait(navegador, Duration.ofSeconds(20));

        try {
            navegador.get("https://demo.prestashop.com/#/en/front");
            navegador.manage().window().maximize();
            Thread.sleep(2000);

            aguarde.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("framelive")));

            WebElement produto = aguarde.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".thumbnail.product-thumbnail")));
            rolarAteElemento(navegador, produto);
            Thread.sleep(1500);
            produto.click();
            System.out.println("Produto selecionado com sucesso!");

            aguarde.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-container")));
            System.out.println("Detalhes do produto.");
            Thread.sleep(1500);

            WebElement campoQuantidade = aguarde.until(ExpectedConditions.visibilityOfElementLocated(By.id("quantity_wanted")));
            campoQuantidade.clear();
            campoQuantidade.sendKeys("1");
            System.out.println("Quantidade definida para 11.");
            Thread.sleep(1500);

            WebElement botaoAdicionar = aguarde.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-primary.add-to-cart")));
            botaoAdicionar.click();
            System.out.println("Produto no carrinho!");
            Thread.sleep(4000);

            WebElement botaoCheckout = aguarde.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.btn.btn-primary[href*='cart?action=show']")));
            botaoCheckout.click();
            System.out.println("Navegando para o carrinho de compras...");
            Thread.sleep(4000);

        } catch (Exception erro) {
            System.out.println("Erro durante o processo de compra: " + erro.getMessage());
        } finally {
            navegador.quit();
        }
    }

    private static void rolarAteElemento(WebDriver navegador, WebElement alvo) {
        ((JavascriptExecutor) navegador).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", alvo);
        System.out.println("Elemento centralizado na tela com sucesso.");
    }
}
