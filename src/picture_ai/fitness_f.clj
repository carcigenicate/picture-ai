(ns picture-ai.fitness-f
  (:require [helpers.general-helpers :as g]
            [picture-ai.image-helpers :as ih]
            [picture-ai.gene-parser :as gp]
            [picture-ai.gene :as ge])

  (:import [java.awt.image BufferedImage]
           [java.awt Color]))

(def new-img-bg-color (Color. 255 255 255))

(defn image-from-genes [img-width img-height genes]
  ; TODO: Create an image of the same size as the target, and draw the circle based on the genes.
  (let [img (BufferedImage. img-width img-height BufferedImage/TYPE_INT_RGB)
        g (.createGraphics img)]

    (ih/with-color g new-img-bg-color
      (.fillRect g 0 0 img-width img-height))

    (doseq [{x :x, y :y, r :radius, c :color} genes]
      (ih/with-color g c
        (ih/draw-circle g x y r)))

    (.dispose g)

    img))

(defn new-fitness-f-for [^BufferedImage target-img]
  (let [targ-width (.getWidth target-img)
        targ-height (.getHeight target-img)]

    (fn [genes]
      (let [g-img (image-from-genes targ-width targ-height genes)]
        (ih/abs-channel-diff-sum g-img target-img)))))