(ns ludus-militarium.render
  (:require [ludus-militarium.util.tile :as t]
            [ludus-militarium.config :as cf]))

(defmulti draw
          "Called when the entity needs to be drawn"
          #(:type %1))
(defmethod draw :default [entity player-color]
  [:fill {:color (if (:selected? entity) "yellow" player-color)}
   [:ellipse (merge (t/tile->center-pixel (:position entity) cf/tile-size)
                    {:width  cf/tile-size
                     :height cf/tile-size})]])

(defn render [state]
  [;; Draw a grid of tiles
   (mapv (fn [[x y]] [:fill {:color "green"}
                      [:rect {:x x :y y :width cf/tile-size :height cf/tile-size}]])
         (mapcat identity
                 (map (fn [y] (map (fn [x] [x y])
                                   (range 0 cf/game-width cf/tile-size)))
                      (range 0 cf/game-height cf/tile-size))))
   ;; Draw entities
   (mapv #(draw % (:color (get (:players state) (:owner %))))
         (:entities state))])
