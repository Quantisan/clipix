(ns clipix.commands
  (:require [clojure.string :as str]))

(defn to-colour
" Converts a one-character string to 'colour', which is a char.
"
  [s]
  (char (first s)))

(defn I
" Create a new M x N image with all pixels coloured white (O).
"
  [_ m n]
  (let [row  (into [] (repeat m \O))]
    (into [] (repeat n row))))

(defn C
"  Clears the table, setting all pixels to white (O).
"
  [t]
  (let [m  (count (first t))
        n  (count t)]
    (I nil m n)))

(defn L
" Colours the pixel (X,Y) with colour C.
"
  [t x y c]
  (let [x  (dec x)
        y  (dec y)
        c  (to-colour c)]
    (assoc-in t [y x] c)))

(defn V
" Draw a vertical segment of colour C in column X between rows Y1 and Y2
  (inclusive).
"
 [t x y1 y2 c]
 (let [L-vert  (fn [t y]       ;; closure on x and c
                 (L t x y c))]
   (reduce L-vert t (range (dec y1) y2))))