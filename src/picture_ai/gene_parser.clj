(ns picture-ai.gene-parser)

(defrecord Circle [position radius])

; TODO: Store [x y radius] triplets in the genes, or [i radius] pairs?

(defn split-into-circles [genes]
  (partition 2 genes))

(defn parse-split-into-circles [split-genes]
  (for [[x y r] split-genes]))