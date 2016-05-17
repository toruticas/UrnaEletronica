# Tema e Título

## Membros

Rafael Mariano de Castro Silva - 7564023

Sarsha

Broder do Sarsha

## Introdução

O tema do projeto se resume no desenvolvimento de uma aplicação cliente-servidor de urna eletrônica com Socket (TCP) desenvolvido em java, com utilização de código e com um objeto que corresponda ao candidato. O candidato possui algumas informações básica fudamentais bem como o código do candidato, o nome do candidato, o partido do candidato e a quantidade de votos que este possui.

Esta aplicação possui um Makefile que auxilia na compilação do programa. Basta executá-lo que um arquivo jar será gerado para usod direto.

```bash
$ make
```

Dai é só usar o arquivo jar.


```bash
$ java -jar UrnaEletronica.jar
```

Caso necessite testar a solução de forma direta, sem usar a aplicação do cliente, basta usar o aplicativo netcat que envia dados para um host e aplicação específica.

```bash
$ java -jar UrnaEletronica.jar
$ cat <arquivo-com-o-dados> | netcat <ip-do-host> <porta-da-aplicação>.
```

## Fundamentação Teórica

O serviço responde pela porta padrão 2202 e imprime um temporizador a cada 250ms.
Para garantir a segurança do servidor um arquivo de log é escrito a cada requisição enviada para o
servidor, afim de manter consistência nas informações.

## Detalhes da Solução

## Metodologia, ambientes e ferramentas Utilizadas

Os ambientes e ferramentas usados nesta aplicação foram:
- Editor Atom;
- Oracle8Sdk;
- Makefile;
- Interpretador bash;
- cat para testar o socket.

## Modelagem do sistema a ser desenvolvido/estudado

## Conclusões

## Referências Bibliográficas

[1] http://www.devmedia.com.br/java-sockets-criando-comunicacoes-em-java/9465

[2] https://www.caelum.com.br/apostila-java-orientacao-objetos/apendice-sockets/#19-5-servidor

[3] http://stackoverflow.com/questions/10131377/socket-programming-multiple-client-to-one-server

[4] https://www.caelum.com.br/apostila-java-orientacao-objetos/programacao-concorrente-e-threads/#17-1-threads

[5] http://www.devmedia.com.br/trabalhando-com-threads-em-java/28780
