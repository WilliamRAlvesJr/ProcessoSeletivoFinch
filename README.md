


# ProcessoSeletivoFinch

## Requisitos

### Banco de dados
É preciso um banco de dados MySQL com uma base chamada **"lanchonete"**, os dados serão inseridos automaticamente. 

Ou executar o sql no arquivo **"Instalação/script_sql.txt"**. (necessário executar sempre que executar o programa) .

### Detalhes do banco
O banco deve estar configurado com **"Timezone=America/Sao_Paulo"**, as credenciais do banco devem ser usuário **"root"** e senha **""**.

Ou basta alterar o arquivo **"application.properties"** para inserir as credencias de preferência.

### Lombok
Na pasta **"Instalação"**, existe o arquivo **"lombok-1.18.10"**, é necessário executar e instalar em sua IDE de preferência.

## Instalação via Maven

Para instalar utilize na raiz do projeto:
```bash
 mvn clean install
```
e depois:
```bash
 java -jar target/SistemaLanchonete-0.0.1-SNAPSHOT.jar
```

## Executar

Para executar, basta utilizar o seguinte comando na raiz do projeto:
```bash
 java -jar target/SistemaLanchonete-0.0.1-SNAPSHOT.jar
```
