(ns ludus-militarium.behavior
  (:require [ludus-militarium.util.tile :as t]
            [cljs.pprint :refer [pprint]]))

(defrecord Entity [id position current-health movement type selected? active? owner])

(defn dbg [x] (pprint x) x)
(defmulti on-selected
          "Called when the unit is selected"
          :type)
(defmethod on-selected :default [entity]
  (println (str "Selected Player " (:owner entity) "'s " (:type entity)))
  (pprint entity)
  (if (:active? entity)
    (assoc entity :selected? true)
    entity))

(defmulti on-turn-start
          "Called when the turn starts"
          :type)
(defmethod on-turn-start :default [entity]
  (assoc entity :active true))

(defmulti on-move
          "Called when user clicks empty tile when a unit is selected"
          #(:type %1))
(defmethod on-move :default [entity position]
  (assoc entity :position position))

(defmulti can-move? #(:type %1))
(defmethod can-move? :default [entity position]
  (>= (:movement entity) (dbg (t/distance position (:position entity)))))
