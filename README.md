[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=alapierre_ksef-java-rest-client&metric=alert_status)](https://sonarcloud.io/dashboard?id=alapierre_java-ksef-fa)
[![Renovate enabled](https://img.shields.io/badge/renovate-enabled-brightgreen.svg)](https://renovatebot.com/)
[![Maven Central](http://img.shields.io/maven-central/v/io.alapierre.ksef.fa/java-ksef-fa)](https://search.maven.org/artifact/io.alapierre.ksef.fa/java-ksef-fa)

# KSeF FA Java model

Model parser and validator for  XML FA(2) KSeF structure. 

# Sample

## Parse and validate FA(2) to Java model

````java
FakturaSerializer serializer = new FakturaSerializer();
Faktura invoice = serializer.fromFile(new File("src/test/resources/fa.xml"), true);
````

## Serialize model to XML (without validation)

````java
FakturaSerializer fakturaSerializer = new FakturaSerializer();

Faktura invoice = fakturaSerializer.create();

Faktura.Podmiot1 podmiot1 = new Faktura.Podmiot1();
TAdres address = new TAdres();
address.setAdresL1("ul. Kolejowa 123");
address.setAdresL2("05-092 Łomianki");

podmiot1.setAdres(address);
invoice.setPodmiot1(podmiot1);

val str = fakturaSerializer.toString(invoice, false);

System.out.println(str);
````

# How to use with maven

Stable project attracts are available in Maven Central, just add dependency to your project:

````xml
<dependency>
    <groupId>io.alapierre.ksef.fa</groupId>
    <artifactId>java-ksef-fa</artifactId>
    <version>2.0.0</version>
</dependency>
````

# Another connected GitGub Repositories

- [gobl.java](https://github.com/alapierre/gobl.java) Java GOBL implementation
- [gobl.java.ksef](https://github.com/alapierre/gobl.java.ksef) GOBL to FA(2) Converter


# Build Requirements

The project can be built on JDK17+.

Building the API client library requires:
1. Java 17+
2. Maven
