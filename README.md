# Attornatus-Teste de integridade Attornatus 
Este projeto foi criado para a participação no processo seletivo da Empresa Attornatus Procuradoria Digital. O desafio consistia em desenvolver uma aplicação API Rest para gerenciar Pessoas. Esta API deveria conter um CRUD e o relacionamento entre duas tabelas (Pessoa e Endereço), onde a entidade pessoa poderia receber mais de um endereço.  

# Estrutura do projeto

O projeto está dividido em uma estrutura MVC.
O projeto foi construído com Spring Boot 4 na linguagem Java. 
Existe também uma camada de banco de dados desenvolvida com H2 (banco em mamória) para persistência de dados  das entidades  durante a execução da aplicação.

OBS:Foram criados getters e setters  das duas entidades, contudo usando a biblioteca Lombok e a anotação @Data não seria necessário, porém como não havia instrução se poderia ou não usar o lombok resolvi não utilizá-lo. 

# Endpoints da API

Entidade Pessoa

@RequestMapping("pessoas") localhost:8080/pessoas usado para listar todas as pessoas cadastradas e seus endereços.

@GetMapping("/pessoa/{idPessoa}") e @GetMapping("/pessoaNome/{nome}") localhost:8080/pessoas/pessoaNome/{nome ou idPessoa} usado para consultar uma pessoa pelo sua chave prmária(id) ou por nome, lembrando que o endepoint por nome só funcionará se não houver nomes iguais, para busca pecisa recomendo a busca por id. 

@PostMapping("/pessoa") localhost:8080/pessoas/pessoa usado para criar uma entidade pessoa.

@PutMapping("/pessoa/{idPessoa}") localhost:8080/pessoas/pessoa/{idPessoa} usado para editar uma pessoa já cadastrada.

@DeleteMapping("/delPessoaId/{idPessoa}") e @DeleteMapping("/delPessoa/{nome}") localhost:8080/pessoas/delPessoa/{nome ou idPessoa} usado para deletar uma pessoa pelo sua chave prmária(id) ou por nome, lembrando que o endepoint por nome só funcionará se não houver nomes iguais.

Entidade Endereço

@RequestMapping("enderecos") localhost:8080/enderecos usado para listar todos os endereços cadastrados.

@GetMapping("/endereco/{idEndereco}") e @GetMapping("/enderecoPessoa/{nome}") usado para consultar uma pessoa pelo sua chave prmária(id) ou por nome, lembrando que o endepoint por nome só funcionará se não houver nomes iguais, para busca pecisa recomendo a busca por id. 

@PostMapping("/endereco/{nome}") localhost:8080/enderecos/endereco/{nome} usado para criar um novo endereço, precisa passar por parametro o nome da entidade Pessoa.

@PostMapping("/atribuirEndereco/{idPessoa}/{idEndereco}") localhost:8080/enderecos/atribuirEndereco/1/4 usado para atribuir um novo endereço, precisa passar por parametro o id da entidade pessoa e depois o id do endereço.

@PutMapping("/endereco/{idEndereco}/{idPessoa}") localhost:8080/enderecos/endereco/{idEndereco}/{idPessoa} usado para edtar um endereço, precisa passar por parametro o id da entidade endereço e depois o id da pessoa.

@DeleteMapping("/endereco/{idEndereco}") localhost:8080/enderecos/endereco/{idEndereco} usado para excluir um endereço pela chave primária.

## Dependências utilizadas

Para a aplicação em Spring foram usadas, principalmente, as dependências do mavem e do próprio framework spring.

Para a camada de banco de dados foi utilizada a seguinte dependência: H2 banco em memória.

## Execução da aplicação

Neccessáio baixar o código do projeto,fork, abri-lo em alguma IDE, recomendo o eclipse, subir o servido e acessá-lo no localhost:8080/h2.
Recomendo inserir alguns dados com o Postman ou no próprio h2.

## Testes
Testes foram realizados através da ferramenta Postman, onde foi possivel tester todos os endpoints.

## Conhecimentos adquiridos e dificuldades encontradas

* Rotina de CRUD.
* Criar uma interface assíncrona para comunicação  com o H2, usando o relacionamento de muitos para um entre as duas entidades, usando chave estrangeira.

###### Considerações finais

Agradeço a oportunidade da participação no processo seletivo!!


