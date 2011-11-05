(ns clipix.core
  (:use [clipix commands io])
  (:gen-class))

(def image (atom []))

(defn- cont?
  [s]
  (if (not= "X" s)
    s
    false))

(defn- call! [f args]
    (apply swap! image f args))

(defn command!
  [[f & args]]
  (cond
    (= "I" f) (call! I args)
    (= "C" f) (call! C args)
    (= "L" f) (call! L args)
    (= "V" f) (call! V args)
    (= "H" f) (call! H args)
    (= "F" f) (call! F args)
    (= "S" f) (call! S args)))

(defn input 
  []
  (print "> ") (flush)
  (if-let [in (cont? (read-line))]
    (do
      (let [args  (parse-input in)]
        (command! args))
      (input))
    nil))

(defn -main 
  [& args]
  (input))