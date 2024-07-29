import scala.io.StdIn._

object StudentRecords extends App {
  def getStudentInfo(): (String, Int, Int, Double, Char) = {
    def calculatePercentage(marks: Int, totalMarks: Int): Double = (marks.toDouble / totalMarks) * 100

    def assignGrade(percentage: Double): Char = {
      if (percentage >= 90) 'A'
      else if (percentage >= 75) 'B'
      else if (percentage >= 50) 'C'
      else 'D'
    }

    println("Enter student's name:")
    val name = readLine()

    println("Enter student's marks:")
    val marks = readInt()

    println("Enter total possible marks:")
    val totalMarks = readInt()

    val percentage = calculatePercentage(marks, totalMarks)
    val grade = assignGrade(percentage)

    (name, marks, totalMarks, percentage, grade)
  }

  def printStudentRecord(student: (String, Int, Int, Double, Char)): Unit = {
    val (name, marks, totalMarks, percentage, grade) = student
    println(s"Name: $name")
    println(s"Marks: $marks / $totalMarks")
    println(s"Percentage: $percentage%")
    println(s"Grade: $grade")
  }

  def validateInput(name: String, marks: Int, totalMarks: Int): (Boolean, Option[String]) = {
    if (name.isEmpty) {
      (false, Some("Name cannot be empty."))
    } else if (marks < 0 || totalMarks < 0) {
      (false, Some("Marks and total marks must be positive."))
    } else if (marks > totalMarks) {
      (false, Some("Marks cannot exceed total possible marks."))
    } else {
      (true, None)
    }
  }

  def getStudentInfoWithRetry(): (String, Int, Int, Double, Char) = {
    var isValid = false
    var errorMessage: Option[String] = None
    var name = ""
    var marks = 0
    var totalMarks = 0

    while (!isValid) {
      println("Enter student's name:")
      name = readLine()

      println("Enter student's marks:")
      marks = readInt()

      println("Enter total possible marks:")
      totalMarks = readInt()

      val validationResult = validateInput(name, marks, totalMarks)
      isValid = validationResult._1
      errorMessage = validationResult._2

      if (!isValid) {
        println(s"Error: ${errorMessage.get}")
      }
    }

    val percentage = (marks.toDouble / totalMarks) * 100
    val grade = if (percentage >= 90) 'A'
                else if (percentage >= 75) 'B'
                else if (percentage >= 50) 'C'
                else 'D'

    (name, marks, totalMarks, percentage, grade)
  }

  val student = getStudentInfoWithRetry()
  printStudentRecord(student)
}
