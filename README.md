# Stream

Esse projeto é a construção de uma API para um plataforma de stream, na qual, podemos ter uma simulação de venda de um serviço, que seriam um duo a partir do streamer...

Ou seja, basicamente uma pessoa pode criar uma stream, que tem um preço, e dessa forma, ele pode basicamente deixar essa stream amostra para que um outro usuário
possa pedir um duo com o usuário dessa stream, escolhendo o jogo, e assim a quantidade de horas que ele deseja jogar, calculando com o preço da stream, temos o preço do serviço de duo.

E dessa forma temos a venda de um serviço dessa plataforma de stream!

- Esta API foi construída durante o curso de Sistemas de Informação da Unitins, na matéria de: Tópicos de Programação.

---

## UML:

````plantuml
@startuml
class Usuario {
  +id: Long
  +nome: String
  +login: String
  +cpf: String
  +senha: String
  +perfil: Perfil
  +listaTelefone: List<Telefone>
  +streams: List<Stream>
}

class Stream {
  +id: Long
  +nome: String
  +nomeUsuario: Usuario
  +duos: List<Duo>
  +precoStream: Float
}

class Game {
  +id: Long
  +nome: String
  +categoria: String
  +nomeImagem: String
}

class Duo {
  +id: Long
  +quantidadeHoras: Integer
  +stream: Stream
  +solicitacao: Solicitacao
  +listaGame: List<Game>
  +calcularCustoTotal(): Float
}

class Solicitacao {
  +id: Long
  +dataHora: LocalDateTime
  +usuario: Usuario
  +duo: Duo
  +valorTotal: Float
  +status: StatusSolicitacao
  +pagamento: Pagamento
}

class Pagamento {
  +id: Long
  +valor: Float
  +confirmacaoPagamento: Boolean
  +tipoPagamento: TipoPagamento
  +dataConfirmacaoPagamento: LocalDate
  +solicitacao: Solicitacao
}

enum Perfil {
  ADMIN, USUARIO
}

enum StatusSolicitacao {
  AGUARDANDO, PAGO, REJEITADO
}

enum TipoPagamento {
  CARTAO_CREDITO, BOLETO
}

Usuario "1" --o "n" Stream
Stream "1" --o "n" Duo
Duo "1" --o "1" Solicitacao
Solicitacao "1" --o "1" Pagamento

@enduml

````

---

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```

---

