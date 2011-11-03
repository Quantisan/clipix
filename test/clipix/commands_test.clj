(ns clipix.commands-test
  (:use [clipix.commands])
  (:use [clojure.test]))

(def table0 [[\O \O \O] [\O \O \O] [\O \O \O] [\O \O \O]])
(def table1 [[\O \A \O] [\O \O \O] [\O \O \O] [\A \O \O]])

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
         (L table0 2 1 "A"))))

(deftest V-test
  (is (= [[\O \A \O] [\O \A \O] [\O \A \O] [\O \O \O]]
         (V table0 2 1 3 "A"))))