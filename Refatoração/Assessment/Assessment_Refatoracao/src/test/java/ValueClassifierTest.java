import org.junit.jupiter.api.Test;
import org.example.ValueClassifier;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValueClassifierTest {

    @Test
     void testClassification() {
        ValueClassifier classifier = new ValueClassifier();

        assertEquals("ALTO", classifierTestHelper(classifier, 29));

        assertEquals("MÉDIO", classifierTestHelper(classifier, 10));

        assertEquals("BAIXO", classifierTestHelper(classifier, 3));

        assertEquals("CASO RARO", classifierTestHelper(classifier, -9999));
    }

    private String classifierTestHelper(ValueClassifier classifier, int value) {
        try {
            var method = ValueClassifier.class.getDeclaredMethod("classify", int.class);
            method.setAccessible(true);
            return (String) method.invoke(classifier, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
