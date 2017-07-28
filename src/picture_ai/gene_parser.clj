(ns picture-ai.gene-parser)

(defrecord Circle [x y radius])

; TODO: Store [x y radius] triplets in the genes, or [i radius] pairs?

(defn split-into-parts [genes]
  (partition 3 genes))

(defn parse-split-into-circles [split-genes]
  (for [[x y r] split-genes]
    (->Circle x y r)))

(defn parse-genes [genes]
  (-> genes
      (split-into-parts)
      (parse-split-into-circles)))