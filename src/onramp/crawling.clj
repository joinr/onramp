;;A simple exposition of how to express things in clojure.
(ns onramp.crawling
  (:use clojure.repl))

;;We'll approach clojure from the view of linguistics, rather than other
;;approaches. While there will be plenty of discourses on mathematical topics, I
;;find it easier to view clojure as a language, and to explore how we - as
;;humans - can express things in this language.

;;I think you'll be pleased to find that Clojure provides an interesting balance
;;of depth and power: It takes minimal effort to become "dangerous", or fluent
;;in expressing complicated topics in clojure, but the outward simplicity belies
;;the expressive depth clojure provides. In other words, clojure grows with
;;you....baby steps are easy and undemanding, but you - like many others - may
;;be surprised at the expressive power that such a relatively "simple" language
;;provides.


;;Primitive Expressions
;;=====================

;;Clojure is a language, just like English or Spanish, or Chinese, or Klingon.
;;The only difference between aforementioned languages and languages like
;;clojure is the intent: clojure is intended to serve primarily as a medium for
;;expressing computations to a computer, in a human-readable format.

;;Due to some unique characteristics in clojure's pedigree, and the conscience
;;design choices of the core development team, the language serves for more
;;purposes than "merely" describing computations.

;;Or, from another angle, "describing computations" in clojure carries far more
;;utility than in other languages aimed at the same purpose.

;;The REPL - your conversational partner
;;======================================

;;When we work "in" clojure we're typically working "in" the Read Evaluate Print
;;Loop (REPL), or writing chunks of text that are ultimately destined for the
;;REPL.

;;I find it helpful to think of the REPL as an
;;active listener.  It is simultaneously:
;;-a partner that can read and write clojure as its native tongue
;;-waits for you to say something
;;-always responds to what you say
;;-and waits for the next piece of the conversation.

;;In other words, the REPL continually
;;1. =R=eads input (typically from you, the user),
;;2. =E=valuates the input,
;;3. =P=rints output,
;;4. =L=oops back to 1., that is read the next input

;;The REPL is your gateway to expressing computations and other things in
;;clojure. Once you get used to conversing with it, you will find the REPL is an
;;invaluable tool, and that it lends a "liveliness" to the experience, so
;;that "programming" feels more like conversing.

;;The REPL Comprehends Clojure
;;============================

;;How then, do we converse with our partner?
;;Assuming you have an active REPL, you will see
;;a prompt, akin to:
;;user>
;;This prompt is the REPL "waiting" for you to tell it
;;something.

;;When we communicate with the repl, we enter in some characters via the
;;keyboard, and then (typically) hit the "enter" key.

;;Note: there are other ways to provide input to the REPL; we can
;;leverage copy/paste functionality followed by "enter", and some
;;integrated development environments simplify "sending" input
;;to the REPL for evaluation and report the result.

;;Let's keep it simple for now, evaluate the following expression by
;;typing or pasting it into the repl, followed by the "enter" key:

"hello REPL"

;;should yield:

;;"hello REPL"
;;user>

;;You sent some input for the REPL to Read and Evaluate, it Printed the result,
;;then continued Reading input by presenting you with the prompt.

;;It turns out the input you sent "hello REPL" was something the REPL
;;comprehended, and it merely printed the input back at you. Let's delve into
;;that...

;;Primitive Expressions
;;=====================

;;Like any language, clojure has the notion of "syntax", that
;;is rules that govern how sentences are built, what valid
;;sentences look like, etc.

;;In English, we have letters, that is A-Z,a-z, as well as punctuation, like the
;;humble period . , and numbers 0-9. There is also the notion of "whitespace",
;;that is symbols that denote the separation of words.

;;An English sentence may look like:
;;- This is a simple sentence.

;;When you read the preceding sentence, your brain automatically applied the
;;English syntax to figure out that:
;;1. There is a sentence, because we have a period.
;;2. Since sentences are composed of words
;;   - There are words in the stuff before the period.
;;   - Words are separated by spaces, so we think there are
;;     5 words.

;;Your brain is acting just like a REPL, but for the English language. It Reads
;;the input, Evaluates the sentence, teasing out the structure and meaning, and
;;does something with the result. If you spoke the sentence out loud, we could
;;view that as analogous to Printing the result.

;;We can think of letters as the most primitive form of communication in
;;English. That is, when our brain-REPL tears apart a sentence, it never tries
;;to tear apart letters (or numbers). In fact, we leave them alone.....in other
;;words,

;;- Evaluating a primitive returns the primitive (there's nothing to do).
;;- Evaluating a sentence may return less-primitive things, like a collection of
;;  words, or if our rules are sophisticated, a subject and a predicate, phrases,
;;  etc.

;;Clojure Primitives
;;==================

;;If primitives evaluate to themselves, then they form a building block for the
;;language. We can view the language as a system of rules used to project
;;meaning onto combinations of primitive constructs. Primitives, then, may be
;;seen as a form of data. The language defines how data may be arranged (syntax)
;;and interpreted to have meaning (semantics).

;;Clojure has several primitive types that, when entered into the REPL, evaluate
;;to themselves. We treat these as data. More importantly, since they are
;;primitive, neither we (nor Clojure) requires any additional information to
;;explain what they mean or how to evaluate them.

;;Characters
;;==========

;;Characters are like individual letters or numbers in English, with the
;;addition of a leading back-slash to indicate the data is a character and not a
;;symbol.

\a ;the character "a"
\b ;the character "b"

;;Strings
;;=======

;;Strings are collections of characters, indicated by surrounding one or more
;;characters with double-quotes:

"We have already seen strings."

;;We'll dissect the following line in the next section, but for now, evaluate it
;;to see what happens:

(seq "I contain characters")

;;Should yield
;(\I \space \c \o \n \t \a \i \n \space \c \h \a \r \a \c \t \e \r \s)

;;To foreshadow, we told the REPL to evaluate an expression, one which applied
;;'seq' to the string, and the REPL dutifully printed the result. Mentally
;;bookmark that example as we move on.

;;Numbers
;;=======

;;Numbers are fairly consistent with our idea of what numbers should look like.
;;Clojure comes from a long line of mathematically sophisticated languages, so
;;the REPL understands many kinds and formats of numbers, and knows how to
;;handle them gracefully. We will explore common numbers.

;;Integers
;;========

1
2
3
100000000000

;;Floats
;;======

;;Floating numbers are denoted by the decimal point representation:
1000.2
42.42

;;Ratios
;;======

;;Clojure provides facilities for dealing with ratios explicitly. Unless
;;otherwise instructed, division will always produce a ratio if even division
;;cannot be accomplished. If we want to, we express ratios directly as a
;;forward-slash, flanked by integers:

42/5


;;Booleans
;;========

;;Clojure understands primitive notions for true and false, also known as
;;Boolean values - named after George Boole.

true
false

;;nil
;;===

;;The symbol nil has some unique properties, one of which is primitive
;;evaluation.

nil

;;yields nil.

;;=nil= is equivalent to false with respect to logical truth, however, it has
;;other uses. =nil= is a common return result from the REPL, and is typically
;;used to indicate emptiness or falseness.

;;Exotic Primitives
;;===========

;;Clojure provides many other constructs that have primitive evaluation rules.
;;Here are two of the most important....


;;Keywords
;;========

;;Keywords are a common idiom in clojure expressions, and are formed by
;;prefixing a colon, : , onto a - any combination of characters

:a
:b
:this-is-a-keyword
:THISisALSOaKEYWORD

;;Quoted Symbols
;;==============

;;Any input prefaced by the single-quote, ', is treated as primitive data. That
;;is, the REPL evaluates it to be identical to the input. Much like in English,
;;this process is called "quoting".

;;We're literally "quoting the source", that is telling the REPL to treat the
;;quotation exactly as it came:

'2
'"three"

;;Symbols are unadorned collections of characters that
;; - may not be begin with a number
;;Quoted symbols return data like anything else...

'x
'this-will-return-a-symbol

;;The result printed back is identical to the input, with the notable exception
;;of the original quotation mark. Clojure interpreted the data, 'x, as being a
;;meaningless symbol that just happened to look like "x". There is, however, a
;;difference between x and "x" and \x....

;;Symbols play a crucial role in giving meaning to expressions, and
;;they have non-primitive evaluation rules.

;;What happens if we don't use a quote?  Foreshadowing....

;;We'll get into trouble because clojure will NOT interpret the thing we're
;;evaluating as data.....this requires us to give meaning to the symbol in order
;;for Clojure to evaluate it.
(comment
  x ;try it
)

;;Clojure prints an error if we try to evaluate an unquoted symbol:

;;=CompilerException java.lang.RuntimeException:
;;Unable to resolve symbol: x in this context,
;;compiling:(C:\Users\somebody\AppData\Local\Temp\form-init8366984737339229996.clj:1:5500)=

;;The error tells us what the problem is (x is undefined in this context), and
;;where is occurred.

;;In the next section we'll see how to express complex ideas, like defining what
;;x means, all while conversing with the REPL.

;;Complex Expressions
;;===================

;;In the original English example, we used a sentence to gain familiarity with
;;expressing a complicated concept from primitive concepts. Letters formed
;;words, words formed a phrase, and a phrase ending with a period formed a
;;sentence. In each of the preceding examples, we built something complex out of
;;something primitive.  We will use => to informally denote rules for understanding
;;how our language expresions are built.

;;Letters => Words
;;Words => Phrase
;;Phrases + \. => Sentence

;;Interestingly enough, we were able to decompose a complex sentence into its
;;component pieces via the same rules:

;;Sentence => Phrases + \.
;;Phrase => Words
;;Word => Letters

;;In this case, letters are the most primitive elements of a sentence. Since we
;;have defined Clojure primitives, are there analogous ways to compose them into
;;things like phrases, and sentences?

;;Lists
;;=====

;;Lists are the fundamental means of composing primitives into complex
;;expressions. In fact, they are central to expressing anything in Clojure.
;;Lists are denoted by enclosing pairs of parenthesis. For now, we will quote
;;them to treat them as unevaluated data.:

'(this is a quoted list that will not be evaluated)

;;We can put anything inside the list, including other lists:
'(this list contains (another list))
'(1 first 2 second 3 third)
'("Lists" :can 'contain :different "types" (:like-numbers 0 1 2 3 4 5 6 7))

;;Unquoted Lists Are Complex Expressions
;;======================================

;;If we remove the preceding quotation mark, the list is no longer data - per
;;our primitive evaluation rules. What will the REPL do?

;;Clojure follows an interesting evaluation rule regarding lists:
;;- the first element of the list is treated specially.
;;  - the REPL will =E=valuate this element and then =A=pply the
;;    result to the remaining elements
;;- Any elements of the list after the first element are treated
;;  as arguments.  They are evaluated, and their results are used
;;  as input to Apply.

;;Let's try simple arithmetic first.

;;Assume, for the sake of argument, that in the REPL's universe, the symbol '+
;;is pre-defined as addition.

(+ 2 3)

;;Yields 5

;;Assume we have similar operators associated with the symbols '*, '/, for
;;multiplication and division respectively...

(* 2 3)
;;yields 6
(/ 2 3)
;;yields 2/3


;;What happens if we insert an arbitrary symbol into the beginning
;;of the list?
(comment
  (f 2 3))


;;We get an error because f is not defined...
;;CompilerException java.lang.RuntimeException:
;;Unable to resolve symbol: f in this context,
;;Compiling:(C:\Users\tspoon\AppData\Local\Temp\form-init8366984737339229996.clj:1:1) 

;;How can we define 'f? Or any other symbol?

;;In clojure parlance, the structure (f x1 x2 x3 ....) is called a
;;=form=, with the first element of the list denoting what kind of
;;form it is.  Clojure has some useful built-in forms that
;;define special rules for evaluation.  These "special forms" or
;;built-in forms are the basis of clojure.  The first special form
;;is (def name expr), which lets us define things.

;;Any time the REPL is passed a form, i.e.
;;(something x y z and more args ....)
;;the REPL will attempt to evaluate the form and print the result.

;;Invalid forms will trigger exceptions.

;;Using def and symbols to define Vars
;;=====================================

;;Clojure provides many built-in symbols that allow us to express things. Key
;;amongst this is =def=, which allows us to define a symbol, to give it meaning.

(def x 2)

;;yields

;;nil
;;user>

;;In this case, the REPL has acknowledged our request to define x as the integer
;;2, and printed nil in response. nil is a typical result for operations like
;;def, which cause a side-effect, but yield no useful value.

;;Now, if we evaluate x, unquoted.....
x
;;yields
;;2

;;user>

;;Let the REPL help you via =doc= and =source=
;;============================================

;;Before we proceed, you should know how to ask the REPL for help. The
;;preponderance of things in clojure - particularly things that are built-in or
;;pre-defined - have some documentation associated with them. This
;;documentation, and many times even the source code, is available via the REPL
;;using the =doc= function.

;;Using =doc= and =source= will turn the REPL into an interactive library that
;;allows you to look up information on unfamiliar symbols you may encounter.

;;=doc= is provided in the clojure.repl namespace, which has already been
;;referenced for you. If you ever find that the =doc= function doesn't' work,
;;tell the REPL

(use 'clojure.repl)
;;to bring it into scope.


;;For instance, we can look up the
;;documentation on doc:
(doc doc)

;; -------------------------
;; clojure.repl/doc
;; ([name])
;; Macro
;;   Prints documentation for a var or special form given its name
;; nil

;;and def...
(doc def)

;; -------------------------
;; def
;;   (def symbol doc-string? init?)
;; Special Form
;;   Creates and interns a global var with the name
;;   of symbol in the current namespace (*ns*) or locates such a var if
;;   it already exists.  If init is supplied, it is evaluated, and the
;;   root binding of the var is set to the resulting value.  If init is
;;   not supplied, the root binding of the var is unaffected.

;;   Please see http://clojure.org/special_forms#def
;; nil

;;The aforementioned mathematical operators are
;;also documented:
(doc +)

;; -------------------------
;; clojure.core/+
;; ([] [x] [x y] [x y & more])
;;   Returns the sum of nums. (+) returns 0. Does not auto-promote
;;   longs, will throw on overflow. See also: +'
;; nil

;;As you progress through this tutorial, use =doc= liberally
;;when you see something unfamiliar.  Chances are, there will
;;be an immediate explanation of it in the REPL.  If you want to
;;go further, you can use (=source= the-thing) to view the source
;;code, if available.  This is an excellent way to learn more
;;clojure.

;;Note: many integrated development environments will provide
;;the same information, without requiring the repl-based
;;path we demonstrated here.

;;Vars
;;====

;;Specifically, the symbol x has meaning in the current context. The current
;;context includes a place to hold defined symbols like =x=, called a
;;=namespace=. So, in the current namespace, =onramp.crawling=, the REPL can
;;now resolve the meaning of =x=. In Clojure parlance, we say that =x=
;;is "bound" to the value 2. This binding implies that =x= is a Var, or defined
;;symbol, which resides in the onramp.crawling namespace.

;;From this point forward, when we evaluate bound symbols, that is Vars, the
;;resulting evaluation will be the value passed in during def.

(def person "Tom")
;;=person= is bound to "Tom"
(def color :blue)
;;=color= is bound to :blue

;;We can refer to specific vars using the long-form prefixing the namespace and
;;a forward-slash to the var:

onramp.crawling/person
;;evaluates to "Tom"

;;Clojure has a pre-defined var called =list=
;;the allows us to construct lists.
(def the-list (list person color))

;;When the REPL evaluated the preceding expression, it first evaluated 'def,
;;resolving it into clojure's built-in definition facility.

;;According to the rules of def, clojure knows the that second symbol is
;;supposed to be the thing being defined, and so the REPL does not evaluate
;;=the-list=.

;;Finally, the REPL evaluates the definition of =the-list=, the third argument
;;of the application of (def ...).

;;This is another list, which means evaluation starts again...
;;  -With =list= as the thing to apply
;;  -and =person=, =color= as arguments...
;;     -=list= evaluates to the built-in facility for creating lists.
;;     -=person= evaluates to the string "Tom", per our definition.
;;     -=color= evaluates to the keyword :blue, per our definition.
;;  -(list "Tom" :blue) evaluates to '("Tom" :blue)

;;The var =the-list= refers to the resulting '("Tom" :blue) list.
;;We can use other built-in definitions to examine the =the-list=

(first the-list)
;;"Tom"
(second the-list)
;;:blue
(rest the-list)
;;(:blue)

;;Collecting Data With Vectors
;;============================

;;Rather than using quoted lists, we can accomplish a similar function - denoted
;;a collection of data - using a clojure vector.
;;Vectors have special syntax, denoted by wrapped brackets, [...], and may also
;;be constructed using the =vector= symbol.

(def v1 ["this" :is :a 'vector])
v1
;;["this" :is :a 'vector]
(def v2 (vector :also :a :vector))
v2
;;[:also :a :vector]

;;Our positional functions work on vectors as well...
(first v2)
;;:also
(second v2)
;;:a
(rest v2)
;;(:a :vector)

;;Note that evaluating =rest= returned what appears to be a list, not a vector.
;;We'll discuss this behavior when we work with sequences later.

;;Vectors are idiomatic in clojure, since they specifically denote data, and
;;they have some extremely useful performance characteristics.

;;From a linguistic perspective, vectors give us a visual cue that we're working
;;with a data structure, which, unlike a like list, will not be interpreted as a
;;form to be evaluated.

;;Clojure's built-in functions expect "bindings" - associations between symbols
;;and values - to be expressed as vectors (which is the impetus for introducing
;;them now). With vectors in hand, we can use more built-in functionality to
;;evaluate more complex expressions...

;;Local definitions with =let=
;;============================

;;Clojure provides another means for binding symbols to values. When we look at
;;the behavior of =def=, we see the that definition is in a sense, global. If
;;=x= is defined to be a value, the var =x= is bound to said value everywhere we
;;evaluate =x=, unless something changes ( maybe redefine =x=...).

;;Rather than rely on global bindings, we introduce a more localized, ad-hoc
;;form of binding vars. The clojure =let= form (let [& bindings] body) defines
;;local, =lexically-scoped= Vars that hold in the expression contained by let.

;;When the REPL sees a =let= form, it expects a =vector= of bindings, which act
;;much like our bindings from the =def= form, followed by a /body/ to evaluate.
;;=let= effectively creates a pocket environment, where the symbols are bound
;;according to the supplied bindings, then in this environment, the /body/ is
;;evaluated. Outside of the let form, the bindings do not exist. They are said
;;to be lexically scoped to the =body= of the let.

;;For example,
(let [state "Idaho"]
  state)
;;yields
;;"Idaho"

;;Despite the fact that we never defined =state=, inside the =let= form, state
;;is defined and bound, so the REPL can evaluate it.

;;We can have multiple bindings for a let:
(let [state "Idaho"
      city "Boise"]
  [city state])

;;["Boise" "Idaho"]

;;We can also nest let forms, like any other expression:
(let [state "Idaho"]
  (let [city "Boise"]
    [city state]))

;;["Boise" "Idaho"]

;;Interestingly, we can bind existing Var definitions, and
;;temporarily override, or =shadow= them inside of a let:
(let [state "Idaho"]
  (let [state "Wisconsin"]
    state))
;;"Wisconsin"

;;Function Definitions With =fn=
;;==============================

;;With =let= in hand, and the notion of lexically-scoped or locally-meaningful
;;var bindings, we can define functions. In clojure, functions are incredibly
;;important and useful. They allow us to define ways to transform inputs,
;;compute things, and generally get stuff done. As with mathematical functions,
;;Clojure functions take arguments as input and return a result.

;;The (fn [& bindings] body) form, evaluated by itself, takes a vector of
;;bindings - just like =let=, and treats these bindings as arguments to the
;;function. Like let, the body of the function is evaluated - at some point -
;;with the bindings in place. The trick is that the bindings are established
;;when the function is =invoked=, that is when the function is =applied= to
;;arguments as part of a clojure =form=. The result is a function object that
;;may be evaluated as the first element of a form, and applied against
;;arguments.

;;Most clojure functions follow the notion of purity - that is:
;;- the function always returns the same output for a given input
;;- the function depends only on its arguments, and nothing else

;;Functions that follow these properties are useful because they are easy to
;;understand and reason about. Let's define a function that adds 42 to its
;;input.

(fn [x] (+ x 42))

;;#object[user$eval7721$fn__7722 0x7152a114 "user$eval7721$fn__7722@7152a114"]

;;The REPL gives us back a weird result if we send it a (fn ....) form, but it
;;looks like some kind of data with garbage in the name. This is clojure's way
;;of telling us that we have gotten back an object, in this case, the function
;;we defined.

;;We should be able to use this object as the first element of a form, with any
;;arguments as remaining elements.

((fn [x] (+ x 42)) 2)
;;44

;;We have no way of referring to the function, or do we?
;;What about =def= and =let=?
(def add-42 (fn [x] (+ x 42)))
;;nil

(add-42 2)
;;44
;;Similarly...

(let [add (fn [x] (+ x 42))]
  (add 2))
;;44

;;Note the difference....add-42 exists in the larger scope of the namespace,
;;where add exists in the lexical scope of the let form, hence, we don't have a
;;reference to add once the let form is evaluated.

;;defn
;;====

;;Defining named functions is so commonplace, that clojure provides a nice
;;shorthand for it:
;;(defn name [& args] body)

(defn add-2 [x] (+ x 2))

;;defn actually has more options, like the
;;ability to add documentation strings....
(defn add-2
  "Add 2 to any number!"
  [x]
  (+ x 2))

;;(doc add-2)
;; -------------------------
;; user/add-2
;; ([x])
;;   Add 2 to any number!

;;Function Arity
;;==============

;; Functions can have multiple "arities", or argument bindings.
;; - Use a list of ([bindings] body) for each arity.
;; - Denote "list" of arguments with [& something]
;;   - Tells clojure to collect the items after & and dump them into a list
;;     bound to "something"
;;   - Example of destructuring, more on this later.
(defn variadic
  ([x]
   (println (str "you called me with 1 arg, " x)))
  ([x y]
   (println (str "you called me with 2 args: " [x y])))
  ([x y & more]
   (println (str "you called me with >2 args: " (into [x y] more)))))

(variadic 1) ;=> you called me with 1 arg: 1
(variadic 1 2) ;=> you called me with 2 args: [1 2]
(variadic 1 2 3 4 5 6) ;=> you called me with >2 args: [1 2 3 4]

;;Function Composition
;;====================

;; Functions are a useful abstraction because they
;; compose easily.
;; - Style of programming called "functional programming"
;; - Underlying philosophy in clojure.
;; - Define complex functions by composing simple functions.

(defn square [x] (* x x))
(defn distance [[x1 y1] [x2 y2]]
 (Math/sqrt (+ (square (- x2 x1))
               (square (- y2 y1)))))
(defn normal [[x y]]
 (let [d (distance [0 0] [x y])]
   [(/ x d) (/ y d)]))

;;Application with =apply=
;;========================
;;We can use the =apply= form to apply a function to a sequence of arguments as
;;if the function were invoked with the arguments.

(apply + [1 2])
;;3
(apply * [2 4 6 8])
;;384
(apply * '(0.5 0.5 0.5))
;;0.125
(apply list [1 2 3 4 5 6])
;;(1 2 3 4 5 6)

;;Logical operations via =and=,=or=, =not=
;;========================================

;;Logical operations are "short-circuiting", which means that the result will
;;return as soon as any sufficient criteria is met, without evaluating the rest.

(and true false) ;false
(and false
     (fn [] (println "launch!") true)) ;false

(or  true false) ;true
(or  true (fn [] (println "eject!") false)) ;true
(not true) ;false
(not false) ;true

;;"Truthiness"
;;============

;; Clojure allows things that don't look like =true= or =false= to denote =true=
;; and =false=.
;; - Eliminates a surprising amount of boiler plate since we don't bother to
;;   explicitly check for =true= or =false=
;; - Any expression that evaluates to a value "other" than false or =nil= is
;;   considered logically equivalent to =true=

(and true [1]) ;[1] => true
(and true nil) ;nil => false
(and nil)      ;nil => false
(and 2 :a)     ;:a  => true
(or false nil :B false) ;:B => true
(and [1 2 3] false) ;false
(or [1 2 3] [4 5 6]) ;[1 2 3] => true

;;Conditional Control, =if=, =cond=, =case=
;;========================================

;;Sometimes we want to compute different results based on some condition.
;;Typically, conditions will be functions that take a single input and return
;;true or false (or nil). These logical predicate functions are typically
;;implemented using the =if= form.

;;To help us out, we'll use the function (println ...) to tell the REPL to print
;;output...

(println
 (if true "it was true" "it was false"))
(println
 (if false "it was true" "it was false"))
(println
 (if nil  "it was true" "it was false"))

;;We can define a function that determines if
;;a number lies between an upper and a lower bound:
(defn between? [x lower upper]
  (if (and (> x lower)
           (< x upper))
      true
        false))

;;Clojure has equality operators built-in, so you also get <= , >=, = , and
;;more.

;;We can equivalently define between? without using if....since and will return
;;true or false...it's already a predicate.
(defn between? [lower upper x]
  (and (> x lower)
       (< x upper)))

;;=if= does not evaluate anything it doesn't have to, we never get to the false
;;branch:
(if (between? 0 20 10)
    (println "all-clear!")
    (println "launch the missiles!"))

;;If we have nested conditions, it's easier to use =cond=
(cond (between? 20 40 10) :a
      (between? 40 42 10) :b
      (= 10 10)      :ten
      :else (println "launch the missiles!"))

;;=case= is useful if we have a specific value
(case 42
  0  :zero
  10 :ten
  42 :forty-two
  :dunno)

(if (first [nil 1 2])
    :first-exists
    :first-is-nil)

;;:first-is-nil
(if-let [x (first [1 2])]
    x
    :first-is-nil)

;;Iteration via =loop=/=recur=
;;============================
;;Looping isn't incredibly common in clojure, we'll see later that we can
;;accomplish much of what looping is typically used for via more expressive
;;functions. Still, the ability to tell the REPL to repeatedly evaluate an
;;expression is fundamental to computing things.

;;Clojure provides us with the loop/recur idiom.
;;Inside of a (loop [& bindings] & body) form, we can re-enter
;;the loop - loop again - using the (recur & bindings) form.

;;Count to ten...
(loop [idx 0]
  (if (= idx 10) [:done idx]
      (recur (+ idx 1))))

;;We can replace (+ idx 1) with the more idiomatic (inc x)
(loop [idx 0]
  (if (= idx 10) [:done idx]
      (recur (inc idx))))

;;Find the 10th odd number after 0
(loop [idx   0
       n     0
       acc   0]
  (if (= n 10)    acc ;return the number
      (recur (inc idx)
             (if (odd? idx) (inc n) n)
             (if (odd? idx) idx acc))))
;;19

;;=do= things
;;===========
;;The =do= form allows us to evaluate a sequence of forms, and ignores the
;;intermediate results, returning only the last result. =do= is useful for
;;performing "side effects" like printing.

(defn spooky-add-2 [x]
  (do (println "action")
      (println "at a distance")
      (println "is")
      (println "spooky")
      (+ x 2)))

;;We can approximate do using =let=:
(defn spooky-add-4 [x]
  (let [_ (println "action")
        _ (println "at a distance")
        _ (println "is")
        _ (println "spooky")]
    (+ x 4)))

;;In the preceding case, we told the REPL we don't care
;;about the var being bound, to basically ignore it.
;;We communicated this using the _ underscore as the var name.

;;=eval= expressions
;;the =eval= form allows you to evaluate arbitrary expressions.
;;- the /E/ in REPL
;;- interprets clojure forms, returning a result
;;- this mechanism is far more powerful, leading to macros
;;  - outside the scope of this training.
(eval '(+ 2 3))
;;5
(eval (list 'list 2 3 4 ''x))
;;(2 3 4 x)
(eval (list 'def 'x 2))
;;#'training.crawling/x
x
;;2

;;Simple Input/Output Via =read=, =println=
;;=========================================
;;Clojure provides full access to the same functionality that
;;the REPL uses to read input and coerce it into clojure expressions.
;;=read= lets us collect a line of input from the user:
(comment
  (read)) ;try it out

;;=println= evaluates the expression, and prints the result with a new line.
(println (+ 2 3))
;Build your own repl...
(defn repl [n]
  (loop [remaining n]
    (if (zero? remaining) :done
     (do (println (eval (read)))
         (recur (dec remaining))))))

(comment  ;try it out...
  (repl 2))


;;Practical Exercise: Guess the Number
;;====================================
;;We now know enough to express a fairly simple program. Our program will tell
;;the user to pick a random number between 0 and 100, then search for the user's
;;number.

(defn read-yes-no
  "Given a simple prompt, msg, will prompt the user to enter input of y or n. Will
  parse the result into a keyword :yes or :no, or throw on exception on bad
  input."
  [msg]
  (println (str msg ", answer y|n"))
  (case  (keyword (read))
    :y :yes
    :n :no
    (throw (Exception.  (str "bad-input!")))))

(defn pick-number
  "Given a lower and upper bound, repeatedly tries to guess the number using
  binary search. Recursively calls itself, polling the user for correctness each
  time, and letting the user's feedback guide the binary search."
  [lower upper]
  (let [half-length (quot (- upper lower)  2)
        guess       (+ lower half-length)]
    (case (read-yes-no  (str "is your number " guess " ?"))
      :yes  (println "Thanks for playing!")
      :no   (case (read-yes-no (str "Is your number less than? " guess))
              :yes  (pick-number lower (dec guess))
              (pick-number  (inc guess) upper)))))

(defn pick-number-iterative
  "Given a lower and upper bound, repeatedly tries to guess the number using
  binary search. Loops, polling the user for correctness each time, and letting
  the user's feedback guide the binary search. This version uses loop/recur"
  [lower-init upper-init]
  (loop [lower lower-init
         upper upper-init]
    (let [distance (quot (- upper lower)  2)
          guess    (+ lower distance)]
      (case (read-yes-no  (str "is your number " guess " ?"))
        :yes  (println "Thanks for playing!")
        :no   (case (read-yes-no (str "Is your number less than? " guess))
                :yes  (recur lower (dec guess))
                (recur  (inc guess) upper))))))

(defn play!
  "Plays a single game of pick-the-number. User should select a number from 0 to
  100"
  [] (pick-number 0 100)) 
