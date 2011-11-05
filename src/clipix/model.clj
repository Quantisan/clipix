(ns clipix.model)

(defn to-colour
" 
Converts a one-character string to 'colour', which is a char.
"
  [s]
 (first s))

(defn get-cell
" Arguments:
  t -- table
  x -- int of x coordinate, starts at 1
  y -- int of y coordinate, starts at 1
"
  [t x y]
  (get-in t [(dec y) (dec x)]))

(defn put-cell
" Arguments:
  t -- table
  x -- int of x coordinate, starts at 1
  y -- int of y coordinate, starts at 1
  c -- a Character representing colour
"
  [t x y c]
  (assoc-in t [(dec y) (dec x)] c))

(defn is-colour?
" Is (X,Y) of the same colour as the given c?
"
  ([t [x y] c]
    (is-colour? t x y c))
  ([t x y c]
    (= (get-cell t x y) c)))

(defn adjacents
" Returns adjacents coordinates.

  Warning:
  No limit checking, so results can be out of bound.
"
  [x y]
  (let [delta-xy       [[1 0] [0 1] [-1 0] [0 -1]]]
    (map (partial map + [x y]) delta-xy)))

(def ^{:private true
       :doc "local buffer for use with crawl-fill"}
      buffer (atom []))

(defn- crawl-fill
" Fill crawler with side effect on a local buffer.
  
  Algorithm:
  When current pixel is target colour, 
  paint current pixel, and recur on neighbours.
"
  [[x y] old-c new-c]
  (when (is-colour? @buffer x y old-c)    
    (swap! buffer put-cell x y new-c)
    (doseq [xy  (adjacents x y)]
      (crawl-fill xy old-c new-c))))

(defn flood-fill
" A wrapper to crawl-fill to reset the local buffer.
"
  [t x y c]
  (let [old-c   (get-cell t x y)]
    (do 
      (reset! buffer t)
      (crawl-fill [x y] old-c c)
      @buffer)))

