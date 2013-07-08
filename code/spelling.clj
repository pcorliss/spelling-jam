#!/usr/bin/env java -cp /Users/pcorliss/git/clojure-koans/lib/clojure-1.3.0.jar clojure.main

(println "hello world")

(def file_read (fn [] (slurp "data/big.txt")))

(def alphabet (seq "abcdefghijklmnopqrstuvwxyz"))

(def nwords (frequencies (re-seq #"\w+" (.toLowerCase (file_read)))))

(def word_split 
  (fn [word] 
    (for 
      [i (range (inc (.length word)))] 
      [
       (subs word 0 i), 
       (subs word i (.length word))
      ]
)))

(def deletes 
  (fn [word]
    (for [pair (word_split word)]
      (str (first pair) 
           (if (not (empty? (second pair))) 
            (.substring (second pair) 1))))))

(def replaces
  (fn [word]
    (for [pair (word_split word)]
      (for [alpha alphabet]
        (str (first pair)
             (if (not (empty? (second pair))) 
              (str alpha
                (.substring (second pair) 1))))))))

(def inserts
  (fn [word]
    (for [pair (word_split word)]
      (for [alpha alphabet]
        (str (first pair)
              alpha
              (second pair))))))

(def transposes
  (fn [word]
    (for [pair (word_split word)]
        (str (first pair)
             (if (> (.length (second pair)) 1)
                (str (second (second pair)) (first (second pair)) 
                     (if (> (.length (second pair)) 2)
                       (.substring (second pair) 2))))
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
