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
      (let [ a (first pair)
             b (second pair) ]
      (str (first pair) 
           (if (not (empty? b)) 
            (.substring b 1)))))))

(def replaces
  (fn [word]
    (for [pair (word_split word)]
      (let [ a (first pair)
       b (second pair) ]

      (for [alpha alphabet]
        (str a
             (if (not (empty? b)) 
              (str alpha
                (.substring b 1)))))))))

(def inserts
  (fn [word]
    (for [pair (word_split word)]
      (let [ a (first pair)
       b (second pair) ]
      (for [alpha alphabet]
        (str a
            alpha
            b))))))

(def transposes
  (fn [word]
    (for [pair (word_split word)]
      (let [ a (first pair)
             b (second pair) ]
        (str a
             (if (> (.length b) 1)
                (str (second b) (first b) 
                     (if (> (.length b) 2)
                       (.substring b 2))))
)))))

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
