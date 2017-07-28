(ns picture-ai.fitness-f
  (:require [helpers.general-helpers :as g]
            [picture-ai.image-helpers :as ih]
            [picture-ai.gene-parser :as gp]
            [picture-ai.gene :as ge])

  (:import [java.awt.image BufferedImage]))

(defn- comparing-reduction [running-sum [pix1 pix2]])

(defn pixels-for-image [^BufferedImage img]
  (for [y (.getHeight img)
        x (.getWidth img)]
    [x y]))

(defn image-from-genes [img-width img-height genes]
  ; TODO: Create an image of the same size as the target, and draw the circle based on the genes.
  (let [img (BufferedImage. img-width img-height BufferedImage/TYPE_INT_ARGB)
        g (.createGraphics img)]

    (doseq [{x :x, y :y, r :radius, c :color} genes]
      (ih/with-color g c
        (ih/draw-circle g x y r)))

    img))

(defn new-fitness-f-for [^BufferedImage target-img]
  (fn [genes]
    (reduce
      (fn [running-error [x y]])

      0
      (pixels-for-image target-img))))
