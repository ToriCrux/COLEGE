import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class ContactFormAutomation {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver browser = new ChromeDriver();

        try {
            browser.get("https://demo.prestashop.com/#/en/front");
            browser.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("framelive")));

            navigateToContactPage(wait);
            chooseSubjectOption(wait, "2");
            fillEmailField(wait, "contact@test.com");
            typeMessage(wait, "Isso é uma automação para testar a página de Contato.");
            submitForm(wait);

        } catch (Exception ex) {
            System.out.println("Erro durante automação de Formulário de Contato: " + ex.getMessage());
        } finally {
            browser.quit();
        }
    }

    private static void navigateToContactPage(WebDriverWait wait) {
        WebElement contactLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#contact-link a")));
        contactLink.click();
        System.out.println("Página de Contato acessado com sucesso.");
    }

    private static void chooseSubjectOption(WebDriverWait wait, String value) {
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_contact")));
        Select subjectSelect = new Select(dropdown);
        subjectSelect.selectByValue(value);
        System.out.println("Selecionado: Serviço do Consumidor.");
    }

    private static void fillEmailField(WebDriverWait wait, String email) {
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        try {
            emailField.sendKeys(email);
            System.out.println("Email válida: " + email);
        } catch (Exception e) {
            System.out.println("Tipo de entrada para email inválida: " + e.getMessage());
        }
    }

    private static void typeMessage(WebDriverWait wait, String content) {
        WebElement messageBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("contactform-message")));
        try {
            messageBox.sendKeys(content);
            System.out.println("Mensagem de entrada bem sucedida.");
        } catch (Exception e) {
            System.out.println("Mensagem de falha: " + e.getMessage());
        }
    }

    private static void submitForm(WebDriverWait wait) {
        WebElement sendBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='submitMessage']")));
        sendBtn.click();
        System.out.println("Formulário de Contato enviado com sucesso.");
    }
}
