(ns ludus-militarium.events
  (:require [ludus-militarium.util.tile :as t]
            [ludus-militarium.config :as cf]
            [ludus-militarium.behavior :as b]
            [ludus-militarium.game :as g]))

(defn get-cursor-position [event]
  (let [canvas (.-target event)
        rect (.getBoundingClientRect canvas)
        x    (/ (- (.-clientX event) (.-left rect)) 2)
        y    (/ (- (.-clientY event) (.-top rect)) 2)]
    {:x x :y y}))

(defmulti handle-event #(.-type %))
(defmethod handle-event "mousedown" [event]
  (fn [state]
    (if (= "CANVAS" (.-nodeName (.-target event)))
      (-> event
          get-cursor-position
          (t/pixel->tile cf/tile-size)
          (g/position->entity state)
          b/on-selected
          (g/update-entity state))
      state)))

(defmethod handle-event "keydown" [event]
  identity)
