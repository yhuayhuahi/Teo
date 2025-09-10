object Vuelto {
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 1                
    else if (money < 0) 0            
    else if (coins.isEmpty) 0        
    else {
      0 // valor temporal para que compile
    }
  }

  def testFunction() {
    println("Esta es una funcion")
  }

  def main(args: Array[string]) {
    testFunction()
  }
}


