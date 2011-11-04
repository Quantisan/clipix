(ns clipix.core
  (:use [clipix commands io])
  (:gen-class))

(def ^:dynamic *image* (atom []))

(defn cont?
  [s]
  (if (not= "X" s)
    s
    false))

(defmacro transform! 
" Takes string input and update data with corresponding command function.
"  
  [[f & args]]
 `(swap! *image* ~(symbol f) ~@args))

(defn input []
   (print "> ") (flush)
   (if-let [in (cont? (read-line))]
      (do
         (-> in
           (parse-input)
           (transform!))
         (recur))
      nil))

(defn -main 
  [& args]
  (input))