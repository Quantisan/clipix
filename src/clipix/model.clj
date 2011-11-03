(ns clipix.model)

(def ^:dynamic *image* [])

(defn get-cell
  [t x y]
  (get-in t [(dec y) (dec x)]))

(defn put-cell
  [t x y c]
  (assoc-in t [(dec y) (dec x)] c))



(defn select-fill
" A list of neighbouring (x, y) which is the same colour as parameter (X,Y) 
  and shares a common side with any pixel in R also belongs to this region.
"
  [t x y]
  (let [c  (get-cell t x y)]))
  