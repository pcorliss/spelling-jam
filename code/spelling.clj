#!/usr/bin/env java -cp /Users/pcorliss/git/clojure-koans/lib/clojure-1.3.0.jar clojure.main

(println "hello world")

(defn file_read [] (slurp "data/big.txt"))

(def alphabet (seq "abcdefghijklmnopqrstuvwxyz"))

(def nwords (frequencies (re-seq #"\w+" (.toLowerCase (file_read)))))

(defn word_split 
  [word] 
  (for 
      [i (range (inc (.length word)))] 
      [
       (subs word 0 i), 
       (subs word i (.length word))
      ]
))

(defn deletes 
  [word]
  (for [pair (word_split word)]
      (let [ a (first pair)
             b (second pair) ]
      (str a
           (if (not (empty? b)) 
            (subs b 1))))))

(defn replaces
  [word]
  (for [pair (word_split word)]
      (let [ a (first pair)
       b (second pair) ]

      (for [alpha alphabet]
        (str a
             (if (not (empty? b)) 
              (str alpha
                (subs b 1))))))))

(defn inserts
  [word]
  (for [pair (word_split word)]
      (let [ a (first pair)
       b (second pair) ]
      (for [alpha alphabet]
        (str a
            alpha
            b)))))

(defn transposes 
  "This is a new line and some doc"
  [word]
  (for [pair (word_split word)]
      (let [ a (first pair)
             b (second pair) ]
        (str a
             (if (> (.length b) 1)
                (str (second b) (first b) 
                     (if (> (.length b) 2)
                       (subs b 2))))
))))

(def edits1 
  (fn [word] 
    ()))

(def known (fn [words] (filter #(contains? nwords %) words)))

(def correct 
  (fn [word] 
    (not (empty? (known [word]))
)))

;(println nwords)

(println "Computer:" (correct "computer"))
(println "Hammer:" (correct "hammer"))
(println "Box:" (correct "box"))
(println "Love:" (correct "love"))
(println "fizzbuzz:" (correct "fizzbuzz"))
