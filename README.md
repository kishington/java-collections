# Character counter
Сhar-counter application that takes a string and returns the number of unique characters in the string.
A string with the same character sequence may be passed to the method several times.
Counting operation can be time-consuming. To address that, the method supports caching of previous results. Java Collections used in this application where appropriate.<br/>

Example output:
<pre>
hello world!
"h" - 1
"e" - 1
"l" - 3
"0" - 2
" " - 1
"w" - 1
"r" - 1
"d" - 1
"!" - 1
<pre/>
