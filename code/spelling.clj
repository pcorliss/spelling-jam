#!/usr/bin/env java -cp /Users/pcorliss/git/clojure-koans/lib/clojure-1.3.0.jar clojure.main

(println "hello world")

;(def words (fn [] (re-seq #"[a-z]+" (map .toLowerCase (slurp "data/big.txt")))))
;
(def file_read (fn [] (slurp "data/big.txt")))

;(def words (fn [text] (re-seq #"[a-z]+" (.toLowerCase text))))

;(def train (fn [features] ()))

(def nwords (frequencies (re-seq #"\w+" (.toLowerCase (file_read)))))

(println nwords)
