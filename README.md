# 🎵 Sonora — Sistema de Gerenciamento de Músicas  
**Desenvolvido em Spring Boot + Thymeleaf + MySQL com Testes TDD (JUnit)**

O **Sonora** é um sistema web simples para gerenciamento de músicas, permitindo cadastrar, listar, atualizar, favoritar e excluir músicas.  
O projeto também inclui **uma suíte de testes criada seguindo TDD**, validando as principais operações do DAO.

---

## **Equipe**
- **Anna Quésia dos Santos**  
- **Laura Rodrigues de Souza de Camargo**  
- **Otávio Augusto Fermino da Silva**

---

## **Tecnologias Utilizadas**

### Backend  
- **Java 21**  
- **Spring Boot 3.4**  
- **Spring MVC**  
- **JDBC + MySQL**  
- **DAO Pattern**

### Frontend  
- **Thymeleaf**  
- **Bootstrap 5.3**  
- **HTML5 + CSS3**

### Testes  
- **JUnit 5**  
- **TDD (Test Driven Development)** aplicado ao DAO  
- **Testes unitários** para:
  - Criar música  
  - Ler músicas  
  - Atualizar música  
  - Favoritar/desfavoritar  
  - Deletar  
  - Filtrar favoritas  

---

## Estrutura do Projeto

```
src
├── main
│   ├── java
│   │   ├── DAO
│   │   │    └── MusicDAO.java
│   │   ├── dataBaseConection
│   │   │    └── ConnectionFactory.java
│   │   ├── model
│   │   │    └── Music.java
│   │   └── sonoraweb
│   │        ├── SonoraWebApplication.java
│   │        └── controller
│   │             └── MusicController.java
│   └── resources
│        ├── static
│        │     ├── css
│        │     │     └── styles.css
│        │     └── images
│        └── templates
│              ├── index.html
│              └── edit.html
└── test
└── java
└── DAO
└── MusicDaoTest.java
```

---


## **Como executar o projeto**

### **1. Clonar o repositório**
```bash
git clone https://github.com/SEU_USUARIO/Sonora.git
cd Sonora
```

### **2. Configurar o banco MySQL**
```bash
CREATE DATABASE sonora;
USE sonora;

CREATE TABLE music (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255),
  artist VARCHAR(255),
  album VARCHAR(255),
  duration DOUBLE,
  favorite BOOLEAN
);
```

### **3. Rodar com MavenL**
```bash
mvn spring-boot:run
```

---
