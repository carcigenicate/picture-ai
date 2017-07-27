(ns picture-ai.main
  (:require [quil.core :as q])


  (:gen-class))

(def width 1000)
(def height 1000)

(defn to-color [n]
  [(q/red n)
   (q/green n)
   (q/blue n)])

(defn setup-state []
  (q/with-fill [200 0 0]
    (q/rect 100 100 400 500))

  (clojure.pprint/pprint (to-color (q/get-pixel 50 50)))
  (clojure.pprint/pprint (to-color (q/get-pixel 50 100))) ; TODO: Test half-opaque polygon layering
  (clojure.pprint/pprint (to-color (q/get-pixel 101 101))))


(defn -main [& args]
  (q/defsketch Pic-Test
               :size [width height]
               :setup setup-state))

