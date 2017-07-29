(ns picture-ai.gene
  (:require [helpers.general-helpers :as g])

  (:import [java.awt Color]))

(defrecord Gene [x y radius color])

(defn random-color-channel ^long [rand-gen]
  (g/random-int 0 256 rand-gen))

(defn random-color [rand-gen]
  (Color. (random-color-channel rand-gen)
          (random-color-channel rand-gen)
          (random-color-channel rand-gen)))

(defn new-gene-f [width height min-radius max-radius rand-gen]
  #(->Gene (g/random-int 0 width rand-gen)
           (g/random-int 0 height rand-gen)
           (g/random-int min-radius max-radius rand-gen)
           (random-color rand-gen)))