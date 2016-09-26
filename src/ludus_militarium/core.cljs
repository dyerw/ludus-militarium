(ns ludus-militarium.core
  (:require [play-cljs.core :as p]
            [ludus-militarium.game :as g]
            [ludus-militarium.render :as r]
            [ludus-militarium.config :as cf]
            [ludus-militarium.events :as e]
            [ludus-militarium.example.simple-scenario :refer [example-scenario]]
            [cljs.pprint :refer [pprint]]))

(enable-console-print!)
(defn dbg [x] (pprint x) x)

(def game (p/create-game cf/game-width cf/game-height))
(def state (atom {}))

;; Create the screen
(def main-screen
  (reify p/Screen

    (on-show [this]
      (reset! state (g/load-scenario example-scenario)))

    (on-hide [this])

    (on-render [this]
      (p/render game (r/render @state)))

    (on-event [this event]
      (swap! state (e/handle-event event)))))

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
