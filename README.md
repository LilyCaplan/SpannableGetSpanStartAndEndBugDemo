This app is meant to illustrate a bug we have found with `Spannable`. A user will type in text into an `EditText` assign styles to a span and then upon either entering a character at the very end of the span or backspacing the last span, the entire span will become all styles that have been applied whether or not they have been applied to those characters. If you notice the logs upon adding the character at the end or backspacing that the start and end points provided by `getSpanStart` and `getSpanEnd` return the entire length of the text though styles may have only been applied to a smaller portion of the text

Here are the following steps to reproduce:
1. type in a word in the `EditText` field (ex. HellWord)
2. highlight a portion of the word and press one of the style buttons at the top of the screen, very important that its's only a portion of the text and not the whole text typed in.
3. highlight the remaining characters that have not been styled and apply the style you have not used by clicking it's respective button
4. add a character at the end of the word you have styled or backspace at the end and observe the whole word becoming both styles


screen recording:
