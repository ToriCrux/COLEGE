import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class UserRegistrationAutomation {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();

        try {
            navegador.get("https://demo.prestashop.com/#/en/front");
            navegador.manage().window().maximize();

            WebDriverWait espera = new WebDriverWait(navegador, Duration.ofSeconds(20));
            espera.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("framelive")));

            // Acessando a tela de login
            WebElement botaoLogin = espera.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.user-info a")));
            botaoLogin.click();
            System.out.println("Acesso à tela de login realizado.");

            // Criando a conta
            espera.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form#login-form")));
            WebElement linkCriarConta = espera.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.no-account a")));
            ((JavascriptExecutor) navegador).executeScript("arguments[0].scrollIntoView(true);", linkCriarConta);
            espera.until(ExpectedConditions.elementToBeClickable(linkCriarConta)).click();
            System.out.println("Redirecionado para o formulário de criação de conta.");

            Thread.sleep(1500); // Aguarda carregamento visual

            // Preenchendo os campos
            selecionarGenero(navegador, espera);
            preencherCampo(espera, "field-firstname", "Victoria", "Nome");
            preencherCampo(espera, "field-lastname", "Cruz", "Sobrenome");
            preencherCampo(espera, "field-email", "vic_cruz@example.com", "Email");
            preencherCampo(espera, "field-password", "StrongPassw0rd123!", "Senha");

            marcarCheckbox(espera, "psgdpr", "Termos e Condições");
            marcarCheckbox(espera, "customer_privacy", "Privacidade do Cliente");

            // Subindo o formulário
            WebElement botaoSalvar = espera.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-link-action='save-customer']")));
            botaoSalvar.click();
            System.out.println("Cadastro enviado com sucesso!");

        } catch (Exception erro) {
            System.out.println("Ocorreu um erro durante o processo de registro: " + erro.getMessage());
        } finally {
            navegador.quit();
        }
    }

    private static void selecionarGenero(WebDriver navegador, WebDriverWait espera) {
        WebElement opcaoMasculino = espera.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("label[for='field-id_gender-1']")));
        try {
            opcaoMasculino.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) navegador).executeScript("arguments[0].click();", opcaoMasculino);
        }
        System.out.println("Gênero selecionado com sucesso.");
    }

    private static void preencherCampo(WebDriverWait espera, String campoId, String valor, String campoDescricao) {
        try {
            WebElement campo = espera.until(ExpectedConditions.presenceOfElementLocated(By.id(campoId)));
            campo.sendKeys(valor);
            System.out.println("🟢 " + campoDescricao + " preenchido.");
        } catch (Exception e) {
            System.out.println("Falha ao preencher o campo " + campoDescricao + ": " + e.getMessage());
        }
    }

    private static void marcarCheckbox(WebDriverWait espera, String nomeCheckbox, String descricao) {
        try {
            WebElement checkbox = espera.until(ExpectedConditions.presenceOfElementLocated(By.name(nomeCheckbox)));
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
            System.out.println("Checkbox '" + descricao + "' marcado.");
        } catch (Exception e) {
            System.out.println("Falha ao marcar checkbox " + descricao + ": " + e.getMessage());
        }
    }
}
