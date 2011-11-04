(ns clipix.io-test
  (:use [clipix.io])
  (:use [clojure.test]))

(deftest parse-input-test
  (is (= ["I" 2 10]
         (parse-input "I 2 10")))
  (is (= ["L" 1 2 "C"]
         (parse-input "L 1 2 C"))))

(deftest parse-ints-test
  (is (= ["L" 1 2 "C"]
         (parse-ints ["L" "1" "2" "C"]))))