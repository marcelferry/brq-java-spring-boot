mvn archetype:generate  \
-DgroupId=com.brq.digital.treinamento.spring  \
-DartifactId=maven-simple-example \
-DarchetypeArtifactId=maven-archetype-quickstart  \
-DinteractiveMode=false 

cd maven-simple-example

mvn clean 

mvn package

java -cp target/maven-simple-example-1.0-SNAPSHOT.jar com.brq.digital.treinamento.spring.App

mvn install

mvn site

google-chrome target/site/


mvn archetype:generate \
-DarchetypeGroupId=org.codehaus.mojo.archetypes \
-DarchetypeArtifactId=pom-root \
-DarchetypeVersion=RELEASE \
-DgroupId=com.brq.digital.treinamento.spring \
-DartifactId=simple-parent

include

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.5.3.RELEASE</version>
		<relativePath />
	</parent>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
	</properties>

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

	<plugin>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-maven-plugin</artifactId>
	</plugin>


mvn archetype:generate  \
-DgroupId=com.brq.digital.treinamento.spring  \
-DartifactId=book-rest \
-DarchetypeArtifactId=maven-archetype-quickstart  \
-DinteractiveMode=false 

delete App.java

delete AppTest.java

create Application.java

create HelloController.java

		<!-- https://mvnrepository.com/artifact/org.mockito/mockito-core -->
		<dependency>
		    <groupId>org.mockito</groupId>
		    <artifactId>mockito-core</artifactId>
		    <version>2.7.19</version>
		    <scope>test</scope>
		</dependency>
		
		

create HelloControllerTest.java

mvn archetype:generate  \
-DgroupId=com.brq.digital.treinamento.spring  \
-DartifactId=book-service \
-DarchetypeArtifactId=maven-archetype-quickstart  \
-DinteractiveMode=false 


mvn archetype:generate -DgroupId=com.brq.digital.treinamento.spring \
-DartifactId=book-app \
-DarchetypeArtifactId=maven-archetype-webapp \
-DinteractiveMode=false

mvn eclipse:eclipse -Dwtpversion=2.0

create Application

update web.xml

