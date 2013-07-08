#!/usr/bin/env java -cp /Users/pcorliss/git/clojure-koans/lib/clojure-1.3.0.jar clojure.main

(defn file_read [] (slurp "data/big.txt"))

(def alphabet "abcdefghijklmnopqrstuvwxyz")

(def nwords (frequencies (re-seq #"\w+" (.toLowerCase (file_read)))))

(defn word_split 
  [word] 
  (let [ length (.length word) ]
    (for 
        [i (range (inc length))] 
        [
         (subs word 0 i), 
         (subs word i length)
        ]
)))

(defn deletes 
  [word]
  (for [pair word]
      (let [ a (first pair)
             b (second pair) ]
      (str a
           (if (not-empty b)
            (subs b 1))))))

(defn replaces
  [word]
  (flatten 
    (for [pair word]
        (let [ a (first pair)
         b (second pair) ]

        (for [alpha alphabet]
          (str a
               (if (not-empty b) 
                (str alpha
                  (subs b 1)))))))))

(defn inserts
  [word]
  (flatten
    (for [pair word]
        (let [ a (first pair)
         b (second pair) ]
        (for [alpha alphabet]
          (str a
              alpha
              b))))))

(defn transposes 
  "This is a new line and some doc"
  [word]
  (for [pair word]
      (let [ a (first pair)
             b (second pair) ]
        (str a
             (if (> (.length b) 1)
                (str (second b) (first b) 
                     (if (> (.length b) 2)
                       (subs b 2))))
))))

(defn edits1
  [word]
  (let [ splits (word_split word) ]
    (set 
    (concat
      (deletes splits)
      (replaces splits)
      (transposes splits)
      (inserts splits)
))))

(defn known
  [words]
  (not-empty (filter #(contains? nwords %) words)))

(defn correct 
  [word] 
    (or (known [word])
        (known (edits1 word))
))

;(println nwords)

(println "Computer:" (correct "computer"))
(println "Hammer:" (correct "hammer"))
(println "Box:" (correct "box"))
(println "Love:" (correct "love"))
(println "fizzbuzz:" (correct "fizzbuzz"))
(println "hamer:" (correct "hamer"))
(println "acount:" (correct "acount"))
(println "beetween:" (correct "beetween"))


;(println "Hammer: " (edits1 "hammer"))
