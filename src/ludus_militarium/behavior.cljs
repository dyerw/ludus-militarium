(ns ludus-militarium.behavior)

(defrecord Entity [id position current-health type selected? active? owner])

(defmulti onSelected
          "Called when the unit is selected"
          :type)
(defmethod onSelected :default [entity]
  (if (:active? entity)
    (assoc entity :selected? true)
    entity))

(defmulti onTurnStart
          "Called when the turn starts"
          :type)
(defmethod onTurnStart :default [entity]
  (assoc entity :active true))

(defmulti onMove
          "Called when user clicks empty tile when a unit is selected"
          #(:type %1))
(defmethod onMove :default [entity position]
  (assoc entity :position position))
