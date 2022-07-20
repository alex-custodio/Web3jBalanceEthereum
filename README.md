# Back-end do Projeto pro Desafio ÇuÇ

## Software Alvo (Desafio)
### Participantes:
+ [Alex Custódio](https://github.com/alex-custodio)
+ [Thiago Almeida](https://github.com/ThiagoAlmeida0)

Criar uma aplicação Flutter ou React Native que se conecte com o back-end em java puro; acessando a Blockchain do Ethereum através de Smart Contracts.

## Nossa implementação:
Criar uma aplicação Flutter que se conecte com o back-end em java puro; acessando a Blockchain do Ethereum através de Smart Contracts para exibir os dados de uma determinada carteira, exibindo seus dados baseado em sua chave pública.

Este repositório guarda o código do back-end em java da aplicação almejada no software alvo. Os Smart Contracts estão incluídos internamente dentro da biblioteca Web3j além da possibilidade de fazer utilizando Solidity (a escolha foi para que fosse considerado os smart contracts internos da biblioteca), como é possível ver em sua documentação: [Documentação Para Smart Contracts](https://https://docs.web3j.io/4.8.7/smart_contracts/smart_contracts_overview/) _(Web3j can do all of this for you with its Solidity smart contract wrappers. To understand what is happening behind the scenes, you can refer to the details in...)_

## Tecnologias e Ferramentas Utilizadas (Fiz esse projeto como projeto de Netbeans, podendo ser aberto através desse programa)
+ Java
+ Biblioteca Web3j
+ Maven para integrar biblioteca
+ Netbeans (VS Code não tankou)

## Como funciona
Após as importações e estruturas básicas, a linha 18 inicializa a conexão cliente necessária para se comunicar com a Blockchain do Ethereum. Para o código funcionar é necessário inserir um endpoint seu que pode ser gerado no site [infura.io](https://infura.io) após criar uma conta, um projeto com qualquer nome e ser resgatado nessa área marcada de preto com um endereço.

![screenshot-readme](https://user-images.githubusercontent.com/89322317/180072980-bbd14323-8152-45ce-8e3c-01f34632ee5a.png)
```
18 -    final Web3j client = Web3j.build(new HttpService("insert here your infura.io mainnet endpoint"));
```
Após preencher este campo com o o endpoint mainnet, o programa deverá executar normalmente lendo através de um scanner alguma chave pública, sendo necessário digitar essa chave.

## Entrada do Programa (endereços podem ser pegados para teste no site etherscan, que lista os mesmos dados que são listados através do programa, inclusive para verificar a veracidade da execução do programa)
```
Enter the public key:
0xExemplo
```
## Saída do Programa (exemplo)
```
Unscaled Balance: 10
Scaled Balance: 0.0010 Ether
```

