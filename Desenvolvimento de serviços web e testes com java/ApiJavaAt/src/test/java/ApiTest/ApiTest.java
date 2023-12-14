package ApiTest;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiTest {

    @Test
    void testListagemUsuarios() {
        try {
            URL url = new URL("https://randomuser.me/api/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int codigoRetorno = connection.getResponseCode();
            assertEquals(200, codigoRetorno);

            connection.disconnect();

            System.out.println("Parabéns! Teste de listagem de usuários bem-sucedido!");
        } catch (Exception e) {
            System.err.println("Vish... Erro ao tentar fazer a requisição à API: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
