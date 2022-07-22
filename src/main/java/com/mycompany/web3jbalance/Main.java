
package com.mycompany.web3jbalance;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import com.sun.net.httpserver.HttpServer;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;


/**
 * Servidor HTTP
 * 
 */
public class Main {
    // Número de conexões máximas.
    public static final int BACKLOG = 100;
    public static Web3j client = Web3j.build(
                new HttpService("insert here your infura endpoint")
        );
    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress(9999);
        HttpServer server = HttpServer.create(address, BACKLOG);
        server.setExecutor(Executors.newSingleThreadExecutor());
        // Caminhos.
        server.createContext(IndexHandler.PATH, new IndexHandler());
        server.createContext(ScaledBalanceHandler.PATH, new ScaledBalanceHandler());
        server.createContext(UnscaledBalanceHandler.PATH, new UnscaledBalanceHandler());
        // Iniciando servidor.
        server.start();
        System.out.printf("Server is open on %s\n\n", server.getAddress().getPort());
    }
}
