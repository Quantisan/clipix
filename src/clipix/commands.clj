(ns clipix.commands
  (:use [clipix.model :only (put-cell flood-fill)])
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
  ([t [x y] c]
    (L t x y c))
  ([t x y c]
    (let [c  (to-colour c)]
      (put-cell t x y c))))

(defn V
" Draw a vertical segment of colour C in column X between rows Y1 and Y2
  (inclusive).
"
 [t x y1 y2 c]
 (let [L-vert  (fn [t1 y]       ;; closure on x and c
                   (L t1 x y c))]
   (reduce L-vert t (range y1 (inc y2)))))

(defn H
" Draw a horizontal segment of colour C in row Y between columns X1 and X2
(inclusive).
"  
  [t x1 x2 y c]
   (let [L-hori  (fn [t1 x]       ;; closure on y and c
                   (L t1 x y c))]
   (reduce L-hori t (range x1 (inc x2)))))

(defn F
" Fill the region R with the colour C. R is defined as: Pixel (X,Y) belongs to R. Any other
pixel which is the same colour as (X,Y) and shares a common side with any pixel in R also belongs
to this region.
"
  [t x y c]
  (let [c  (to-colour c)]
    (flood-fill t x y c)))

(defn S
" Print and return the contents of the current image.
"  
  [t]
  (let [joined-rows (map str/join t)
        formatted   (str/join \newline joined-rows)]
    (println formatted)   ;;;; side-effect ;;;;
    formatted))