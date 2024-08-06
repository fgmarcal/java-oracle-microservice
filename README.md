# Microsserviço de Envio de Relatórios por Email

Este projeto é um microsserviço backend desenvolvido em Java utilizando Spring Boot para gerar e enviar relatórios por email utilizando a Oracle Cloud.

## Setup Inicial

1. **Conta na Oracle Cloud:** Para inicializar o projeto, é necessário criar uma conta gratuita na [Oracle Cloud](https://www.oracle.com/cloud/free/).

2. **Gerar API Key:**
    - Acesse o Oracle Cloud Console.
    - Vá para "Identity & Security" > "Users".
    - Selecione seu usuário.
    - Vá para "API Keys" e clique em "Add API Key".
    - Baixe o arquivo key.pem e guarde em um local seguro.

3. **Configurar a API Key:**
    - Na raiz do projeto, crie uma pasta chamada `.oci`.
    - Dentro da pasta `.oci`, crie um arquivo chamado `config` com as seguintes informações:

      ```
      [DEFAULT]
      user=<seu usuário>
      fingerprint=<seu fingerprint>
      tenancy=<seu tenancy>
      region=<sua região>
      key_file=<caminho para o key.pem>
      ```
## Configuração do Bucket na Oracle

1. Busque pelo serviço de *Bucket* do *Object Storage* (utilize a barra de pesquisa)
2. Clique em *Create Bucket*
3. Nomeie seu bucket como quiser, este nome será usado na propriedade bucket.name do *application.properties*.
4. Selecione o bucket criado para encontrar a informação do *namespace* que deverá ser preenchido no bucket.namespace do *application.properties*.
5. O arquivo *report.html* será usário como exemplo nesta aplicação e está salvo na raiz do projeto.
    - Ainda no serviço de Bucket, faça o upload do arquivo em **Objects**. É aqui que se inclui o arquivo que será enviado pelo serviço de email.

## Configurações do Serviço de Email na Oracle

1. Habilite o serviço *Email Delivery* na Oracle Cloud (busque na barra de pesquisa por 'email')
2. Em *List Scope*, selecione o Compartment (pode ser root)
2. Inclua o email responsável pelo envio em *Approved Senders*. Recomenda-se utilizar o mesmo e-mail do usuário cadastrado.
3. Salve o usuário e senha gerados, disponíveis em *OCID*.
4. Em *Configuration* há os valores do endpoint SMPT e a porta.
2. Renomeie o arquivo 'blank_application_properties' para *application.properties* e preencha os campos correspondentes à sua conta da Oracle.


## Dependências

Adicione as seguintes dependências no seu arquivo `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>com.oracle.oci.sdk</groupId>
        <artifactId>oci-java-sdk-addons-resteasy-client-configurator</artifactId>
    </dependency>
    <dependency>
        <groupId>com.oracle.oci.sdk</groupId>
        <artifactId>oci-java-sdk-objectstorage</artifactId>
    </dependency>
    <dependency>
        <groupId>com.oracle.oci.sdk</groupId>
        <artifactId>oci-java-sdk-common-httpclient-jersey</artifactId>
    </dependency>
    <dependency>
        <groupId>com.oracle.oci.sdk</groupId>
        <artifactId>oci-java-sdk-core</artifactId>
        <version>3.44.4</version>
    </dependency>
</dependencies>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.oracle.oci.sdk</groupId>
            <artifactId>oci-java-sdk-bom</artifactId>
            <version>3.44.4</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        <dependency>
            <groupId>com.oracle.oci.sdk</groupId>
            <artifactId>oci-java-sdk-common-httpclient-jersey</artifactId>
            <version>3.44.4</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```
