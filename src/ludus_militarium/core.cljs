(ns ludus-militarium.core
  (:require [play-cljs.core :as p]
            [ludus-militarium.util.tile :as t]
            [ludus-militarium.render :as r]
            [ludus-militarium.config :as cf]
            [cljs.pprint :refer [pprint]]))

(enable-console-print!)
(defn dbg [x] (pprint x) x)

(def game (p/create-game cf/game-width cf/game-height))
(def state (atom {}))

(defrecord Entity [id position type selected? active?])
(defmulti create-default
          "Creates a default version of a given type"
          :type)
(defmethod create-default :default [entity]
  (->Entity (random-uuid) (:position entity) :basic false true))

;; Entity behaviors with default functions
;; implement these for a unit type to give it custom behavior


;; Event handling functions
(defmulti handle-event #(.-type %))
(defmethod handle-event "mousedown" [event]
  (fn [state]
    (println (js->clj event))
    state))

(defmethod handle-event "keydown" [event]
  (fn [state]
    state))

;; Create the screen
(def main-screen
  (reify p/Screen

    (on-show [this]
      (reset! state  {:entities [(create-default {:type :none :position {:x 0 :y 0}})]}))

    (on-hide [this])

    (on-render [this]
      (p/render game (r/render @state)))

    (on-event [this event]
      (swap! state (handle-event event)))))

;; Get everything rollin'
(doto game
  (p/stop)
  (p/start ["keydown" "mousedown"])
  (p/set-screen main-screen))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )
