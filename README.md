# Tutorial 

Este tutorial destina-se aos workshops sobre tecnologias

* Maven
* Json
* Rest
* Java
* Spring MVC
* Spring Boot

## Criando nosso primeiro projeto Maven

Vamos criar um projeto utilizando a ferramenta de linha de comando do maven. Este projeto terá sua estrutura com base no arquétipo de inicio rápido que nos dara uma classe para App e um caso de teste AppTest.

```
mvn archetype:generate  \
	-DgroupId=com.brq.digital.workshop  \
	-DartifactId=maven-simple-example \
	-DarchetypeArtifactId=maven-archetype-quickstart  \
	-DinteractiveMode=false
```

Agora, acessamos a pasta criada, `cd maven-simple-example`, e executamos uma limpeza no projeto, utilizando `mvn clean`, o goal `clean`, apaga a pasta target, que contém todos os arquivos gerados durante o ciclo de vida de build.

Apõs o processo de limpeza, vamos executar `mvn package`, que serã responsável por gerar o pacote de acordo com a configuração do `pom.xml`.

No caso de sucesso o nosso pacote estára compilado e disponível para execução.

```
java -cp target/maven-simple-example-1.0-SNAPSHOT.jar com.brq.digital.workshop.App
```

Como vimos, o nosso pacote foi corretamente compilado, mas não estará disponível para ser utilizado pelos demais projetos que poderemos contruir para utilizá-lo. Para instalar ele em nosso repositório local, na pasta `.m2`, precisaremos utilizado os seguinte comando: `mvn install`.

O maven disponibiliza um comando que gera uma documentação em html. Podemos utilizar o comando `mvn site`, que disponibilizará esse conteúdo na pasta `target/site`, que poderá ser acessado via browser.

## Criando um projeto múltiplo


### Criando o projeto parent

Quando montamos uma arquitetura "foda", e desejamos que todo projeto tenha acesso as componentes que fazem parte dela, podemos criar um projeto parent, do tipo POM, que será responsáveis por fornecer aos projetos filhos todas as dependências necessárias para gerar a estrutura.

Vamos criar esse projeto parent da seguinte forma.

```
mvn archetype:generate \
	-DarchetypeGroupId=org.codehaus.mojo.archetypes \
	-DarchetypeArtifactId=pom-root \
	-DarchetypeVersion=RELEASE \
	-DgroupId=com.brq.digital.workshop \
	-DartifactId=simple-parent
```

Para agilizar o nosso workshop, vamos utilizar o SpringBoot que nos auxiliará definindo as dependências necessárias e diminuindo o processo de configuração.

```
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.5.3.RELEASE</version>
		<relativePath />
	</parent>
```
Essa dependencia fornecerá as dependencias e plugins necessários para configuração, compilação e execução do seu projeto.

Vamos colocar as bibliotecas de referentes aos testes.

```
    	<dependencies>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-core</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.jmockit</groupId>
			<artifactId>jmockit</artifactId>
			<version>1.33</version>
			<scope>test</scope>
		</dependency>
	</dependencies>
```		

Podemos definir configurações em comum para todos os projetos.

```
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
	</properties>
```

E também configurar os plugins, conforme esse exemplo

```
	<plugin>
		<groupId>org.apache.maven.plugins</groupId>
		<artifactId>maven-compiler-plugin</artifactId>
		<version>${maven-compiler-plugin.version}</version>
		<inherited>true</inherited>
		<configuration>
			<source>1.8</source>
			<target>1.8</target>
		</configuration>
	</plugin>
```

Vamos colocar o puglins do SpringBoot.

```
	<plugin>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-maven-plugin</artifactId>
	</plugin>
```

## Criando um projeto para o serviço REST

Vamos criar um projeto que possa nos fornecer as interfaces de Serviço Rest. Vamos implementar duas interfaces rest, para listagem e busca por id de um livro.

Primeiro vamos criar o projeto usando o maven

```
mvn archetype:generate  \
	-DgroupId=com.brq.digital.workshop  \
	-DartifactId=book-rest \
	-DarchetypeArtifactId=maven-archetype-quickstart  \
	-DinteractiveMode=false 
```

Vamos acessar esse projeto e apagar os arquivos padrões: `App.java` e `AppTest.java`. 

Abra o `pom.xml` adicione a referencia a projeto parent:

```
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.brq.digital.workshop</groupId>
	<artifactId>book-rest</artifactId>
	<packaging>jar</packaging>
	<version>1.0-SNAPSHOT</version>
	<name>Servico REST para acesso as informações de Livros</name>
	<parent>
		<groupId>com.brq.digital.workshop</groupId>
		<artifactId>simple-parent</artifactId>
		<version>1.0-SNAPSHOT</version>
		<relativePath>../simple-parent</relativePath>
	</parent>
	<properties>
		<java.version>1.7</java.version>
	</properties>
	<dependencies>
	</dependencies>
```
Vamor criar a classe Application.java para iniciar o SpringBoot.

```
@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }

}
```

Com essa pequena estrutura nós ja temos um projeto web, pronto para ser executado. Para testá-lo vamos executar o comando `mvn clean install` e em seguinda executaremos o comando `mvn spring-boot:run`.

Assim que processo estiver pronto você verá a mensagem:

```
Started Application in X.xxx seconds (JVM running for X.xxx)
``` 

Basta acessar o seu navegador com o seguinte endereço:

```
http://localhost:8080/
```

Vamos adicionar a nossa classe de Pojo:

```
public class Book {

	private Long id;
	private String titulo;
	private String autor;
	private String categoria;
	private BigDecimal preco;
	private LocalDate dataCadastro;
	private boolean ativo;
	
    /* ... getter e setter omitido ... * / 
    
}
```

Veremos que a classe apresenta um erro no campo LocalDate (Se você já estiver usando Java8 a classe poderá ser importada diretamente do JDK, mas nesse projeto a intenção é utilizar a biblioteca joda-time). 

Para fazer a correção, primeiramente vamos incluir a biblioteca JodaTime que melhora comportamento e resolve problemas comuns da biblioteca original de `java.util.Date` da Linguagem (pré JDK 8).

``` 
		<dependency>
		    <groupId>joda-time</groupId>
		    <artifactId>joda-time</artifactId>
		    <version>2.9.7</version>
		</dependency>	
```

Vamos criar a classe BookController.java

```
@RestController
@RequestMapping("books")
public class BookController {

    @RequestMapping(method= RequestMethod.GET)
    public List<Book> list() {
        return Arrays.asList(new Book(), new Book());
    }

    @RequestMapping(value="{id}", method= RequestMethod.GET)
    public Book findById( @PathVariable("id") Long bookId ) {
        return new Book();
    }

}
```
Observe que a classe está com a anotação `@RestController`, que já expoe os métodos da classes e já faz os tratamentos para que o retorno seja tratado como Json.

A anotação `@RequestMapping` no nível de classe ajuda a compor o endpoint da api. 

Agora podemos executar novamente o comando `mvn spring-boot:run`, que irá subir o nossa aplicação e poderemos chamar os dois endpoints definidos.

Lista de Todos os Livros
```
http://localhost:8080/books
```

Recupera o livro com Id igual a 1 
```
http://localhost:8080/books/1
```

Para mudarmos o nosso webservice para o uso de uma base de dados precisaremos inserir a dependência da biblioteca de acesso a banco. Para esse projeto vamos usar o spring-data através da inclusão da dependência abaixo: 

```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
```

De acordo com a base de dados, devemos fazer a inclusão do Driver JDBC em nosso projeto, vamos para testes utilizar o H2 Database, que permite que prototipemos projetos rapidamente, já que em um única biblioteca temos o Driver JDBC e o próprio Database Manager.

```
		<dependency>
		    <groupId>com.h2database</groupId>
		    <artifactId>h2</artifactId>
		    <version>1.4.193</version>
		</dependency>
```

Vamos agora criar uma nova classe em nosso projeto que será reponsável pelas chamadas ao banco de dados.

```
@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {

}
```

Precisaremos adaptar nosso POJO adicionando as anotações referentes ao spring-data (JPA).

```
@Entity
@Table(name="BOOK")
public class Book {

	@Id
	private Long id;
	/* ... ignorado ... */
	
}
```


```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-rest</artifactId>
		</dependency>
```

## Criando um projeto para a camada service

```
mvn archetype:generate  \
-DgroupId=com.brq.digital.workshop  \
-DartifactId=book-service \
-DarchetypeArtifactId=maven-archetype-quickstart  \
-DinteractiveMode=false 
```

Vamos incluir a biblioteca JodaTime que melhora comportamento e resolve problemas comuns da biblioteca original de `java.util.Date` da Linguagem.
Tambẽm será necessário adicionar a biblioteca de registro de logs, e a biblioteca de manipulação de chamadas Rest.

``` 
		<dependency>
		    <groupId>org.apache.logging.log4j</groupId>
		    <artifactId>log4j-core</artifactId>
		    <version>2.7</version>
		</dependency>
		<dependency>
		    <groupId>javax.ws.rs</groupId>
		    <artifactId>javax.ws.rs-api</artifactId>
		    <version>2.0.1</version>
		</dependency>
		<dependency>
		    <groupId>joda-time</groupId>
		    <artifactId>joda-time</artifactId>
		    <version>2.9.7</version>
		</dependency>		
```

```
mvn archetype:generate -DgroupId=com.brq.digital.workshop \
-DartifactId=book-app \
-DarchetypeArtifactId=maven-archetype-webapp \
-DinteractiveMode=false
```

`mvn eclipse:eclipse -Dwtpversion=2.0`

create Application

update web.xml

# Compilando todos os projetos

Para não precisarmos entrar em cada pasta e executar o processo de build de cada um dos projetos podemos criar um projeto parent, ou gerar apenas um `pom.xml` na raiz dos projetos, com o seguinte conteúdo.

```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.brq.digital.workshop</groupId>
	<artifactId>simple-compiler</artifactId>
	<version>1.0-SNAPSHOT</version>
	<packaging>pom</packaging>
	<name>simple-compiler</name>
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.7</java.version>
	</properties>
	<modules>
		<module>simple-parent</module>
		<module>book-rest</module>
		<module>book-service</module>
		<module>book-app</module>
  	</modules>
</project>
```

Agora basta executar um comando `mvn clean install` para que processo faça a compilação de todos os projetos, na sequência definida no `pom.xml`.

Caso ocorra um erro na compilação de um projeto, você pode realizar a correção e continuar o processo a partir do último projeto compilado com sucesso, utilizando o comando `mvn <goals> -rf :<projeto>`. Por exemplo caso o projeto `book-service` de algum problema, você poderia continuar utilizando o comando: `mvn clean install -rf :book-service`


