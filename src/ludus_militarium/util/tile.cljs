(ns ludus-militarium.util.tile)

(defn pixel->tile [x y tile-size]
  {:x (Math/floor (/ tile-size x))
   :y (Math/floor (/ tile-size y))})

(defn tile->center-pixel [position tile-size]
  (let [center-pixel-helper #(+ (* % tile-size) (Math/floor (/ tile-size 2)))]
    {:x (center-pixel-helper (:x position))
     :y (center-pixel-helper (:y position))}))
