package com.example.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class DemoApplication {

	public static void main(String[] args) {
        // Valor en base64 (asegúrate de proporcionar el valor completo aquí)
        String base64PDF = "JVBERi0xLjQKJeLjz9MKNCAwIG9iago8PC9GaWx0ZXIvRmxhdGVEZWNvZGUvTGVuZ3RoIDQ3Mz4+c3RyZWFtCniclZRNb9swDIbv/BU8bhdNki3J6rHFhnXo0BZz0cOwSxGnaxE7qLGhf3/6tsSmhx2CkLHeh3ypmC9wPgJHYyWOO/g8wi28AGe8MwpfQeI39/AZBMfv8PMXxx10Go3SOIMyMTqEqNOM9y7umjA+/w33sDio4sJq5Jij9RF66c5wR/OBFMx9eR4niTviMXsHkZg/Xi6Y7tBwxYzwEGGZyenBpQOzyuU9s76PXrJu2FLPaxQZoI1hVm4AbQamzQngRkiSOXW0EUJJCih5BFSKubX0pmfCi/rUVPLgBhYliaC0jcSMSJYrD62kIIqLhChlKbJiVD6S1TLLOKcWccoJRVT32XROiPUsw08zPfGmJdm4rG+zBSjOBlsBlCvTnwBWhCgp/4dCiCUpIOeVBwIoHtqeCa+ZYvJQLoZOnlxM5aGVbIjsIiNyWYqsGZuPZLXMMs6JIE45oYjtPsnb2hA9we22T18Eyh7HPQi/elBgWDWaWRxn+HC57I/rPOFuOuDF4Wla/kwfx2e/CHk47pZMggjZQIbgbjCsFwF0sz7N04rLcX5Yp7PECFJJpVL5Pp1UB+XX66vL+vw7pXSYpxf8mB7/Lrvjf9RyWhWL3dyN10ngN/0t/APGoDo9CmVuZHN0cmVhbQplbmRvYmoKMSAwIG9iago8PC9Db250ZW50cyA0IDAgUi9UeXBlL1BhZ2UvUmVzb3VyY2VzPDwvUHJvY1NldCBbL1BERiAvVGV4dCAvSW1hZ2VCIC9JbWFnZUMgL0ltYWdlSV0vRm9udDw8L0YxIDIgMCBSL0YyIDMgMCBSPj4+Pi9QYXJlbnQgNSAwIFIvTWVkaWFCb3hbMCAwIDYxMiA3OTJdPj4KZW5kb2JqCjcgMCBvYmoKPDwvRGVzdFsxIDAgUi9YWVogMCA3NTAgMF0vVGl0bGUoSW5mb3JtZSBkZWwgQ2xpZW50ZSkvUGFyZW50IDYgMCBSPj4KZW5kb2JqCjYgMCBvYmoKPDwvVHlwZS9PdXRsaW5lcy9Db3VudCAxL0ZpcnN0IDcgMCBSL0xhc3QgNyAwIFI+PgplbmRvYmoKMiAwIG9iago8PC9TdWJ0eXBlL1R5cGUxL1R5cGUvRm9udC9CYXNlRm9udC9IZWx2ZXRpY2EtQm9sZC9FbmNvZGluZy9XaW5BbnNpRW5jb2Rpbmc+PgplbmRvYmoKMyAwIG9iago8PC9TdWJ0eXBlL1R5cGUxL1R5cGUvRm9udC9CYXNlRm9udC9IZWx2ZXRpY2EvRW5jb2RpbmcvV2luQW5zaUVuY29kaW5nPj4KZW5kb2JqCjUgMCBvYmoKPDwvS2lkc1sxIDAgUl0vVHlwZS9QYWdlcy9Db3VudCAxL0lUWFQoMi4xLjcpPj4KZW5kb2JqCjggMCBvYmoKPDwvUGFnZU1vZGUvVXNlT3V0bGluZXMvVHlwZS9DYXRhbG9nL091dGxpbmVzIDYgMCBSL1BhZ2VzIDUgMCBSPj4KZW5kb2JqCjkgMCBvYmoKPDwvTW9kRGF0ZShEOjIwMjMwOTA5MTI0NTQ0LTA1JzAwJykvQ3JlYXRpb25EYXRlKEQ6MjAyMzA5MDkxMjQ1NDQtMDUnMDAnKS9Qcm9kdWNlcihpVGV4dCAyLjEuNyBieSAxVDNYVCkvVGl0bGUoSW5mb3JtZSBkZWwgQ2xpZW50ZSk+PgplbmRvYmoKeHJlZgowIDEwCjAwMDAwMDAwMDAgNjU1MzUgZiAKMDAwMDAwMDU1NSAwMDAwMCBuIAowMDAwMDAwODcwIDAwMDAwIG4gCjAwMDAwMDA5NjMgMDAwMDAgbiAKMDAwMDAwMDAxNSAwMDAwMCBuIAowMDAwMDAxMDUxIDAwMDAwIG4gCjAwMDAwMDA4MDUgMDAwMDAgbiAKMDAwMDAwMDcyMSAwMDAwMCBuIAowMDAwMDAxMTE0IDAwMDAwIG4gCjAwMDAwMDExOTUgMDAwMDAgbiAKdHJhaWxlcgo8PC9JbmZvIDkgMCBSL0lEIFs8ODE3MWIwZjVhZGRiYmQ3NTYzMzgyZmM2Yzc1NDEwZWU+PDE1YWMzZjA4N2U1MDAzMWZmZTE3N2Q5MjljOTExYmNlPl0vUm9vdCA4IDAgUi9TaXplIDEwPj4Kc3RhcnR4cmVmCjEzNDQKJSVFT0YK";

        try {
            // Decodificar la cadena base64 a bytes
            byte[] pdfBytes = Base64.getDecoder().decode(base64PDF);

            // Especifica la ruta donde deseas guardar el PDF
            String outputPath = "D:/JAVA/ClaroProject/pdfs/informess.pdf";

            // Escribe los bytes en un archivo PDF
            Files.write(Path.of(outputPath), pdfBytes);

            System.out.println("PDF descargado y guardado en: " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
