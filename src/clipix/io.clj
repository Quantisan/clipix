(ns clipix.io
  (:require [clojure.string :as str]))

(defn parse-ints
  [coll]
  (let [[x & more]  coll]
    (when (seq x)
      (cons (try 
              (Integer/parseInt x)
              (catch NumberFormatException e
                x))
            (parse-ints more)))))

(defn parse-input
  [s]
  (let [coll  (str/split s #"\s+")]
    (parse-ints coll)))
