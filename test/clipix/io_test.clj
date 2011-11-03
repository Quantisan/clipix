(ns clipix.io-test
  (:use [clipix.io])
  (:use [clojure.test]))

(deftest parse-input-test
  (is (= ["I" 2 3])
      (parse-input "I 2 3")))