(ns clipix.core
  (:use [clipix commands io])
  (:gen-class))

(def image (atom []))

(defn cont?
  [s]
  (if (not= "X" s)
    s
    false))

(defn call-transform! [[f & args]]
  (when-let [fun  (resolve (symbol f))]
    (apply swap! image fun args)))

(defn input 
  []
  (print "> ") (flush)
  (if-let [in (cont? (read-line))]
    (do
      (let [args  (parse-input in)]
        (println @image) (flush)
        (call-transform! args))
      (input))
    nil))

(defn -main 
  [& args]
  (input))