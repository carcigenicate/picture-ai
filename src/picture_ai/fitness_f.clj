(ns picture-ai.fitness-f
  (:require [picture-ai.image-helpers :as ih])

  (:import [java.awt.image BufferedImage]))

(defn- comparing-reduction [running-sum [pix1 pix2]])

(defn pixels-for-image [^BufferedImage img]
  (for [y (.getHeight img)
        x (.getWidth img)]
    [x y]))

(defn image-from-genes [genes])
  ; TODO: Create an image of the same size as the target, and draw the circle based on the genes.

(defn new-fitness-f-for [^BufferedImage target-img]
  (fn [genes]
    (reduce
      (fn [running-error [x y]])

      0
      (pixels-for-image target-img))))
