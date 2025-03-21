import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class InterfaceScreenshotAutomation {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        WebDriverWait aguarde = new WebDriverWait(navegador, Duration.ofSeconds(10));

        try {
            navegador.get("https://demo.prestashop.com/#/en/front");
            navegador.manage().window().maximize();

            aguarde.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("framelive")));

            tirarScreenshotTela(navegador, "screenshots/full_view.png");

            WebElement botaoLogin = aguarde.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.user-info a")));
            tirarScreenshotDoElemento(botaoLogin, "screenshots/login_button.png");

            System.out.println("Capturas realizadas com sucesso!");

        } catch (Exception erro) {
            System.out.println("Falha na execução do script: " + erro.getMessage());
        } finally {
            navegador.quit();
        }
    }

    private static void tirarScreenshotTela(WebDriver navegador, String caminhoArquivo) {
        File imagem = ((TakesScreenshot) navegador).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(imagem, new File(caminhoArquivo));
            System.out.println("Tela salva em: " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar imagem da tela: " + e.getMessage());
        }
    }

    private static void tirarScreenshotDoElemento(WebElement elemento, String caminhoArquivo) {
        File imagem = elemento.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(imagem, new File(caminhoArquivo));
            System.out.println("Elemento salvo em: " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar imagem do elemento: " + e.getMessage());
        }
    }
}
