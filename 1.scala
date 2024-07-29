object Inventories extends App {
  val inventory1 = Map(
    001 -> ("Product_1", 50, 1500.0),
    002 -> ("Product_2", 200, 300.0),
    003 -> ("Product_3", 30, 750.0)
  )

  val inventory2 = Map(
    002 -> ("Product_2", 50, 250.0),
    004 -> ("Product_4", 100, 450.0)
  )

  val productNames = inventory1.values.map(_._1)
  println(s"Product Names: $productNames")

  val totalValue = inventory1.values.map { case (_, quantity, price) => quantity * price }.sum
  println(s"Total Value: $totalValue")

  val isEmpty = inventory1.isEmpty
  println(s"Is inventory1 empty? $isEmpty")

  val mergedInventory = (inventory1.toSeq ++ inventory2.toSeq)
    .groupBy(_._1)
    .map { case (id, products) =>
      val name = products.head._2._1
      val totalQuantity = products.map(_._2._2).sum
      val maxPrice = products.map(_._2._3).max
      id -> (name, totalQuantity, maxPrice)
    }

  println(s"Merged Inventory: $mergedInventory")

  val productIdToCheck = 008
  inventory1.get(productIdToCheck) match {
    case Some((name, quantity, price)) =>
      println(s"Product ID: $productIdToCheck, Name: $name, Quantity: $quantity, Price: $price")
    case None =>
      println(s"Product with ID $productIdToCheck does not exist in inventory1")
  }
}
