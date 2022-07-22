package com.mycompany.web3jbalance;

import static java.net.HttpURLConnection.HTTP_OK;
import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthGetBalance;

//Classe que gerencia caminho da balança
public class UnscaledBalanceHandler implements HttpHandler {

    public static final String PATH = "/UnscaledBalance";

    @Override
    public void handle(HttpExchange conn) throws IOException {
        try {
            String[] pathParts = conn.getRequestURI().getPath().split("/");
            // É aqui que entra a chave pública da conta 
            String publicKeyAdress = pathParts[2]; // partes[0] = "", partes[1] = "UnscaledBalance"
            byte[] result = sendResponse(publicKeyAdress);
            try {
                //Primeiro status e depois cabeçalhos
                conn.sendResponseHeaders(HTTP_OK, result.length);
                Headers headers = conn.getResponseHeaders();
                headers.add("Content-Type", "text/html; charset=UTF-8");
                
                // Depois envia o resultado para o cliente (navegador ou programa).
                try (OutputStream out = conn.getResponseBody()) {
                    out.write(result);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conn.close();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ScaledBalanceHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ScaledBalanceHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(ScaledBalanceHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    byte[] sendResponse(String publicKeyAdress) throws InterruptedException, ExecutionException, TimeoutException {
  
        final String ethAdress = publicKeyAdress;
        // Utiliza do client para receber essas informações.
        final EthGetBalance balanceResponse = Main.client.ethGetBalance(
                ethAdress, DefaultBlockParameter.valueOf("latest")).sendAsync().get(10, TimeUnit.SECONDS);
        final BigInteger unscaledBalance = balanceResponse.getBalance();
        return unscaledBalance.toString().getBytes();
    }
}