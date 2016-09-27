(ns ludus-militarium.events
  (:require [ludus-militarium.util.tile :as t]
            [ludus-militarium.config :as cf]
            [ludus-militarium.behavior :as b]
            [ludus-militarium.game :as g]
            [cljs.pprint :refer [pprint]]))

(defn dbg [x] (pprint x) x)

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
      (let [selected-position (-> event
                                 get-cursor-position
                                 (t/pixel->tile cf/tile-size))
            selected-entity (g/position->entity selected-position state)
            owned? (= (:owner selected-entity) (:turn state))]
        (cond
          ;; Select new unit
          owned?
          (-> selected-entity
              b/on-selected
              (g/update-entity (g/unselect-all state)))
          ;; Move
          (and (nil? selected-entity) (b/can-move? (g/currently-selected-entity state) selected-position))
          (-> (g/currently-selected-entity state)
              (b/on-move selected-position)
              (g/update-entity state))
          ;; Attack TODO: Implement
          (not (nil? selected-entity))
          state
          :else state
          ))
      state)))

(defmethod handle-event "keydown" [event]
  identity)
