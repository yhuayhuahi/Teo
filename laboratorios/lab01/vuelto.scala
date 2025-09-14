def countChange(money: Int, coins: List[Int]): Int = {
  if (money == 0) 1                // caso base hay 1 forma de dar cambio
  else if (money < 0) 0            // si la cantidad es negativa
  else if (coins.isEmpty) 0        // si no hay monedas disponibles no se puede dar cambio
  else {
    countChange(money - coins.head, coins) + countChange(money, coins.tail)
  }
}

@main def runCountChange(): Unit = {
  println("Ejemplo 1: " + countChange(4, List(1, 2)))    
  // resultado esperado: 3

  println("Ejemplo 2: " + countChange(10, List(2, 5, 3, 6)))
  // resultado esperado: 5

  println("Ejemplo 3: " + countChange(0, List(1, 2, 5)))
  // resultado esperado:  1

  println("Ejemplo 4: " + countChange(7, List(2, 4)))
  // resultado esperado:0
}



