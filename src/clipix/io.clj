(ns clipix.io
  (:require [clojure.string :as str]))

(defn parse-input
  [s]
  (str/split s #"\s+"))