(ns picture-ai.image-helpers
  (:import [java.awt.image BufferedImage]
           [java.awt Graphics2D Color]
           [java.io File]
           [javax.imageio ImageIO]))

; TODO: Fix GA to either handle multiple sections of different genes, or
; TODO:  accept a constructor to create a new gene instead of giving it the entire set.

(defn read-image ^BufferedImage [^String path]
  (ImageIO/read (File. path)))

(defn get-channels [color-int]
  (let [c (Color. color-int)]
    [(.getRed c)`
     (.getGreen c)
     (.getBlue c)]))

(defn save-image [^BufferedImage img ^String path]
  (let [parts (clojure.string/split path #"\.")
        ^String ext (last parts)]

    (ImageIO/write img ext (File. path))))

(defn get-color-at [^BufferedImage img x y]
  (let [c (.getRGB img x y)]
    (get-channels c)))

(defn absolute-channel-diff-sum-at [x y ^BufferedImage img1 ^BufferedImage img2]
  (let [abs #(if (pos? %) % (- %))
        cs1 (get-color-at img1 x y)
        cs2 (get-color-at img2 x y)]
    ; TODO: Untested
    (apply +
      (map abs
           (map - cs1 cs2)))))

