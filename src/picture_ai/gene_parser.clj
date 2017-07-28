(ns picture-ai.gene-parser)

(defrecord Circle [position radius])

(defn split-into-circles [genes]
  (partition 2 genes))

(defn parse-split-into-circles [split-genes]
  (for [[]]))