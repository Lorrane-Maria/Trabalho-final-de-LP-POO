<p align="center">
  <img src="" />
</p>

<h1 align="center">
  <b>Sonhaverso</b>
</h1>

## 🦾 DESAFIO 
**Proposta para o trabalho final das disciplinas de Linguagens de Programação e Programação Orientada a Objetos - 3º semestre do curso de Tecnologia em Análise e Desenvolvimento de Sistemas:** Construir um SisVendas - Sistema de vendas de produtos (E-commerce).

## ⚛️ PROJETO  

O **Sonhaverso** é uma **livraria**, com objetivos de:
- Inspirar a imaginação;
- Incentivar o conhecimento;
- Oferecer variedade no catálago.


## 🛠️ TECNOLOGIAS 
- **Java 17:** Linguagem de programação utilizada.
- **IntelliJ IDEA:** IDE utilizada para o desenvolvimento.
- **SQLite:** Banco de dados utilizado para armazenar as informações do sistema.

- **Bibliotecas principais:**
- javax.swing
- java.sql
- java.util
- java.awt

## 📐 ARQUITETURA  
O aplicativo foi construído utilizando uma arquitetura componentizada conforme estrutura abaixo:
- _src_
  - _Livraria_ (pacote principal)
    - _Controller_ (gerencia a regra de negócio e a comunicação entre a view e o model)
    - _Model_ (define as classes que representam os dados da aplicação e a lógica de negócio.)
    - _Util_ (contém utilitários, como a conexão com o banco de dados.)
    - _View_ (responsável pelas interfaces gráficas com o usuário)

## 📋 RECURSOS  
- **Cadastro de Usuário**
  - Permite a criação de novos usuários com dados pessoais e de endereço.
- **Login de Usuário:** 
  - Autentica usuários cadastrados.
- **Vitrine de Livros**
  - Exibe os livros disponíveis com informações como nome, preço, autor e quantidade em estoque.
- **Carrinho de Compras**
  - Adiciona livros ao carrinho e finaliza o pedido.
- **Pagamento**
  - Escolha de forma de pagamento entre cartão de crédito, débito ou PIX.
- **Gerenciamento de Estoque**
  - Adiciona produtos ao estoque e verifica disponibilidade.

## 🖼️ TELAS  
- **Login:** 

  <img src="" width="200" height="400">

- **Cadastro:** 

  <img src="" width="200" height="400">

- **Vitrine:** 

<img src=""  width="200" height="400">

- **Carrinho:** 
  
<img src=""  width="200" height="400">

- **Pagamento:** 

<img src=""  width="200" height="400">


## 🚀 EXECUTANDO O PROJETO  
Para rodar o projeto é necessário seguir os passos abaixo:

`1. Realize um clone desse repositório na sua máquina local, garantindo que ele seja movido para um diretório próprio.`

    git clone https://github.com/Lorrane-Maria/Trabalho-final-de-LP-POO.git

`2. Abrir o projeto no IntelliJ.`

`3. Abra o arquivo MainView e execute.`

    src/Livraria/View/MainView.java

`4. Ao abrir a tela de login selecione o botão cadastrar.`

`5. Na tela de cadastro preencha todos os campos e conclua o cadastro no novo usuário.`

`6. Ao retornar para tela de login acesse com email e senha cadastrada.`

`7. Ao efetuar o login, será redirecionado a tela de vitrine onde podera ver os produtos cadastrados.`

`8. Selecione um livro, poderá escolher a quantidade de livro desejados limitando ao máximo existente no estoque.`

`9. Ao clicar no botão "Ver Carrinho" é feito o redirecionamento para a tela carrinho de compras, onde podera finalizar ou desistir da compra.`

`10. Selecionando desistir, volta para tela de vitrine e selecionando finalizar compra é redirecionado a tela de pagamento.`

`11. Na tela de pagamento, selecione o método de pagamento de desejado e ao clicar em pagar é concluido o fluxo.`
    

## 🧑‍💻 DESENVOLVEDORES  
Lorrane Maria ([Linkedin](https://www.linkedin.com/in/lorrane-maria-5396b021b/)) / Contato: lorranemaria57@gmail.com

Matheus Felipe 
