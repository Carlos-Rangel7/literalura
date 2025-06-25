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

![Image](https://github.com/user-attachments/assets/9183ebbe-aa72-4665-88a0-4576ae9aa37b)



### 2️⃣ Listar livros registrados
Exibe todos os livros salvos no banco de dados, mostrando título, idioma, número de downloads e autor principal.

![Image](https://github.com/user-attachments/assets/482815b5-5bdb-40b3-8a5b-21192eb20d6e)

### 3️⃣ Listar autores registrados
Exibe todos os autores cadastrados, com nome, ano de nascimento, ano de falecimento (ou se está vivo) e os livros associados a ele.

![Image](https://github.com/user-attachments/assets/2a06f7dc-4cd4-4e22-8826-13521db9634e)

### 4️⃣ Listar autores vivos em um determinado ano
Permite inserir um ano e lista todos os autores que estavam vivos naquele período, com base nos dados de nascimento e falecimento.

![Image](https://github.com/user-attachments/assets/7e11acce-8af8-4430-b507-3d9e18831e33) ![Image](https://github.com/user-attachments/assets/e3bdf3b9-ea4d-4558-9cce-c8143e1848d4)

### 5️⃣ Listar livros em um determinado idioma
Permite escolher um idioma (ex: `pt`, `en`, `es`, `fr`) e exibe todos os livros cadastrados que foram escritos nesse idioma.

![Image](https://github.com/user-attachments/assets/2e86ff1e-6a40-47c4-b000-e2791ab2f4ae)  ![Image](https://github.com/user-attachments/assets/5a90ad9c-59e3-45a4-ac3e-09f34943384d)


### 6️⃣ Buscar top livros mais baixados
Faz uma consulta direta à API Gutendex para exibir os livros com maior número de downloads, sem salvar no banco. O usuário pode escolher quantos livros deseja ver (até 10).

![Image](https://github.com/user-attachments/assets/dea22ec1-4ebc-4413-a643-e3d3e9f3b651)
