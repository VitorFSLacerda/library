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

##  Generating Documentation (Javadoc)

	To generate and open the documentation:

		./gradlew javadoc

	Then open the following file in your browser:

		app/build/docs/javadoc/index.html

	https://documentationlibrary.netlify.app

---

## Implementation Notes and Design Logic

__Class Structure and Responsibilities:__

Library: Acts as the control layer, centralizing all business logic (borrowing, returning, registration). This design 
follows the Single Responsibility Principle, separating domain logic from the entities themselves (User, Book, Author).

User, Book, Author: These are simple entity classes representing the core objects of the system. Each class encapsulates 
its own behavior. I intentionally avoided assigning additional responsibilities to keep them cohesive and reusable.

exceptions package: I created custom exceptions to capture different types of runtime errors clearly and without relying on 
scattered if conditions. This avoids mixing flow control logic with error checking, promoting clean and expressive code.

Messages class: All user/system messages were centralized to avoid repetition and ease future maintenance.

---

__Error Handling:__

Instead of returning null or boolean, I chose to throw specific exceptions because:

It makes the code more readable and explicit: throw new BookNotAvailableException(...) clearly shows the failure reason.

It allows precise test coverage using assertThrows(...) in unit tests.

It facilitates friendly user messages and clean logging.

Defined exceptions:

- BookNotFoundException: Book title is not registered in the library.
- BookNotAvailableException: All copies of the book are currently borrowed.
- UserNotFoundException: User name is not registered.
- UserHasBookException: The user has already borrowed a book.
- UserHasNoBookException: The user has no borrowed book to return.

---

__Unit Testing with JUnit:__

I implemented automated tests using JUnit 5, covering realistic scenarios:

Author and book registration.

Borrowing with multiple available copies.

Invalid borrowing attempts (e.g., no available copies, unregistered user).

Returning books and verifying status updates.

JUnit was chosen for its seamless Gradle integration, its clean setup using @BeforeEach, and support for asserting expected exceptions.

---

__Javadoc Documentation:__

All source code has been documented using Javadoc in public classes and methods. The automatically generated documentation provides a 
structured overview of the system, including details about classes, attributes, methods, exceptions, and responsibilities.
The result is a navigable HTML interface that facilitates project understanding for future developers. This documentation was generated 
using Gradle and can be easily hosted for browser access.

---

__Gradle:__

I chose to use **Gradle** as the build system due to its flexibility, performance, and strong integration with the Java ecosystem. Gradle allows 
for automation of tasks such as compilation, test execution, documentation generation (Javadoc), and dependency management (JUnit for testing). Additionally, 
the use of Kotlin for configuration (`build.gradle.kts`) provides a safer and more modern syntax. This makes the project easier to maintain and more scalable in the long term.
