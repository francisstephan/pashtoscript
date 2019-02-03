package pashto

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.document
import org.scalajs.dom.html
import scala.scalajs.js

object JavascriptFun {
  val doc = js.Dynamic.global.document
  def copyTextToClipboard(text:String):Unit = {
    // adapted from https://stackoverflow.com/questions/400212/how-do-i-copy-to-the-clipboard-in-javascript
    val textArea = doc.createElement("textarea");

    textArea.style.position = "fixed"; textArea.style.top = 0; textArea.style.left = 0;
    textArea.style.width = "2em"; textArea.style.height = "2em"; textArea.style.padding = 0;
    textArea.style.border = "none"; textArea.style.outline = "none"; textArea.style.boxShadow = "none";
    textArea.style.background = "transparent";
    textArea.value = text;

    doc.body.appendChild(textArea);
    textArea.select();
    try {
      val successful = doc.execCommand("copy")
    } catch  {
      case e: Exception => js.Dynamic.global.console.log("Oops, unable to copy")
    }
    doc.body.removeChild(textArea);
  }
  def focus(){
    doc.getElementById("entrée").focus()
  }
}

object PashtoApp {
  val Entrée = ScalaComponent.builder[Unit]("Entrée")

  case class State(entrée: String)

  class Backend($: BackendScope[Unit, State]) {
    def onChange(e: ReactEventFromInput) = {
      val newValue = e.target.value
      $.modState(_.copy(entrée = newValue))
    }

   def callClipboard(e: ReactEventFromInput) : Callback = {
     val txt = document.getElementById("convert").textContent
     JavascriptFun.copyTextToClipboard(txt)
     // Necessary to make the compiler happy and to prevent state reset :
     e.preventDefaultCB >>
     $.modState(s => State(s.entrée))
   }

   def reset(e: ReactEventFromInput) = {
     document.getElementById("entrée").asInstanceOf[html.Input].value = ""
     JavascriptFun.focus()
     e.preventDefaultCB >>
     $.modState(s => State(""))
   }

    def render(state: State) =
      <.div(
        <.p("Type on latin keyboard: \n",
          <.input(^.`type`:="button", ^.cls := "rebut", ^.value := "Copy Pashto to clipboard", ^.onClick ==>  callClipboard),
          <.input(^.`type`:="button", ^.cls := "rebut", ^.value := "Reset", ^.onClick==>reset)
        ),
        <.input(^.id := "entrée", ^.autoFocus:= true, ^.onChange ==> onChange),
        <.p("Get pashto text : "),
        <.p(^.id := "convert", Translator.transl(state.entrée))
      )
  }

  val pashtoApp = ScalaComponent.builder[Unit]("PashtoApp")
    .initialState(State(""))
    .renderBackend[Backend]
    .build

  def main(args: Array[String]): Unit = {
    pashtoApp().renderIntoDOM(document.getElementById("app"))
  }
}
