(ns picture-ai.main
  (:require [quil.core :as q]
            [quil.middleware :as m]

            [ai-retry.genetic-algorithm.genetic-algorithm :as ga]
            [ai-retry.genetic-algorithm.population :as gp]
            [ai-retry.genetic-algorithm.settings :as gs]
            [ai-retry.genetic-algorithm.helpers.gene-helpers]

            [picture-ai.fitness-f :as ff]
            [picture-ai.image-helpers :as ih]
            [picture-ai.gene :as ge]

            [helpers.general-helpers :as g])

  (:import [java.awt.image BufferedImage]
           [processing.core PImage])

  (:gen-class))

(def global-rand-gen (g/new-rand-gen 99))

; - - - - - - - - - - Settings - - - - - - - - - -

(def ^:const target-img (ih/read-image "anx.jpg"))
(def target-width (.getWidth target-img))
(def target-height (.getHeight target-img))

(def min-circle-radius (* target-width 0.01))
(def max-circle-radius (* target-width 0.2))

(def width target-width)
(def height target-height)

(def new-gene-f
  (ge/new-gene-f target-width target-height
                 min-circle-radius max-circle-radius
                 global-rand-gen))

(def problem-settings
  (gs/->Problem-Settings (ff/new-fitness-f-for target-img)
                         new-gene-f
                         gp/std-error-comparator))

(def standard-settings
  (gs/map->Standard-Settings
    {:elite-perc 0.3
     :lesser-perc 0.3
     :keep-perc 0.4
     :mut-chance 0.05
     :pop-size 20}))

(def seq-length 10)

(def ga-settings
  (gs/->Settings standard-settings problem-settings))

(def save-path "drawn-image.jpg")

(def max-gens 100)

; - - - - - - - - - - End Settings - - - - - - - - - -

(defrecord State [pop iters])

(defn new-pop [rand-gen]
  (gp/new-random-population new-gene-f seq-length
                            (:pop-size standard-settings)
                            rand-gen))

(defn draw-buff-img [^BufferedImage img]
  (q/image (PImage. img)
           0 0))

(defn save-best [pop]
  (let [best (first pop)
        img (ff/image-from-genes width height best)]
    (ih/save-image img save-path)))

(defn setup-state []
  (let [pop (new-pop global-rand-gen)]
    (->State pop 0)))

(defn update-state [state]
  (let [{pop :pop i :iters} state]
    (if (> i max-gens)
        (do
          (save-best pop)
          (q/exit))

        (let [advanced-pop
              (gp/advance-population pop ga-settings
                                     (:fit-err-comp problem-settings) global-rand-gen)]
          (println "Finished" i)

          (assoc state :pop advanced-pop
                       :iters (inc i))))))

(defn -main [& args]
  (q/defsketch Picture-GA
               :size [width height]
               :setup setup-state
               :update update-state

               :middleware [m/fun-mode]))

