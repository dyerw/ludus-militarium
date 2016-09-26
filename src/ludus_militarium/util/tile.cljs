(ns ludus-militarium.util.tile)

(defn pixel->tile [position tile-size]
  {:x (Math/floor (/ (:x position) tile-size))
   :y (Math/floor (/ (:y position) tile-size))})

(defn tile->center-pixel [position tile-size]
  (let [center-pixel-helper #(+ (* % tile-size) (Math/floor (/ tile-size 2)))]
    {:x (center-pixel-helper (:x position))
     :y (center-pixel-helper (:y position))}))
