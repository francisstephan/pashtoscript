package pashto

import atom._

// https://github.com/martintrojer/atom-scala

object Translator{
  val latPax = Map("&" -> "\u0621", // hamza
                    "a" -> "\u0623", // a hamza
                     "i" -> "\u0626", // i hamza
                    "u" -> "\u0624", // u hamza
                    "e" -> "\u06c0", // e yé domboridé
                    "'" -> "\u0627", // alif
                    "b" -> "\u0628", // ba
                    "p" -> "\u067E", // pa
                    "t" -> "\u062A", // ta
                    "j" -> "\u062c", // jim
                    "c" -> "\u0686", // chim
                    "H" -> "\u062d", // Ha
                    "x" -> "\u062e", // xa
                    "d" -> "\u062f", // dal
                    "r" -> "\u0631", // re
                    "z" -> "\u0632", // ze
                    "s" -> "\u0633", // sin
                    "S" -> "\u0635", // Sad
                    "D" -> "\u0636", // Zad
                    "T" -> "\u0637", // Ta
                    "Z" -> "\u0638", // Za
                    "A" -> "\u0639", // Ain
                    "f"  -> "\u0641", // fa
                    "q" -> "\u0642", // qaf
                    "k" -> "\u06A9", // kaf
                    "g" -> "\u06AB", // gaf
                    "l" -> "\u0644", // lam
                    "m" -> "\u0645", // mim
                    "n" -> "\u0646", // nun
                    "w" -> "\u0648", // vav
                    "h" -> "\u0647", // ha
                    "y" -> "\u064a", // ya
                    "?" -> "\u061F",
                    "," -> "\u060C",
                    "." -> ".",
                    "$" -> "\u0651", // shadda
                     "_" -> "", // filter "_"
                    "0" -> "\u06F0", "1" -> "\u06F1", "2" -> "\u06F2","3" -> "\u06F3", "4" -> "\u06F4", // numbers should remain at
                    "5" -> "\u06F5", "6" -> "\u06F6", "7" -> "\u06F7","8" -> "\u06F8", "9" -> "\u06F9" ) // the end of the map


  val latPax_ = Map("a" -> "\u0622","e" -> "\u0629", "i" -> "إ", "t" -> "\u062b", "d" -> "\u0630", "r" ->  "\u0698" , "s" -> "\u0634", "g" -> "\u063a", "y" -> "\u0649", "j" -> "ژ", "T" -> "\u067C",
     "z" -> "\u0681","c" -> "\u0685","D" -> "\u0689" ,"r" -> "\u0693" , "S" -> "\u069A", "E" -> "\u06D0",
     "Y" -> "\u06CD" , "R" -> "\u0696", "N" -> "\u06BC" ,"_" -> "" ) // filter "_"

  val lastcar = Atom("")

  def subst(car: String, carte: Map[String, String]) = carte.getOrElse(car,car)

  def subst_(car: String) = {
    val prec = lastcar.get();
    lastcar.reset(car)
    if(prec == "_") subst(car, latPax_) else subst(car, latPax)
  }
  def tos(car: Char):String = subst_(car.toString())
  def transl(s: String): String = {
    lastcar.reset("")
    s.map(tos).foldLeft("")(_+_)
  }

}
