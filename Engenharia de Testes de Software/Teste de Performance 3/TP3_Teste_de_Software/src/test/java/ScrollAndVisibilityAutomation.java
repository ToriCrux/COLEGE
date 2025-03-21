import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class ScrollAndVisibilityAutomation {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        WebDriverWait aguarde = new WebDriverWait(navegador, Duration.ofSeconds(30));

        try {
            long inicioCarregamento = System.currentTimeMillis();

            navegador.get("https://demo.prestashop.com/#/en/front");

            aguarde.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("framelive")));
            aguarde.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));

            long fimCarregamento = System.currentTimeMillis();
            long tempoTotal = fimCarregamento - inicioCarregamento;
            System.out.println("Tempo para carregar a página inicial: " + tempoTotal + " ms");

            WebElement itemProduto = aguarde.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".thumbnail.product-thumbnail")));
            rolarAte(itemProduto, navegador);
            itemProduto.click();
            System.out.println("Produto acessado com sucesso!");

            inicioCarregamento = System.currentTimeMillis();
            aguarde.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-container")));
            fimCarregamento = System.currentTimeMillis();
            tempoTotal = fimCarregamento - inicioCarregamento;
            System.out.println("Tempo para carregar a página: " + tempoTotal + " ms");

            Thread.sleep(4000);

        } catch (Exception erro) {
            System.out.println("Erro durante o teste de rolagem e visibilidade: " + erro.getMessage());
        } finally {
            navegador.quit();
        }
    }

    private static void rolarAte(WebElement elemento, WebDriver navegador) {
        ((JavascriptExecutor) navegador).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", elemento);
        System.out.println("Rolar automático realizado com sucesso.");
    }
}
