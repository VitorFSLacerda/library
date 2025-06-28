Mini-Projeto: Sistema de Biblioteca
Descrição: 
Você está encarregado de desenvolver um sistema de biblioteca.
A biblioteca deve gerenciar livros, autores e empréstimos.
O sistema deve permitir o cadastro de livros e autores, além de possibilitar o empréstimo e devolução de livros para os usuários.


Requisitos:

1. Desenvolver as classes já definidas no projeto (você está livre para criar quaisquer outras classes que achar necessário)
2. Implemente métodos para realizar as seguintes operações:
	- Cadastro de um novo livro e autor.
	- Empréstimo de um livro para um usuário.
	- Devolução de um livro por um usuário.
3. Considere as seguintes interações:
	- Um livro só pode ser emprestado se estiver disponível.
	- Um livro emprestado deve ser marcado como tal e associado ao usuário que o pegou emprestado.
	- Um usuário só pode pegar emprestado um livro por vez.
	- A devolução de um livro deve atualizar seu status para disponível.

Você pode criar todos os cenários e testes direto na função main. Por exemplo: cadastrar um livro e usuário, emprestar o
livro para esse usuário e então tentar emprestar um segundo livro pra ele.
Não se esqueça de usar o System.out.println para exibir no console o estado atual da sua aplicação.
Por exemplo: printar a lista de livros da biblioteca sempre que um novo livro for cadastro, ou printar um alerta quando
um usuário tentar pegar emprestado um livro indisponível.

Critérios de Avaliação:
	- Estruturação adequada das classes e uso correto de conceitos de orientação a objetos.
	- Implementação dos métodos de forma clara e funcional.
	- Tratamento adequado de situações como livros já emprestados e tentativas de empréstimo além do limite por usuário.
	- Documentação clara e concisa, explicando sua lógica e tomada de decisão.


## Library Management System

	This is a mini-project developed as a technical test. It simulates a basic **library system**, 
	allowing you to manage authors, books, users, and loan operations (borrow/return).

---

## Features

	- Register authors and books
	- Register users
	- Borrow and return books
	- Prevent borrowing if:
		- The user already has a book
		- All copies of the book are already borrowed
	- Includes exception handling and unit tests with JUnit
	- Auto-generated documentation using Javadoc

---

## Requirements

	- Java 21+
	- Gradle (or use the wrapper: `./gradlew`)
	- Git (optional, for cloning the repository)

---

## Running the project

	1. Clone the repository
	
		cd library/app

	2. Compile and run the application

		./gradlew run

	Or open and run Main.java in your IDE

---

## Running Tests

	Unit tests are written using JUnit 5. To run the tests:

		./gradlew test

	The test report will be generated at:

		app/build/reports/tests/test/index.html

---

## Project Structure

	app/
	├── src/
	│   ├── main/
	│   │   ├── java/
	│   │   │   ├── models/         # Core classes: Book, Author, User, Library, etc.
	│   │   │   ├── exceptions/     # Custom exceptions
	│   │   │   └── utils/          # Centralized messages
	│   └── test/
	│       └── java/models/        # JUnit tests
	├── build.gradle.kts           # Gradle configuration
	└── README.md

##  Generating Documentation (Javadoc)

	To generate and open the documentation:

		./gradlew javadoc

	Then open the following file in your browser:

		app/build/docs/javadoc/index.html

