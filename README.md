# pashtoscript

An easy way to use your latin keyboard to type in pashto script.

See for instance https://en.wikipedia.org/wiki/Pashto#Writing_system

Developed with Scalajs and scalajs-react

## Usage

Refer to the tables of correspondence to type the desired chars.

After a time this will become quite natural, and you will no longer need to refer to the tables.

Use live at http://www.eludev.fr/pashto

## Comment

A scalajs, scalajs-react port from clojurescript, reagent (see https://github.com/francisstephan/farsiscript)

## References

Uses scalajs-react from https://github.com/japgolly/scalajs-react/blob/master/doc/VDOM.md

No use for state in CharTrans any longer : to be able to consult previous character while parsing a string, just zip the string with a right shift of itself.

## License

MIT
