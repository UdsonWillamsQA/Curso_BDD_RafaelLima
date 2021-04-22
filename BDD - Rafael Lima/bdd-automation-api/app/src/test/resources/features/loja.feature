# language: pt
  Funcionalidade: Gerenciamento de pedidos

    @DeleteExtraPets
    Cenario: Cliente cria um pedido na loja
      Dado que eu possua animal available
      Quando faço o pedido desse animal
      Entao o pedido é aprovado
