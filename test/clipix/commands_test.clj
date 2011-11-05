(ns clipix.commands-test
  (:use [clipix.commands])
  (:use [clojure.test]))

(def table0 [[\O \O \O] [\O \O \O] [\O \O \O] [\O \O \O]])
(def table1 [[\O \A \O] [\O \O \O] [\O \O \O] [\A \O \O]])
(def table2 [[\A \O \O] [\A \A \O] [\O \O \C]])

(deftest I-test
  (is (= table0
         (I nil 3 4))))

(deftest C-test
  (is (= table0
         (C table1))))

(deftest to-colour-test
  (is (= \A
         (to-colour "A"))))

(deftest L-test
  (is (= [[\O \A \O] [\O \O \O] [\O \O \O] [\O \O \O]]
         (L table0 2 1 "A")))
  (is (= [[\O \O \O] [\O \O \O] [\O \O \O] [\A \O \O]]
         (L table0 [1 4] "A"))))

(deftest V-test
  (is (= [[\O \A \O] [\O \A \O] [\O \A \O] [\O \O \O]]
         (V table0 2 1 3 "A"))))

(deftest H-test
  (is (= [[\O \O \O] [\O \O \O] [\O \O \O] [\O \A \A]]
         (H table0 2 3 4 "A"))))

(deftest F-test
  (is (= [[\B \O \O] [\B \B \O] [\O \O \C]]
         (F table2 1 2 "B")))
  (is (= [[\A \O \O] [\A \A \O] [\O \O \B]]
         (F table2 3 3 "B")))
  (is (= [[\A \B \B] [\A \A \B] [\O \O \C]]
         (F table2 3 2 "B"))))

(deftest S-test
  (is (= "OOO\nOOO\nOOO\nOOO")
      (S table0)))