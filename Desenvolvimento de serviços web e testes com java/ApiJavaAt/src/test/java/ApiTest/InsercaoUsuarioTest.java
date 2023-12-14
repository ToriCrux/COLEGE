package ApiTest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Usuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InsercaoUsuarioTest {

    private static final String API_URL = "http://localhost:4567/usuarios";
    private static final int EXPECTED_RESPONSE_CODE = 201;

    @Test
    void testInsercaoUsuario() {
        try {
            HttpURLConnection conexao = abrirConexao();

            Usuario usuario = criarUsuario();
            enviarRequisicao(conexao, usuario);

            verificarResposta(conexao);

        } catch (Exception e) {
            handleError(e);
        }
    }

    private Usuario criarUsuario() {
        return Usuario.builder()
                .id(3)
                .nome("nome3")
                .senha("12152")
                .build();
    }

    private HttpURLConnection abrirConexao() throws Exception {
        URL urlObj = new URL(API_URL);
        HttpURLConnection conexao = (HttpURLConnection) urlObj.openConnection();
        conexao.setRequestProperty("Accept", "application/json");
        conexao.setDoOutput(true);
        conexao.setRequestMethod("POST");
        return conexao;
    }

    private void enviarRequisicao(HttpURLConnection conexao, Usuario usuario) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(usuario);
        conexao.getOutputStream().write(json.getBytes());
    }

    private void verificarResposta(HttpURLConnection conexao) throws Exception {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            assertEquals(EXPECTED_RESPONSE_CODE, conexao.getResponseCode());
        }
    }

    private void handleError(Exception e) {
        System.err.println("Vish... Erro durante o teste: " + e.getMessage());
        e.printStackTrace();
    }
}
