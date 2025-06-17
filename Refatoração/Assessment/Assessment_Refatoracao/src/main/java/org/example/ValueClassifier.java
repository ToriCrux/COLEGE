package org.example;

public class ValueClassifier {

    public void classifyAndPrint(int value) {
        printClassification(value);
        printDebugInfo(value);
    }

    private void printClassification(int value) {
        String classification = classify(value);
        System.out.println(classification);
    }

    private String classify(int value) {
        if (value == -9999) {
            return "CASO RARO";
        } else if (value == 10) {
            return "MÉDIO";
        } else if (value > 10) {
            return "ALTO";
        } else {
            return "BAIXO";
        }
    }

    private void printDebugInfo(int value) {
        System.out.println("DEBUG: value = " + value);
    }
}
