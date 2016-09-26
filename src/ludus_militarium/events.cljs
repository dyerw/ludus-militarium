(ns ludus-militarium.events)

(defmulti handle-event #(.-type %))
(defmethod handle-event "mousedown" [event]
  (fn [state]
    (println (js->clj event))
    state))

(defmethod handle-event "keydown" [event]
  (fn [state]
    state))
