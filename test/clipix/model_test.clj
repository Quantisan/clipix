(ns clipix.model-test
  (:use [clipix.model])
  (:use [clojure.test]))

(def table1 [[\A \B \C] [\E \F \G]])
(def table2 [[\A \O \O] [\A \O \O] [\O \O \C]])

(deftest get-cell-test
  (is (= \E
         (get-cell table1 1 2)))
  (is (= nil
         (get-cell table1 4 4))))  ;; out of range

(deftest put-cell-test
  (is (= [[\A \B \X] [\E \F \G]]
         (put-cell table1 3 1 \X))))

(deftest is-colour?-test
  (is (= false
         (is-colour? table1 4 1 \A)))  ;; out of range
  (is (= true
         (is-colour? table1 3 1 \C)))
  (is (= false
         (is-colour? table1 3 1 \A)))
  (is (= true
         (is-colour? table1 [3 1] \C)))
  (is (= false
         (is-colour? table1 [3 1] \A))))
         
(deftest dimension-test
  (is (= [3 2]
         (dimension table1))))

(deftest adjacents-test
  (is (= [[3 2] [2 3] [1 2] [2 1]]
         (adjacents 2 2))))

(deftest matching-neighbours-test
  (is (= [[1 2]]
         (matching-neighbours table2 1 1)))
  (is (= [[3 1] [2 2]]
         (matching-neighbours table2 2 1)))
    (is (= []
         (matching-neighbours table2 3 3))))
  