# library

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
