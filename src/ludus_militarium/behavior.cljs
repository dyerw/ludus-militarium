(ns ludus-militarium.behavior)

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
