(ns picture-ai.color-test
  (:require [picture-ai.image-helpers :as ih])
  (:import [java.awt.image BufferedImage]
           [java.awt Color Graphics2D]))

(def test-img
  (BufferedImage. 1000 1000 BufferedImage/TYPE_INT_ARGB))

(def g
  (.createGraphics test-img))

(def test-color
  (Color. 50 50 255 175))

(ih/with-color g test-color
               (.fillOval g 0 0 500 500)
               (.fillOval g 300 300 500 500))

(ih/with-color g (Color. 0 0 0)
               (.fillOval g 400 400 10 10))
