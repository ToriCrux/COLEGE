import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginValidCredentialsAutomation {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        WebDriverWait espera = new WebDriverWait(navegador, Duration.ofSeconds(20));

        try {
            navegador.get("https://demo.prestashop.com/#/en/front");
            navegador.manage().window().maximize();

            // Entrando no site
            espera.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("framelive")));

            // Acessando a tela de login
            WebElement entrarLink = espera.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.user-info a")));
            entrarLink.click();
            System.out.println("Link de login acessado com sucesso.");

            // Esperando formulário de login
            espera.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form#login-form")));

            // Preenchendo os campos com email e senha válidos
            WebElement campoEmail = espera.until(ExpectedConditions.presenceOfElementLocated(By.id("field-email")));
            WebElement campoSenha = espera.until(ExpectedConditions.presenceOfElementLocated(By.id("field-password")));
            WebElement botaoEntrar = espera.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#submit-login")));

            campoEmail.sendKeys("vic_cruz@example.com");
            campoSenha.sendKeys("StrongPassw0rd123!");
            botaoEntrar.click();
            System.out.println("Login submetido com credenciais válidas.");

            // Esperando redirecionar e verificar a URL
            Thread.sleep(2000);
            String urlAtual = navegador.getCurrentUrl();
            if (!urlAtual.contains("login")) {
                System.out.println("Login realizado com sucesso! Redirecionado para: " + urlAtual);
            } else {
                System.out.println("Login não foi bem-sucedido. Ainda na página de login.");
            }

        } catch (Exception erro) {
            System.out.println("Erro durante o processo de login: " + erro.getMessage());
        } finally {
            navegador.quit();
        }
    }
}
