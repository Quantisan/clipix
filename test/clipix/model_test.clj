(ns clipix.model-test
  (:use [clipix.model])
  (:use [clojure.test]))

(def table1 [[\A \B \C] [\E \F \G]])

(deftest get-cell-test
  (is (= \E
         (get-cell table1 1 2))))

(deftest put-cell-test
  (is (= [[\A \B \X] [\E \F \G]]
         (put-cell table1 3 1 \X))))