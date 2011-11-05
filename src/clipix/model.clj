(ns clipix.model)

(defn get-cell
  [t x y]
  (get-in t [(dec y) (dec x)]))

(defn put-cell
  [t x y c]
  (assoc-in t [(dec y) (dec x)] c))

(defn is-colour?
" Is (X,Y) of the same colour as the given c?
"
  ([t [x y] c]
    (is-colour? t x y c))
  ([t x y c]
    (= (get-cell t x y) c)))

(defn dimension
  [t]
  [(count (first t)) (count t)])

(defn adjacents
  [x y]
  (let [delta-xy       [[1 0] [0 1] [-1 0] [0 -1]]]
    (map (partial map + [x y]) delta-xy)))

(defn matching-neighbours
  [t x y]
  (let [colour         (get-cell t x y)
        adjacents      (adjacents x y)]
    (filter #(is-colour? t % colour) adjacents)))

(defn select-fill
" A list of neighbouring (x, y) which is the same colour as parameter (X,Y) 
  and shares a common side with any pixel in R also belongs to this region.
"
  [t x y]
  (let [c  (get-cell t x y)]))

(def ^{:private true} buffer (atom []))

(defn- crawl-fill
  [[x y] old-c new-c]
  (when (is-colour? @buffer x y old-c)    
    (swap! buffer put-cell x y new-c)
    (doseq [xy  (adjacents x y)]
      (crawl-fill xy old-c new-c))))

(defn flood-fill
  [t x y c]
  (let [old-c   (get-cell t x y)]
    (do 
      (reset! buffer t)
      (crawl-fill [x y] old-c c)
      @buffer)))

