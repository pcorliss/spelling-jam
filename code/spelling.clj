#!/usr/bin/env java -cp /Users/pcorliss/git/clojure-koans/lib/clojure-1.3.0.jar clojure.main

(defn file-read [] (slurp "data/big.txt"))

(def alphabet "abcdefghijklmnopqrstuvwxyz")

(def nwords (frequencies (re-seq #"\w+" (.toLowerCase (file-read)))))

(defn word-split
  [word]
  (let [length (count word)]
    (for [i (range (inc length))]
        [(subs word 0 i) (subs word i length)])))

(defn deletes
  [word]
  (for [[a b] word]
    (str a
         (if (not-empty b)
           (subs b 1)))))

(defn replaces
  [word]
  (flatten
   (for [[a b] word]
     (for [alpha alphabet]
       (str a
            (if (not-empty b)
              (str alpha
                   (subs b 1))))))))

(defn inserts
  [word]
  (flatten
   (for [[a b] word]
     (for [alpha alphabet]
       (str a
            alpha
            b)))))

(defn transposes
  "This is a new line and some doc"
  [word]
  (for [[a b] word]
    (str a
         (if (> (count b) 1)
           (str (second b) (first b)
                (if (> (count b) 2)
                  (subs b 2)))))))

(defn edits1
  [word]
  (let [splits (word-split word)]
    (set
     (concat
      (deletes splits)
      (replaces splits)
      (transposes splits)
      (inserts splits)))))

(defn known
  [words]
  (not-empty (filter #(contains? nwords %) words)))

(defn correct
  [word]
  (or (known [word])
      (known (edits1 word))))

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
