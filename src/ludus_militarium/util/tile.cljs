(ns ludus-militarium.util.tile)

(defn pixel->tile [position tile-size]
  {:x (Math/floor (/ (:x position) tile-size))
   :y (Math/floor (/ (:y position) tile-size))})

(defn tile->center-pixel [position tile-size]
  (let [center-pixel-helper #(+ (* % tile-size) (Math/floor (/ tile-size 2)))]
    {:x (center-pixel-helper (:x position))
     :y (center-pixel-helper (:y position))}))

(defn distance [position-1 position-2]
  (Math/floor (Math/sqrt (+ (Math/pow (- (:x position-2) (:x position-1)) 2)
                            (Math/pow (- (:y position-2) (:y position-1)) 2)))))
