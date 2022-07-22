package com.mycompany.web3jbalance;

import static java.net.HttpURLConnection.HTTP_OK;
import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

// Classe para o caminho principal do servidor
public class IndexHandler implements HttpHandler {

    public static final String PATH = "/";

    @Override
    public void handle(HttpExchange conn) throws IOException {
        byte[] hello = "This is the ÇuÇ project back-end side".getBytes();

        try {
            // Protocolo HTTP: primeiro os status, depois os cabeçalhos e dados.
            conn.sendResponseHeaders(HTTP_OK, hello.length);

            Headers headers = conn.getResponseHeaders();
            headers.add("Content-Type", "text/html; charset=UTF-8");
            
            
            try (OutputStream out = conn.getResponseBody()) {
                out.write(hello);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}