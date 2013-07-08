#!/usr/bin/env java -cp /Users/pcorliss/git/clojure-koans/lib/clojure-1.3.0.jar clojure.main

(println "hello world")

(def file_read (fn [] (slurp "data/big.txt")))

(def nwords (frequencies (re-seq #"\w+" (.toLowerCase (file_read)))))

(def known (fn [words] (filter #(contains? nwords %) words)))

(def correct 
  (fn [word] 
    (not(empty? (known [word]))
)))

;(println nwords)

(println "Computer:" (correct "computer"))
(println "Hammer:" (correct "hammer"))
(println "Box:" (correct "box"))
(println "Love:" (correct "love"))
(println "fizzbuzz:" (correct "fizzbuzz"))
