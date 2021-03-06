# Tutorial 

Este tutorial destina-se aos workshops sobre tecnologias

* Maven
* Json
* Rest
* Java
* Spring MVC
* Spring Boot

## Criando nosso primeiro projeto Maven

Vamos criar um projeto utilizando a ferramenta de linha de comando do maven. A ferramenta poderá ser baixada diretamente de https://maven.apache.org/download.cgi. Descompacte esta pasta, e configure a variável PATH para acessar a pasta `bin` do conteúdo que você descompactou. 

Utilize o comando abaixo para criar o nosso primeiro projeto. Este projeto terá sua estrutura com base no arquétipo de inicio rápido que nos dará uma classe para `com.brq.digital.workshop.App` e um caso de teste `com.brq.digital.workshop.AppTest`. Você pode personilizar o parametro `-DgroupId` e `-DartifactId` para o nome do grupo e do artefato de sua escolha.

```console
mvn archetype:generate  \
	-DgroupId=com.brq.digital.workshop  \
	-DartifactId=maven-simple-example \
	-DarchetypeArtifactId=maven-archetype-quickstart  \
	-DinteractiveMode=false
```

Agora, acessamos a pasta criada, `cd maven-simple-example`, e executamos uma limpeza no projeto, utilizando `mvn clean`, o goal `clean`, apaga a pasta target, que contém todos os arquivos gerados durante o ciclo de vida de build.

Apõs o processo de limpeza, vamos executar `mvn package`, que serã responsável por gerar o pacote de acordo com a configuração do `pom.xml`.

No caso de sucesso o nosso pacote estára compilado e disponível para execução.

```console
java -cp target/maven-simple-example-1.0-SNAPSHOT.jar com.brq.digital.workshop.App
```

Como vimos, o nosso pacote foi corretamente compilado, mas não estará disponível para ser utilizado pelos demais projetos que poderemos contruir. Para instalar ele em nosso repositório local, na pasta `.m2`, precisaremos utilizar o seguinte comando: `mvn install`.

Para conhecimento, o maven disponibiliza um comando que gera uma documentação em html. Podemos utilizar o comando `mvn site`, que disponibilizará esse conteúdo na pasta `target/site`, que poderá ser acessado via browser.

## Criando um projeto múltiplo


### Criando o projeto parent

Quando montamos uma arquitetura "foda", e desejamos que todo projeto tenha acesso aos componentes que fazem parte dela, podemos criar um projeto parent, do tipo POM, que será responsável por fornecer aos projetos filhos todas as dependências necessárias.

Vamos criar esse projeto parent da seguinte forma.

```console
mvn archetype:generate \
	-DarchetypeGroupId=org.codehaus.mojo.archetypes \
	-DarchetypeArtifactId=pom-root \
	-DarchetypeVersion=RELEASE \
	-DgroupId=com.brq.digital.workshop \
	-DartifactId=simple-parent
```

Você pode personilizar o parametro `-DgroupId` e `-DartifactId` para o nome do grupo e do artefato de sua escolha. 

Após criado, vamos entrar em nosso projeto `cd simple-parent`, e ver que existe apenas um arquivo `pom.xml`. Nesse arquivo vamos personalizar tudo o que queremos que seja herdado pelo nosso projeto.

### Abrindo o projeto no Eclipse

Vamos importar o projeto no Eclipse usando o item de menu `File -> Import...`, na caixa de dialógo `Import`, escolha `Maven -> Existing Maven Projects`. 

Na caixa de dialógo `Import Maven Projects`, utilize o botão `Browse...` para localizar a pasta onde criamos nosso projeto `simple-parent`, e selecionar o arquivo `pom.xml` . Clique em `Finish`, para o projeto ser importado.

Vamos utilizar o Eclipse para facilitar o processo de edição mas continuaremos a usar a linha de comando para compilar o nosso projeto.

### SpringBoot

Apesar de não ser necessário o uso de SpringBoot para o fim desse treinamento, vamos utilizá-lo para agilizar a construção de nossos projetos, e auxiliar nas dependências necessárias, reduzindo o processo de configuração. 

Veja que essa dependência que estamos inserindo é tambem um projeto parent, que personalizará completamente o nosso projeto. Insira o bloco abaixo no `pom.xml` 

```xml
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.5.3.RELEASE</version>
		<relativePath />
	</parent>
```

Eele fornecerá as dependências e plugins necessários para configuração, compilação e execução do seu projeto.

### Biblioteca de Testes

A nossa arquitetura utilizará bibliotecas adicionas para melhorarmos o processo de testes mockados. Vamos então, adicionar as bibliotecas de referentes aos testes no `pom.xml` de nosso projeto parent.

```xml
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

### Propriedades

Podemos definir configurações em comum para todos os projetos, essas configurações podem ser palavras chaves já reservadas e utilizadas pelo maven ou por outros plugins, como também criar novas variaveis que poderão ser utilizadas no próprio contexto do pom.

```xml
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
	</properties>
```

### Plugins

Podemos configurar plugins para adicionar funcionalidades em nosso processo de build. 

O trecho de exemplo abaixo define o plugin padrão de compilação do pacote. Perceba que no cõdigo temos um `${maven-compiler-plugin.version}` utilizado para definir a versão do plugin que será utilizada. Esse valor, `maven-compiler-plugin.version`, terá que ser definido em um block `<properties></properties>` no pom.xml ou em um projeto parent. (Não vamos inserir esse código em nosso projeto)

```xml
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

Insira o trecho abaixo em seu `pom.xml`, o qual insere as funcionalidades de compilação do SpringBoot ao seus projetos.

```xml
<build>
	<plugins>
		<plugin>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-maven-plugin</artifactId>
		</plugin>
	</plugins>
</build>
```

Após configurarmos o nosso pom.xml, teremos que realizar o processo de build, para que ele esteja disponível para os nossos projetos futuros. Use `mvn clean install`.


## Criando um projeto para o serviço REST

### Setup Inicial

Vamos criar um projeto que possa nos fornecer as interfaces de Serviço Rest. Esse serviço é apenas um exemplo e demonstra como contruir um projeto rest, simples. No nosso desenvolvimento diário, vamos utilizar serviços já implementados em outro framework/linguagem.

Vamos implementar duas interfaces rest, para listagem e busca por id de um livro.

Primeiro vamos criar o projeto usando o maven

```console
mvn archetype:generate  \
	-DgroupId=com.brq.digital.workshop  \
	-DartifactId=book-rest \
	-DarchetypeArtifactId=maven-archetype-quickstart  \
	-DinteractiveMode=false 
```

Faça a importação do projeto no Eclipse da mesma forma que fizemos com o projeto `simple-parent`.

Vamos acessar esse projeto e apagar os arquivos padrões: `App.java` e `AppTest.java`, que serão substituídos pelos arquivos que ainda iremos criar.

Abra o `pom.xml` adicione a referência a projeto parent que acabamos de criar, subistituindo todo conteúdo da tag `<project></project>`:

```xml
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.brq.digital.workshop</groupId>
	<artifactId>book-rest</artifactId>
	<packaging>jar</packaging>
	<version>1.0-SNAPSHOT</version>
	<name>Serviço REST para acesso as informações de Livros</name>
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
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
	</dependencies>
```
Quando usamos o eclipse, ele automaticamente baixa as dependências do projeto, permitindo assim que possamos continuar o desenvolvimento sem precisar executar o maven para baixá-las a cada vez que alteramos o `pom.xml`. 

Caso ele não faça, ou estivermos usando a linha de comando, vamos executar o comando `mvn clean install`, e se quiser forçar o Eclipse, use o botão direito no projeto, clique em `Run as... -> maven install`. 

Nos dois casos, por causa do uso do spring boot, o maven deverá baixar todas as bibliotecas, mas poderá ocorrer um erro conforme abaixo: 

```console
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 53.017 s
[INFO] Finished at: 2017-07-06T10:34:29-03:00
[INFO] Final Memory: 25M/229M
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.springframework.boot:spring-boot-maven-plugin:1.5.3.RELEASE:repackage (default) on project book-rest: Execution def
ault of goal org.springframework.boot:spring-boot-maven-plugin:1.5.3.RELEASE:repackage failed: Unable to find main class -> [Help 1]
[ERROR]
...
```
Esse erro será aceitável nesse momento, pois indica que o plugin do spring-boot não localizou uma classe main para usar como executável inicilizador do projeto.

Caso após esse processo, o eclipse continuar a exibir o seu projeto com erro (ícone vermelho) sem ter erro em código fonte ou xml, use o botão direito no projeto, clique em `Maven -> Update Project...`. Para que o eclipse possa acessar todas as bibliotecas utilizadas pelo maven no seu projeto. 

Vamor agora criar a classe Application.java para iniciar o SpringBoot.

```java
@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }
}
```

Com essa pequena estrutura nós ja temos um projeto web, pronto para ser executado. Para testá-lo vamos executar o comando `mvn clean install` e em seguinda executaremos o comando `mvn spring-boot:run`.

Assim que processo estiver pronto você verá a mensagem:

```console
Started Application in X.xxx seconds (JVM running for X.xxx)
``` 
Basta acessar o seu navegador com o seguinte endereço:

```
http://localhost:8080/
```

Hávera um erro, parecido ao trecho abaixo, já que ainda não colocamos nenhum conteúdo para ser visiualizado em nosso projeto.

```
Whitelabel Error Page

This application has no explicit mapping for /error, so you are seeing this as a fallback.

Thu Jul 06 07:19:26 BRT 2017
There was an unexpected error (type=Not Found, status=404).
No message available
```

Esse erro arrumaremos assim que disponibilizarmos conteúdo.

Por default o projeto é executado na porta 8080. Para mudarmos essa configuração podemos criar uma arquivo `application.properties` na pasta `src/main/resources` com o seguinte conteúdo:

```
server.port=8083
```

Assim, basta executar novamente o `mvn spring-boot:run`, e o nosso servidor estará disponível em:
```
http://localhost:8083/
```
### POJO, ValueObject, DTO - Model

Vamos adicionar a nossa classe de Pojo:

```java
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

Para fazer a correção, primeiramente vamos incluir a biblioteca JodaTime que melhora o comportamento e resolve problemas comuns da biblioteca original de `java.util.Date` da Linguagem (pré JDK 8). Inclua o seguinte bloco no `pom.xml` de nosso projeto.

``` xml
		<dependency>
		    <groupId>joda-time</groupId>
		    <artifactId>joda-time</artifactId>
		    <version>2.9.7</version>
		</dependency>	
```

### Controller

Vamos criar uma classe chamada `BookController.java`

```java
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
Observe que a classe está com a anotação `@RestController`, que já expoe os métodos da classe e já faz os tratamentos para que o retorno seja tratado como Json.

A anotação `@RequestMapping` no nível de classe ajuda a compor o endpoint da api. 

Agora podemos executar novamente o comando `mvn spring-boot:run`, que irá subir o nossa aplicação e poderemos chamar os dois endpoints definidos.

Lista de Todos os Livros
```
http://localhost:8083/books
```

Recupera o livro com Id igual a 1 
```
http://localhost:8083/books/1
```

### Persistência e Base de Dados

Para mudarmos o nosso webservice para o uso de uma base de dados precisaremos inserir a dependência da biblioteca de acesso a banco. Para esse projeto vamos usar o spring-data através da inclusão da dependência abaixo em nosso `pom.xml`. 

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
```

De acordo com a base de dados, devemos fazer a inclusão do Driver JDBC em nosso projeto, vamos para testes, utilizar o H2 Database, que permite que prototipemos projetos rapidamente, já que em um única biblioteca temos o Driver JDBC e o próprio Database Manager. Insirá o bloco abaixo no `pom.xml`

```xml
		<dependency>
		    <groupId>com.h2database</groupId>
		    <artifactId>h2</artifactId>
		    <version>1.4.193</version>
		</dependency>
```

O spring-data, abstrai o acesso a base de dados, fornecendo as principais operações atráves da implementação da interface `CrudRepository<T, K>` ou `JpaRepository<T, K>`. Assim, já teremos métodos como `findAll()`, `findOne(K id)`, `save(T model)` e etc. 

Com base nisso vamos agora criar uma nova classe em nosso projeto que será reponsável pelas chamadas ao banco de dados.

```java
@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {

}
```

Precisaremos adaptar nosso POJO adicionando as anotações referentes ao spring-data (JPA).

```java
@Entity
@Table(name="BOOK")
public class Book {

	@Id
	private Long id;
	/* ... ignorado ... */
	
}
```

Dessa forma toda a camada de persistência estará pronta para ser usada.

Vamos agora acertar os últimos detalhes do banco de dados. Adicione os seguintes itens no seu `application.properties`.

```
# Datasource
spring.datasource.url=jdbc:h2:file:~/books
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.initialize=true
spring.jpa.hibernate.ddl-auto=update
```

Os primeiro itens definem as configurações de acesso ao banco. E o último informa que toda vez que mudarmos o nossa entidade (nosso pojo) as alterações devem atualizar a estrutura das tabelas no banco. 

### Juntando tudo

Vamos agora fazer tudo funcionar junto. Vamos alterar a nossa classe `BookController` injetando uma dependência ao nossa classe `BookRepository` da seguinte forma:

```java
...
public class BookController {
	
	@Autowired
	BookRepository bookRepository;

...
```
Dessa forma, o Spring automaticamente carregará nessa variável a instância atual do `BookRepository`. 

Vamos alterar também os métodos que criamos anteriormente em nossa `BookController`.

O método de listagem agora retornará a chamada do método `findAll()`.

```java
    public List<Book> list() {
        return bookRepository.findAll();
    }
```
Já o método de busca por id, vai retornar o método `findOne(K id)`.
```java
    public Book findById( @PathVariable("id") Long bookId ) {
        return bookRepository.findOne(bookId);
    }
```

### Execução

Podemos executar agora nosso projeto através do comando `mvn spring-boot:run` e acessando:

```
http://localhost:8083/books
```

O comando provavelmente deverá retornar vazio `[]`, já que não temos dados em nossa base.

### Inserindo na mão

Para termos acesso ao nosso banco de dados podemos adicionar as seguintes linhas de configuração no arquivo `application.properties`

```
# H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2
```
Essas linhas, habilitarão uma interface de gerenciamento do banco de dados que ficará acessível em:
```
http://localhost:8083/h2
```
Nessa interface teremos acesso ao banco de dados, onde poderemos mudar a estrutura ou adicionar dados para exemplo.

### Automatizando a carga de dados

Podemos também colocar scripts para criação da tabela e para inserção de dados junto ao início da aplicação.

Vamos colocar as duas linhas no `application.properties`. 

```
spring.datasource.schema=classpath:schema.sql
spring.datasource.data=classpath:data.sql
```

Vamos agora criar na pasta `src/main/resources` um arquivo `schema.sql` com o seguinte contéudo:

```sql
DROP TABLE IF EXISTS book;

CREATE TABLE IF NOT EXISTS book (
  id              INT     NOT NULL PRIMARY KEY,
  autor       	  VARCHAR(200) NOT NULL,
  titulo          VARCHAR(200) NOT NULL,
  categoria       VARCHAR(200) NOT NULL,
  preco			  DECIMAL(20, 2),
  dataCadastro	  DATE,
  ativo			  BOOLEAN
);
```
E também criaremos um arquivo `data.sql` com o seguinte conteúdo.

```sql
INSERT INTO book VALUES (1, 'Richard', 'Learning Spring Boot', 'Desenvolvimento', 89.90, '2017-07-01', true);
INSERT INTO book VALUES (2, 'Robert', 'Spring Boot in Action', 'Desenvolvimento', 57.90, '2017-07-03', true);
```

Ao iniciarmos a nossa aplicação novamente a tabela já será criada, e os dados serão inseridos. Agora poderemos novamente chamar a aplicação em:

```
http://localhost:8083/books
```

E veremos os dois novos livros cadastrados.

```json
[{"id":1,"titulo":"Learning Spring Boot","autor":"Richard","categoria":"Desenvolvimento","preco":89.90,"dataCadastro":null,"ativo":true},{"id":2,"titulo":"Spring Boot in Action","autor":"Robert","categoria":"Desenvolvimento","preco":57.90,"dataCadastro":null,"ativo":true}]
```

### Spring 

O Spring nos fornece uma biblioteca que também automatiza o nosso trabalho de criação de serviços rest. É o spring-data-rest. Para ver ela em ação basta adicionar a seguinte dependência em nosso `pom.xml`.

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-rest</artifactId>
		</dependency>
```
E remover ou comentar os métodos criados em nossa classe `BookController`

```java 
//    @RequestMapping(method= RequestMethod.GET)
//    public List<Book> list() {
//        return bookRepository.findAll();
//    }
//    
//    @RequestMapping(value="{id}", method= RequestMethod.GET)
//    public Book findById( @PathVariable("id") Long bookId ) {
//        return bookRepository.findOne(bookId);
//    }
```

Podemos agora executar novamente nosso projeto e chamar a url: 


```
http://localhost:8083/books
```

Perceba que o método funcionará apesar de não estar disponível nenhum método mapeado para o esse endpoint, mas o resultado será um pouco diferente:

```json
{
  "_embedded" : {
    "books" : [ {
      "titulo" : "Learning Spring Boot",
      "autor" : "Richard",
      "categoria" : "Desenvolvimento",
      "preco" : 89.90,
      "dataCadastro" : null,
      "ativo" : true,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8083/books/1"
        },
        "book" : {
          "href" : "http://localhost:8083/books/1"
        }
      }
    }, {
      "titulo" : "Spring Boot in Action",
      "autor" : "Robert",
      "categoria" : "Desenvolvimento",
      "preco" : 57.90,
      "dataCadastro" : null,
      "ativo" : true,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8083/books/2"
        },
        "book" : {
          "href" : "http://localhost:8083/books/2"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8083/books{?page,size,sort}",
      "templated" : true
    },
    "profile" : {
      "href" : "http://localhost:8083/profile/books"
    },
    "search" : {
      "href" : "http://localhost:8083/books/search"
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 2,
    "totalPages" : 1,
    "number" : 0
  }
}
```

## Criando um projeto para a camada service

### Setup Inicial

Vamos criar um componente que será utilizado pelo nossa camada principal para acesso aos dados:

```console
mvn archetype:generate  \
-DgroupId=com.brq.digital.workshop  \
-DartifactId=book-service \
-DarchetypeArtifactId=maven-archetype-quickstart  \
-DinteractiveMode=false 
```

Após a criação, acesse a pasta do projeto `cd book-service`, abra o `pom.xml`, e adicione a referência a projeto parent que de criamos. (Podemos fazer a importação do projeto no Eclipse da mesma forma que fizemos com o projeto `simple-parent`)

```xml
    <modelVersion>4.0.0</modelVersion>
	<groupId>com.brq.digital.workshop</groupId>
	<artifactId>book-service</artifactId>
	<packaging>jar</packaging>
	<version>1.0-SNAPSHOT</version>
	<name>Projeto camada de acesso a dados</name>

	<properties>
	    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.7</java.version>
	</properties>

	<dependencies>
		<dependency>
	      <groupId>junit</groupId>
	      <artifactId>junit</artifactId>
	      <version>3.8.1</version>
	      <scope>test</scope>
	    </dependency>
	</dependencies>
```

Como vamos usar datas em nosso projeto, vamos incluir a biblioteca JodaTime que melhora comportamento e resolve problemas comuns da biblioteca original de `java.util.Date` da Linguagem. Tambẽm será necessário adicionar a biblioteca de registro de logs. Inclue essas duas dependências em seu `pom.xml`

```xml
		<dependency>
		    <groupId>org.apache.logging.log4j</groupId>
		    <artifactId>log4j-core</artifactId>
		    <version>2.7</version>
		</dependency>
		<dependency>
		    <groupId>joda-time</groupId>
		    <artifactId>joda-time</artifactId>
		    <version>2.9.7</version>
		</dependency>		
```

### Modelo

Vamos criar nosso Pojo que será responsável por representar o retorno do Rest.

```java
public class BookDTO {

	private Long id;
	private String titulo;
	private String autor;
	private String categoria;
	private BigDecimal preco;
	private LocalDate dataCadastro;
	private boolean ativo;
	
	/* getter e setter omitidos */
}
```

### Serviços
Vamos adicionar as dependências necessárias para criarmos a parte de componentes do nossa camada de service.

``` xml
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${spring.version}</version>
		</dependency>
```
Vamos adicionar nas `<propierties>` a seguinte linha:

```xml
		<spring.version>4.2.1.RELEASE</spring.version>
```
Para tratarmos a chamada do Serviço Rest, vamos adicionar as dependências para tratamento da requisição.
```xml
		<dependency>
		    <groupId>javax.ws.rs</groupId>
		    <artifactId>javax.ws.rs-api</artifactId>
		    <version>2.0.1</version>
		</dependency>
		<dependency>
		    <groupId>org.glassfish.jersey.core</groupId>
		    <artifactId>jersey-client</artifactId>
		    <version>2.24</version>
		</dependency>
		<dependency>
		    <groupId>org.glassfish.jersey.core</groupId>
		    <artifactId>jersey-common</artifactId>
		    <version>2.24</version>
		</dependency>
		<dependency>
	       <groupId>org.glassfish.jersey.media</groupId>
	       <artifactId>jersey-media-json-jackson</artifactId>
	       <version>2.24</version>
	    </dependency>
```


Nesse projeto vamos criar uma interface `BookService.java`:

```java
public interface BookService {
	BookDTO obtemLivro(Integer bookId);
}
```

E também criaremos uma classe `BookServiceImpl` que implementará `BookService`

```java
@Component
public class BookServiceImpl implements BookService {
	private static final Logger LOGGER = LogManager.getLogger("LOG_NOME");
	
	private WebTarget target;
	
	public BookServiceImpl() {
		target = ClientBuilder.newClient().target("http://localhost:8083");
	}
	
	public BookDTO obtemLivro(Integer bookId){
		
		Response response = target
				.path("books")
				.path(String.valueOf(bookId))
				.request()
				.get();
		
		try{
			return response.readEntity(new GenericType<BookDTO>() {
		    });
		}catch(Exception e){
			LOGGER.info("BOOK", e);
			throw new RuntimeException("ERRO");
		}
	}
}
```

### Execução

Como esse projeto é um componente, precisaremos fazer o `mvn clean install` para que ele seja implantado em nosso repositório local, e possamos utilizá-lo em nosso projeto principal.

## Criando nosso Projeto Principal

### Setup Inicial

Vamos criar um projeto Web. Para isso usaremos um arquétipo do tipo webapp.

```console
mvn archetype:generate -DgroupId=com.brq.digital.workshop \
-DartifactId=book-app \
-DarchetypeArtifactId=maven-archetype-webapp \
-DinteractiveMode=false
```

Podemos adicionar todos as configurações comuns do Eclipse antes de importá-lo em nosso projeto usando o comando `mvn eclipse:eclipse -Dwtpversion=2.0`.

Podemos fazer a importação do projeto no Eclipse da mesma forma que fizemos com o projeto `simple-parent`.

```xml
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.brq.digital.workshop</groupId>
	<artifactId>book-app</artifactId>
	<packaging>war</packaging>
	<version>1.0-SNAPSHOT</version>
	<name>Camada de Visao do Projeto de Livros</name>

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
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
	</dependencies>

	<build>
		<finalName>book-app</finalName>
	</build>
```

Se quiser acertar o carregamento das dependências, pode executar o comando `mvn clean package`. Se o fizer, poderá ter um erro como esse: 


```console
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 53.017 s
[INFO] Finished at: 2017-07-06T10:34:29-03:00
[INFO] Final Memory: 25M/229M
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.springframework.boot:spring-boot-maven-plugin:1.5.3.RELEASE:repackage (default) on project book-rest: Execution def
ault of goal org.springframework.boot:spring-boot-maven-plugin:1.5.3.RELEASE:repackage failed: Unable to find main class -> [Help 1]
[ERROR]
...
```

Isso pode ocorrer pois ainda não criamos nossa classe main do SpringBoot.

### Vamos nosso Hello World em Rest

Vamos criar uma classe Application: 

```java
@RestController
@SpringBootApplication
@EnableAutoConfiguration
public class Application {
	
    
    @RequestMapping("/")
    public String index() {
        return "Hello World";
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

Vamos atualizar o nosso web.xml:

```xml
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://java.sun.com/xml/ns/javaee"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	version="3.0">
</web-app>
```

Para testá-lo vamos executar o comando `mvn clean install` e em seguinda executaremos o comando `mvn spring-boot:run`.

Assim que o processo estiver pronto você verá a mensagem:

```
Started Application in X.xxx seconds (JVM running for X.xxx)
``` 
Basta acessar o seu navegador com o seguinte endereço:

```
http://localhost:8080/
```
O resultado será:
```
Hello World
```

## Melhorando o nosso projeto

### Configurando o MVC

Vamos agora movimentar a `index.jsp` que está em nosso projeto para a pasta `WEB-INF/views`. 

Vamos remover a anotação `@EnableAutoConfiguration` da classe `Application` e criar uma nova classe com o seguinte conteúdo

```java
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.brq.digital.workshop"})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
} 
```

### Entendendo as anotações

A anotação `@Configuration` define que essa classe deverá ser lida como um dos substitutos dos arquivos de configuração do Spring. A anotação `@EnableWebMvc` avisa ao Spring que estamos utilizando um projeto que possui anotações do SpringMvc e que ele deverá carregá-las. A anotação `@ComponentScan` determinar em quais pacotes eu devo buscar as classes anotadas.

A anotação `@Bean` informa ao Spring que ele deve executar aquele método e com o seu resultado criar um bean com o nome do método e que será injetado quando o Spring localizar uma das situações:

```java
	@Autowired
	ViewResolver viewResolver;
    
    /* ou */
    
    @Autowired
    @Qualifier("viewResolver")
	ViewResolver viewResolver;
    
    /* ou */
    
	@Resource
	ViewResolver viewResolver;
    
    /* ou */
    
    @Resource
    @Qualifier("viewResolver")
	ViewResolver viewResolver;
        
    /* ou */
    
	@Inject
	ViewResolver viewResolver;
    
    /* ou */
    
    @Inject
    @Qualifier("viewResolver")
	ViewResolver viewResolver;
    
```

Nesse caso o viewResolver não será acessado diretamente, mas é configurado para que o Spring saiba como tratar o retorno de uma controller. No caso configurado acima, quando um método em uma `Controller` retornar uma String como exemplo: `home`, ele irá procurar na pasta `WEB-INF/views` um arquivo com a extensão `.jsp`, assim: `WEB-INF/views/home.jsp`. Se o método retornar uma estrutura de pasta como por exemplo `erro/erro`, ele vai tentar localizar o arquivo: `WEB-INF/views/erro/erro.jsp`.


### Chamando nossa página de index

Criaremos agora uma classe chamada `IndexController.java`

```java
@Controller
public class IndexController {

}
```

e mover o método:

```java   
    @RequestMapping("/")
    public String index() {
        return "Hello World";
    }
```

do arquivo `Application.java` para o arquivo `IndexController.java` fazendo uma pequena alteração no retorno do método:

```java
    @RequestMapping("/")
    public String index() {
        return "index";
    }
```

A anotação `@Controller` torna essa classe rastreável pelo Framework do Spring no momento da configuração do projeto, quando usaremos a anotaçao `@ComponentScan`, para determinar quais pacotes devem ser escanados a procura de componentes. O `@Controller` é apenas uma especialização da anotação `@Component`. Em combinação com a anotação `@RequestMapping` gera o mapeamento de um url para o serviço dessa classe. 

Para entendermos a composição da URL imaginemos a seguinte classe de exemplo: 

```java
@Controller
@RequestMapping("pessoas")
public class PessoaController {
	@RequestMapping("listar")
    public String listarPessoas() {
        return "lista";
    }
    
}
```
Considerando que a nossa aplicação rode em uma URL como `http://localhost/`, a classe acima criaria um mapeamento acessível pela composição do `@RequestMapping` anotado na classe + o `@RequestMapping` anotado no método, ficando assim:

```
http://localhost/pessoas/listar
```

Como estamos usando o SpringMVC, um retorno do tipo String será tratado como um mapeamento para um arquivo .jsp dentro da pasta `WEB-INF/views`, conforme definimos no nosso bean `ViewResolver` declarado na classe `WebConfig`


Para testá-lo vamos executar o comando `mvn clean install` e em seguinda executaremos o comando `mvn spring-boot:run`.

Assim que o processo estiver pronto, basta acessar o seu navegador com o seguinte endereço:

```
http://localhost:8080/
```
O resultado será:
```
Hello World
```

### Adicionando uma nova Controller

Vamos criar uma classe BookController para atuar com as chamadas relacionadas ao Serviço de Livros.

```java
@Controller
@RequestMapping("books")
public class BookController {

}

``` 

Como já vimos, o `@RequestMapping` vai determinar o mapeamento dessa classe, então os métodos dela serão acessíveis através de:

```
http://localhost:8080/books/
```

Vamos agora criar um método: 

```java
	@RequestMapping("{bookId}")
	public ModelAndView obtemLivro(@PathVariable("bookId") Integer bookId){
		ModelAndView view = new ModelAndView();
		view = new ModelAndView("livro");	
		return view;
	}
```

Nesse método que acabamos de criar, o mapeamento é feito para uma variável identificada por `{ }`. Nesse caso ela se chama `{bookId}`. A chamada para acesso a esse método obrigatóriamente deverá conter o seguinte formato:

```
http://localhost:8080/books/1
```

Sendo 1, o valor que desejamos atribuir a variavel. Como vemos na implementação do método, a anotação `@PathVariable("bookId")` atribui o valor que for passado na url para a variavel `Integer bookId` do método. Dessa forma essa URL espera um valor inteiro. Caso você passe uma string como no exemplo:

```
http://localhost:8080/books/listar
```

O Spring continuará redirecionando a chamada para o mesmo método, mas retornará um erro, já que o valor "listar", não pode ser convertido para inteiro.

Esse método poderia retornar uma String `livro`, mas optamos por usar um objeto um pouco mais completo chamado `ModelView`, que permitira definir o nome da view e quais valores passaremos para ela.

Antes de testarmos precisaremos criar um arquivo `livro.jsp` em `WEB-INF/views/livro.jsp`. Vamos nesse momento usar um conteúdo assim:

```html
<html>
<body>
<h2>Livro!</h2>
</body>
</html>
```

Podemos agora testar novamente nossa aplicação executando o comando `mvn spring-boot:run`.

E acessar a url:

```
http://localhost:8080/books/1
```

Nós criamos apenas um mapeamento, veja que se você tentar acessar um endereço não configurado para ver o que acontece:

```
http://localhost:8080/books/
```

### Chamando nossa camada de Service

Vamos incluir em nosso projeto a camada de service que criamos anteriormente. Para isso vamos incluir a sua dependência em nosso `pom.xml`

```xml
		<dependency>
			<groupId>com.brq.digital.workshop</groupId>
			<artifactId>book-service</artifactId>
			<version>1.0-SNAPSHOT</version>
		</dependency>
```

Lembre-se de ajustar a versão de acordo com a versão compilada e instalada em seu repositório local.

Vamos alterar a `BookController.java` e adicionar na classe a injeção de dependência do `BookService`.

```java	
	@Autowired
	BookService bookService;
```

Depois vamos alterar o nosso método `obtemLivro` para recuperar um livro com base no `bookId`.

```java
		ModelAndView view = new ModelAndView();
		
		try{
			BookDTO book = bookService.obtemLivro(bookId);
			view = new ModelAndView("/livro");
			view.addObject("book",book);
		} catch (Exception e) {
			view.setViewName("erro/erro");
			view.addObject("exception", mensagem);
			return model;
		}	
		return view;
```

E vamos criar um JSP para melhorar a visualização do dados. Para que o spring-boot possa processar corretamente as JSPs, vamos incluir duas novas dependências.

```xml
	    <dependency>
	        <groupId>org.apache.tomcat.embed</groupId>
	        <artifactId>tomcat-embed-jasper</artifactId>
	        <scope>provided</scope>
	    </dependency>
	    <dependency>
	        <groupId>javax.servlet</groupId>
	        <artifactId>jstl</artifactId>
	    </dependency>
```

E alterar o nosso `livro.jsp` para o seguinte conteúdo:

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<body>
<h2>Livro!</h2>
<p>${book.id}</p>
<p>${book.titulo}</p>
<p>${book.autor}</p>
<p>${book.categoria}</p>
<p>${book.preco}</p>
</body>
</html>
```
Podemos agora testar novamente nossa aplicação executando o comando `mvn spring-boot:run`.


E acessar a url:

```
http://localhost:8080/books/1
```

Para o correto funcionamento a aplicação book-rest precisa estar sendo executada também na porta 8083. 

# Compilando todos os projetos

Para não precisarmos entrar em cada pasta e executar o processo de build de cada um dos projetos podemos criar um projeto parent, ou gerar apenas um `pom.xml` na raiz dos projetos, com o seguinte conteúdo.

```xml
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


