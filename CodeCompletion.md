# Introduction #

Code completion suggestions will be context sensitive.


# Details #

Code completion within the editor will be context sensitive.  For instance, if a user is entering function or procedure parameters, only variables which would be within scope and match the parameter type will be suggested.  Suggestions will be ordered by scope, then alphabetically, with the closest scoped variables being suggested first.

```
atom fn
sequence message = "Hello World!"
fn = open("myfile.txt", "w")
puts(

```

At the open parenthesis following puts, only fn would be suggested.  After the user enters the first parameter and a comma, message would be the code completion tip.