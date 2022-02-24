<h1 align="center"> Aplicação To-Do List</h1>

## Sobre
Crud simples de agendamento de tarefas por usuário.  
Para obter acesso a documentação da aplicação ( [clique aqui](https://drive.google.com/file/d/175e_5dCeYXBcHziy7lJD1EOY25Kd1ebE/view?usp=sharing) );  
Para visualizar a aplicação em deploy no Heroku [clique aqui](https://todolistfgh.herokuapp.com/swagger-ui.html) ;  

```bash
login:user/senha:123
```  

**Funcionalidades:**  
**User**  
• Cadastro de usuário  
• Um cadastro por email  
• Alteração de cadastro  
• Deleção de usuário  
• Filtro por nome  

**Task**  
• Cadastro de tarefa  
• Cadastro de nova tarefa   
• Alteração de um título ou descrição da tarefa  
• Alteração da prioridade ou status     
• Filtro por prioridade  
• Filtro de tarefas a fazer   
• Data de criação   
• Data de atualização de algum atributo  
• Deleção de tarefa


**Projeto estruturado em 2 tabelas utilizando o H2 Database;**

- Atributos da tabela User :

_@OneToMany_  

• ID [int]  
• Name [string]  
• Lastname [string]  
• Email [string]  
• Password[string]

- A estrutura da tabela Tasks :
  
_@ManyToOne_  

• ID [int]  
• Title [string]  
• Description [string]  
• Deadline [string]  
• Status [ENUM] **(OPEN/TODO/DONE)**  
• Priority [ENUM] **(GREEN/YELLOW/RED)**  
• createdAt[string]    
• updatedAt[string]

## Implementações futuras / bugs

- Jwt Token.
- Bloqueio de edição por originador da task.
- Testes unitários (Junit).
- corrigir id_user na tabela tasks com retorno "null" no insomnia, porém funcionando no swagger.  
- Query de busca por status não funciona

## Como rodar a aplicação :

- Clone o repositório:

```bash

$git clone https://github.com/felipe0059/ToDo-List

```
- Importe o projeto para sua IDE principal. (recomendo o  [Intellij](https://www.jetbrains.com/idea/download/#section=windows));  
- Aguarde o download das dependências do projeto;  
- Siga o caminho /src/main/java/br.com.desafioviceri e selecione a classe "TodolistApplication";  
- Clique com o botão direito e selecione "Run" . (Ou abra a classe e pressione CTRL+SHIFT+F10) ;
- Após a compilação abra o navegador e em login digite "user" e senha "123";
- Você terá acesso a tela do Swagger;
- Para ter acesso as tabelas mude o link para : http://localhost:8080/h2-console  


**- Métodos de teste no Postman/Insomnia:**  

**Todas as requisições necessitam de autorização** Basic (usuario user | password 123) !

Cadastro de usuario (Um Cadastro por email) : 

```bash
CADASTRAR - http://localhost:8080/users/register - POST
ALTERAR/DELETAR - http://localhost:8080/users/{id} - PUT/DELETE
BUSCA POR NOME - http://localhost:8080/users/search/{name} - GET
BUSCA LOGIN NO BANCO - http://localhost:8080/users/login - PUT

{
  "name": "User",
  "lastname": "user",
  "email": "user@email.com",
  "password": "123456"
}
```  
Cadastro de uma tarefa :

```bash
CADASTRAR - http://localhost:8080/tasks - POST
TODAS TASKS - http://localhost:8080/tasks/all - GET
EDITAR TITULO/DESCRIÇÃO | DELETAR - http://localhost:8080/tasks/{id} - PUT/DELETAR  
FILTRO POR PRIORIDADE - http://localhost:8080/tasks/priority/{priority} - letras maiusculas
FILTRO POR STATUS -  http://localhost:8080/tasks/status - GET
{	
	"title" : "Task ",
	"description": "task 1",
	"deadLine": "2022-30-09",
	"priority": "GREEN",
	"status":"OPEN"
}

ALTERAR STATUS - http://localhost:8080/tasks/status/2

{	
	"status":"OPEN"
}
```
