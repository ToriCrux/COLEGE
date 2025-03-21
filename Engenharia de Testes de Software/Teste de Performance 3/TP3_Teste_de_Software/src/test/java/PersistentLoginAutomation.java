import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.io.*;
import java.time.Duration;
import java.util.Set;

public class PersistentLoginAutomation {

    private static final String COOKIE_FILE = "cookies.data";

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();
        WebDriverWait espera = new WebDriverWait(navegador, Duration.ofSeconds(20));

        try {
            navegador.get("https://demo.prestashop.com/#/en/front");
            navegador.manage().window().maximize();

            espera.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("framelive")));

            File arquivoCookies = new File(COOKIE_FILE);
            if (arquivoCookies.exists()) {
                carregarCookies(navegador, arquivoCookies);
                navegador.navigate().refresh();
                System.out.println("Cookies carregados. Sessão restaurada.");
            } else {
                realizarLogin(navegador, espera);
                salvarCookies(navegador, arquivoCookies);
            }

            WebElement primeiroProduto = espera.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product-miniature .thumbnail-container")));
            primeiroProduto.click();

            WebElement adicionarAoCarrinho = espera.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.add-to-cart")));
            adicionarAoCarrinho.click();

            System.out.println("Produto adicionado ao carrinho com login persistente.");

        } catch (Exception e) {
            System.out.println("Erro durante a automação: " + e.getMessage());
        } finally {
            navegador.quit();
        }
    }

    private static void realizarLogin(WebDriver navegador, WebDriverWait espera) {
        WebElement signIn = espera.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.user-info a")));
        signIn.click();
        espera.until(ExpectedConditions.visibilityOfElementLocated(By.id("field-email")));

        navegador.findElement(By.id("field-email")).sendKeys("vic_cruz@example.com");
        navegador.findElement(By.id("field-password")).sendKeys("StrongPassw0rd123!");
        navegador.findElement(By.id("submit-login")).click();

        System.out.println("Login realizado e sessão iniciada.");
    }

    private static void salvarCookies(WebDriver navegador, File arquivo) throws IOException {
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Cookie cookie : navegador.manage().getCookies()) {
            bw.write(
                    cookie.getName() + ";" +
                            cookie.getValue() + ";" +
                            cookie.getDomain() + ";" +
                            cookie.getPath() + ";" +
                            cookie.getExpiry() + ";" +
                            cookie.isSecure()
            );
            bw.newLine();
        }
        bw.close();
        System.out.println("Cookies salvos com sucesso.");
    }

    private static void carregarCookies(WebDriver navegador, File arquivo) throws IOException {
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);
        String linha;

        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split(";");
            String nome = partes[0];
            String valor = partes[1];
            String dominio = partes[2];
            String caminho = partes[3];
            String dataExp = partes[4];
            boolean seguro = Boolean.parseBoolean(partes[5]);

            Cookie cookie = new Cookie.Builder(nome, valor)
                    .domain(dominio)
                    .path(caminho)
                    .isSecure(seguro)
                    .build();

            navegador.manage().addCookie(cookie);
        }
        br.close();
    }
}
