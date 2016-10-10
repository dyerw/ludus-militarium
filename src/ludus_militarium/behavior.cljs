(ns ludus-militarium.behavior
  (:require [ludus-militarium.util.tile :as t]
            [ludus-militarium.game :as g]
            [cljs.pprint :refer [pprint]]))



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
(defmethod on-turn-start :default [entity game]
  (-> entity
      dbg
      (assoc :movement (g/unit->movement (:type entity) (:unit-types game)))
      (assoc :active? true)))

(defmulti on-move
          "Called when user clicks empty tile when a unit is selected"
          #(:type %1))
(defmethod on-move :default [entity position]
  (-> entity
      (assoc :position position)
      (assoc :movement  (- (:movement entity) (t/distance position (:position entity))))))

(defmulti can-move? #(:type %1))
(defmethod can-move? :default [entity position]
  (>= (:movement entity) (t/distance position (:position entity))))
