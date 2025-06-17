package org.example;

public class DocumentMain {
    public static void main(String[] args) {
        Document pdf = new DocumentPDF();
        Document html = new DocumentHtml();
        Document txt = new DocumentTxt();

        pdf.print();
        html.print();
        txt.print();
    }
}
