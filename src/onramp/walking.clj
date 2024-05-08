;;In this namespace, we broaden our understanding of clojure by examining its
;;plethora of built-in data structures and libraries for working with them.
(ns onramp.walking
  (:require [clojure.set]
            [clojure.string]))

;;Common Mathematical Functions
;;=============================
(+ 2 3) ;5
(* 2 3) ;6
(/ 2.0 3.0) ;0.6666666666666666
(/ 2 3) ;2/3

(Math/pow 2 3); 8.0
(Math/sin Math/PI)
;;1.2246467991473532E-16
(Math/cos Math/PI) ;-1.0

;;[Minor digression]:

;;Prior to clojure 1.11, we used interop for transcendental functions, namely
;;from the java.lang.Math package, which clojure automatically imported and
;;aliased as Math (not the capital).  This is what happened just above.

;;You may still see examples using this, particularly legacy code. Going
;;forward, the same can be accomplished with the formal clojure.math
;;namespace (included after 1.11). It serves as a portable wrapper (for any
;;clojure implementation including clojurescript).  Usage is optional but
;;growing.

(require '[clojure.math :as clj-math])
;;We could have required it in the onramp.walking namespace declaration at the
;;top of the file, but the require form accomplishes the same thing for us,
;;aliasing clojure.matah as `math` (note the capital); we can equivalently:

(clj-math/pow 2 3); 8.0
(clj-math/sin clj-math/PI)
;;1.2246467991473532E-16
(clj-math/cos clj-math/PI) ;-1.0

;;compute remainder using mod
(mod  10 3) ;1
;;or use quot/rem
(quot 10 3) ;3
(rem  10 3)  ;1
(defn divides-by? [x n]
 (zero? (rem x n)))
(divides-by? 10 2) ;true
(divides-by? 10 3) ;false

;;Sequences
;;=========

;;Almost every clojure datastructure that serves as a collection can be viewed
;;through the Sequence abstraction.

;;A sequence is a "lazily" computed stream of values
;;-The source of the sequence is traversed on-demand
;;-Sequences look and act like lists.
;; -Except they are "lazy" vs "Eager".
;; -We only realize portions of the sequence we need
;;-We can work with "infinite" datastructures

;;Basic operations on sequences
;;=============================

;;Range generates a lazy sequence of numbers from [0..bound), exclusive.
(def numbers (range 100))
;;get the first element...
(first numbers)
;;get the nth element
(nth numbers 20)
;;get the last element
(last numbers)
;;return a lazy sequence that draws the first 10 elements
;;and stops
(take 10 numbers)
;;return a lazy sequence that drops the first 10 numbers,
;;starting at 10
(drop 10 numbers)
;;Clump the sequence into pairs
(partition 2 numbers)
;;Return a view of the sequence like a moving window
(partition 2 1 numbers)
;;Generate a sequence using a function
(take 10 (iterate (fn [x] (* x 10)) 1))
;;Concatenate sequences
(concat [:A :B :c] '(:d :e :f))

;;Sequence Comprehensions using =for=
;;===================================
;;We can use the (for [bindings] & body) form to declaratively dissect sequences
;;and return a new lazy sequence
(for [x (range 10)]
  x)

;;returns the cartesian product of xs and ys as a lazy sequence of vector pairs
(for [x (range 10)
      y [:a :b :c]]
  [x y])

;;limit the draws to odd values of x using :when
(for [x (range 10)
      y [:a :b :c]
      :when (odd? x)]
  [x y])

;;introduce lexical bindings using :let
(for [x (range 10)
      y [:a :b :c]
      :when (odd? x)
      :let [xx (* x x)]]
  [xx y])

;;traverse sequences using do-seq, good for side-effects
;;Uses the same binding options as for..
(doseq [x (range 10)]
  (println x))

;;Constructing Sequences and Vectors
;;==================================
;; =conj= is the general mechanism for conjoining elements to collections
;; - constructs a "new" collection with the conjoined element
;;   - original is preserved (unaltered)
;; - For uniformity, =nil= is treated as the empty-sequence
;; =conj= has different behavior based on the datastructure
;; - lists (eg. seqs) conjoin by prepending items to the head
;; - vectors conjoin by appending items to the tail
;; =cons= is a sequence-specific alternative to =conj= that
;; constructs new sequences

;;Construction
;;============
(conj []   2)   ;[2]
(conj [2]  3)   ;[2 3]
(conj '()  2)   ;(2)
(conj '(2) 3)   ;(3 2)
(cons 2 '())    ;(2)
(cons 3 '(2))   ;(3 2)
(cons 4 '(3 2)) ;(4 3 2)
(conj nil 2)    ;(2)
(def xs (list 1 2 3))
(def ys (rest xs))
xs ;(1 2 3)
ys ;(2 3)

;;Map, Filter, Reduce
;;===================
;;The three musketeers of functional programming.

;;Using these three functions, we can build sophisticated, composeable,
;;computational pipelines without needing to get into goopy loop code. Befriend
;;them....they are invaluable.

;;Map
;;===
;;"Map" a function onto a sequence of values.
;;We can view mapping as a transformation of the input sequence, where the
;;elements of the returned sequence have a function applied to them.

(map (fn [x] (+ x 2)) (range 10))
(map (fn [x] [x x])   (range 10))

;;We can map across multiple sequences simultaneously, and provide a function
;;that takes multiple args..

(let [xs (range 10)
      ys (repeat 2)
      zs (repeatedly (fn [] (rand-int 100)))]
  (map (fn [x y z] (+ x y z)) xs ys zs))

;;Filter
;;======
;;Filter takes a predicate function, and a sequence. The resulting sequence only
;;has elements that "passed" the filter, such that when the filter function was
;;applied to the element, the return value is not false.

;;Filter the sequence, retaining only odd numbers.
(filter odd? numbers)
;;Filter evens
(filter even? numbers)

;;Reduce
;;======
;;Reduce allows us to accumulate a result across a sequence, thereby "reducing"
;;the sequence to a single resulting value. Reduce can be called with or without
;;an initial value for the accumulator.
(defn sum-numbers [xs]
  (reduce + xs))

(sum-numbers numbers)
;;We can use reduce to define a function to select a subcollection
;;of the input with 0.5 probability:
(defn randomly-sample [xs]
  (reduce (fn [acc x]
            (if (> (rand) 0.5)
              (conj acc x)
              acc)) [] xs))

(randomly-sample numbers)

;;Maps and Sets
;;=============
;; Clojure provides implementations of data structures
;; that represent sorted/unsorted associative maps and sets.
;; - maps associate a /key/ to a /value/
;;   - effectively Sets of [ /key/ /value/ ] pairs.
;;   - no duplicate keys.
;; - sets are analogous to mathematical sets, but store any
;;   type of clojure data structure
;;   - support union, intersection, and other set-theoretic
;;     operations
;;   - no duplicate entries.
;; - Both types are compatible with sequences.

;;Maps
;;====
;; Because they are so useful and common, Clojure provides a syntax for them:
;;  - maps are sequences of key / value pairs surround by {} braces.
(def the-map
  {:first-key "first-value"
   :second-key "second-value"})
(keys the-map) ;(:first-key :second-key
(vals the-map) ;("first-value" "second-value")
(get the-map :first-key) ;"first-value"
(get the-map :second-key) ;"second-value"
(contains? the-map :third-key) ;false

;;Maps ctd.
;;=========
(def new-map   (assoc the-map :third-key 3))
(def newer-map (dissoc the-map :first-key))
(get new-map   :third-key) ;3
(get newer-map :third-key) ;nil
(get newer-map :first-key) ;nil
(get newer-map :third-key) ;3
(newer-map :third-key) ;3
(seq newer-map) ;([:second-key "second-value"])
(into {} [[:a 0] [:b 1]]) ;{:a 0 :b 1}
(hash-map :a 0 :b 1) ;{:a 0 :b 1}

;;Sets
;;====
;; Sets also have their own syntax:
;; - sequences of values inside #{} are read as sets.
(def s (conj #{} :a))
(contains? s :a) ;true
(s :a) ;:a
(filter s [:a :b :a]) ;[:a :a]
(def s2 #{:a :b :c})
(clojure.set/union s s2);#{:c :b :a}
(clojure.set/intersection s s2);#{:a}
(clojure.set/difference s2 s); #{:b :c}
(into #{} [:a :a :a :a :b]) ;#{:a :b}

;;Strings Revisited and Regexes
;;=============================
;; Strings are prevalent in a lot of processing tasks, so Clojure has a array of
;; functions for dealing with them.

;; - Of note, regular-expressions are related to processing strings.
;; - Regular expressions are delimited by #"..."
;;   - we will not spend time on Regexes unless necessary
;;   - i.e. library usage requires them

(seq "Hi") ;(\H \i)
(nth "Hello" 3); \l
(clojure.string/replace
  "Hello" "e" "a") ;"hallo"
(clojure.string/upper-case
  "hello") ; "HELLO"
(clojure.string/lower-case
 "Hello") ; "hello"

(str "h" \i [:not-a-string])
;"hi[:not-a-string]"
(clojure.string/join ","
 ["x" "y" "z"]) ;"x,y,z"
(clojure.string/join \newline
 ["x" "y" "z"]) ;"x\ny\nz"
(clojure.string/split
  "x,y,z" #",") ;["x" "y" "z"]
(defn line->tabs [ln]
 (clojure.string/split ln #"\t"))
(line->tabs
 (clojure.string/join \tab [1 2 3]))

;;The Road So Far
;;==============
;; We have quite an arsenal of expression by now:
;; - integers, floats, strings, booleans
;; - keywords, quoted symbols
;; - Sequences (lists, vectors, maps, sets)
;; - Functions
;; We've also seen that Clojure provides a fairly
;; large standard library for working with these data types.
;; - "Id rather have 100 functions working on a single data type"
;;   - [some hippy]
;; We'll explore a few more useful functions, then move on to
;; examples
;; - where we can apply what we've seen

;;Useful Builtins
;;===============
(def xs (range 3))
(group-by odd? xs) ;{false [0 2], true [1]}
(group-by
  (fn [x] (if (odd? x) :odd :even))
  xs)   ;{:even [0 2], :odd [1]}
(partition-by
 #(< % 4) (range 5)) ;((0 1 2 3) (4))
(map-indexed (fn [idx x] [idx x]) xs)
;;([0 0] [1 1] [2 2])
(def m {:a {:b 1}})
(assoc-in m [:a :c] 2)  ;{:a {:b 1 :c 2}}
(update-in m [:a :b] inc);{:a {:b 2}}
(update-in m [:a :b] + 2) ;{:a {:b 3}}
(get-in m [:a :b]) ;1
(vec (range 2)) ; [0 1]
(vec #{:a :b})  ; [:b :a]
(into [] #{:a :b}); [:b :a]
(into [] (map inc) (range 2)) ;[1 2]
(into [:z]  {:a 2 :b 3}) ;[:z [:a 2] [:b 3]]

;;IO (Input/Output)
;;=================
;; IO in clojure parlance typically refers
;; reading (input) or writing (output) data to
;; a "stream".
;; - typical streams include files, network sockets
;; - There are two defined streams:
;;   - =*in*=  the stream the REPL's process draws from
;;   - =*out*= the stream the REPL prints to
;;   - notice the asteriks around *in* and *out*
;;     - naming convention (earmuffs) to indicate that the Vars are
;;       "dynamically scoped"
;;     - more to come...
;; Clojure provides some useful functions for reading and
;; writing files.
;; - =spit=, =slurp=, =line-seq=

;;Simple File Operations
;;======================
;;=spit= and =slurp= are sufficient for quick and
;;dirty file operations
;;use spit to spit a string to a file.
(spit "output.clj" (str "Howdy!"))
;;use slurp to return the contents of a file as a string
(println (slurp "output.clj"))

;;Simple Tab-Delimited Files
;;==========================
;;spit structured data to a tab-delimited file.
(let [tabify #(clojure.string/join "\t" %)]
   (->> (concat [(tabify ["X" "Y"])]
        (->> (range 10)
             (map (fn [idx] [idx (rand)]))
             (map tabify)))
       (clojure.string/join \newline)
       (spit "outlines.txt")))
;;read the data back in....
(let [untabify #(clojure.string/split % #"\t")]
   (->> (slurp "outlines.txt")
        (clojure.string/split-lines)
        (map untabify)
        (map #(into [] (map read-string) %))))

;;Lazy Tab-Delimited Files
;;========================
;;Find the first entry with a y-value > 0.5
;;- without reading in the entire file.
(with-open [rdr (clojure.java.io/reader
                   "outlines.txt")]
  (let [untabify #(clojure.string/split % #"\t")]
     (->> (line-seq rdr)
          (map untabify)
          (map #(into [] (map read-string) %))
          (drop 1) ;ignore headers
          (filter (fn [[x y]]
                    (> y 0.5)))
          (first))))

;;Printing Clojure Data to files
;;===============================
;;We can =read= and =print= clojure data
;;- It stands to reason we should be able to change
;;  the targets
;;- =read= and =print= to files?
;;- naive method for "saving" or "serializing" data

;;save
(->> (with-out-str (print {:a 2}))
     (spit "hash-map.edn"))
;;recover
(->> (slurp "hash-map.edn")
     (clojure.edn/read-string))
;;{:a 2}

;;You may note we used clojure.edn/read-string. There is a
;;clojure.core/read-string, which is slightly more powerful (and possibly less
;;secure). It allows "read-time evaluation", which means you can execute
;;arbitrary code at read-time prior to evaluation. This can be handy in some
;;cases, but it's generally considered a security vulnerability.
;;clojure.edn/read-string prevents this implicitly and only yields inert data.

;;We used the .edn suffix out of convention, since the clojure data
;;forms the basis of Extensible Data Notation (EDN), which is a broader
;;serialization format.

;;IO In Practice
;;==============
;;The preceding examples are great for small things
;;- for varying definitions of "small"

;;In practice, we may choose to offload these types of IO routines to
;;special-purpose libraries. 
;;- Typically optimized for size and space
;;- May offer compression, encryption, other conveniences.

;;Clojure has industrial strength libraries for data processing and munging from
;;the scicloj community, most notable tech.v3.dataset and its high-level wrapper
;;tablecloth.

;;Still, we can get a lot of mileage with our pre-defined IO.
