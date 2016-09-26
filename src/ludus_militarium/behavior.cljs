(ns ludus-militarium.behavior)

(defrecord Entity [id position current-health type selected? active? owner])

(defmulti on-selected
          "Called when the unit is selected"
          :type)
(defmethod on-selected :default [entity]
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
