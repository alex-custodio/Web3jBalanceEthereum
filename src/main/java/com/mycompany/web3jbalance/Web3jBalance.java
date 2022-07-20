package com.mycompany.web3jbalance;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import java.util.Scanner;

public class Web3jBalance {

    public static void main(String[] args) throws TimeoutException, InterruptedException, ExecutionException {
        final Web3j client = Web3j.build(
                new HttpService("insert here your infura.io mainnet endpoint")
        );
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the public key:");
        final String ethAdress = input.nextLine(); //insert here your ethereum adress
        
        final EthGetBalance balanceResponse = client.ethGetBalance(
                ethAdress, DefaultBlockParameter.valueOf("latest")).sendAsync().get(10, TimeUnit.SECONDS);
        final BigInteger unscaledBalance = balanceResponse.getBalance();
        final BigDecimal scaledBalance = new BigDecimal(unscaledBalance).divide(
                new BigDecimal(1000000000000000000L),18,RoundingMode.HALF_UP
        );
        System.out.println("Unscaled Balance: " + unscaledBalance);
        System.out.println("Scaled Balance: " + scaledBalance + " Ether");
        
    }
}
