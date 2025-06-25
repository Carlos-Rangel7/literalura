# 📚 LiterAlura

Projeto desenvolvido como desafio da Alura, com o objetivo de consumir a API pública do [Gutendex](https://gutendex.com/) e montar um catálogo de livros, autores e informações relacionadas.

---

## 🛠 Tecnologias Utilizadas

- Java 21
- Spring Boot
- JPA / Hibernate
- PostgreSQL
- Jackson (JSON to Object)
- Gutendex API (https://gutendex.com/)
- Maven

---

## 🎯 Funcionalidades

A aplicação é executada em terminal/console e apresenta ao usuário um menu com as seguintes opções:

### 1️⃣ Buscar livro pelo título
Consulta a API Gutendex com base no título informado, retorna o primeiro resultado encontrado e o armazena no banco de dados junto com seu autor.




### 2️⃣ Listar livros registrados
Exibe todos os livros salvos no banco de dados, mostrando título, idioma, número de downloads e autor principal.

### 3️⃣ Listar autores registrados
Exibe todos os autores cadastrados, com nome, ano de nascimento, ano de falecimento (ou se está vivo) e os livros associados a ele.

### 4️⃣ Listar autores vivos em um determinado ano
Permite inserir um ano e lista todos os autores que estavam vivos naquele período, com base nos dados de nascimento e falecimento.

### 5️⃣ Listar livros em um determinado idioma
Permite escolher um idioma (ex: `pt`, `en`, `es`, `fr`) e exibe todos os livros cadastrados que foram escritos nesse idioma.

### 6️⃣ Buscar top livros mais baixados
Faz uma consulta direta à API Gutendex para exibir os livros com maior número de downloads, sem salvar no banco. O usuário pode escolher quantos livros deseja ver (até 10).

---

## 📸 Demonstração

<!-- Substitua abaixo pelo link da imagem após adicionar ao seu repositório -->
![Demonstração do projeto](./assets/demo.png)

---

## 🚀 Como executar o projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/LiterAlura.git
