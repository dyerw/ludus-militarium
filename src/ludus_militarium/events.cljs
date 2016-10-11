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

(defn on-canvas? [event]
  (= "CANVAS" (.-nodeName (.-target event))))

(defn event->tile [event]
  (-> event
      get-cursor-position
      (t/pixel->tile cf/tile-size)))

(defn event->entity [event state]
  (-> event
      event->tile
      (g/position->entity state)))

(defmulti handle-event #(.-type %))
(defmethod handle-event "mousedown" [event]
  (fn [state]
    (if (on-canvas? event)
      (let [selected-position (event->tile event)
            selected-entity   (event->entity event state)
            owned?            (= (:owner selected-entity) (:turn state))]
        (cond
          ;; Select new unit
          owned?
          (-> selected-entity
              b/on-selected
              (g/update-entity (g/unselect-all state)))
          ;; Move
          (and (nil? selected-entity)
               (b/can-move? (g/currently-selected-entity state) selected-position))
          (-> (g/currently-selected-entity state)
              (b/on-move selected-position)
              (g/update-entity state))
          ;; Attack TODO: Implement
          (not (nil? selected-entity))
          state
          :else state
          ))
      ;; Off canvas clicks change turn for now
      (let [next-turn-game (g/next-turn (g/unselect-all state))]
        (println "It's now Player " (:turn next-turn-game) "'s turn")
        next-turn-game))))

(defmethod handle-event "keydown" [event]
  identity)

(defmethod handle-event "mousemove" [event]
  (fn [state]
    (if (on-canvas? event)
      (assoc state
        :hovered-entity
        (event->entity event state))
      state)))
