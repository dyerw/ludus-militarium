(ns ludus-militarium.ui.render
  (:require [ludus-militarium.ui.unit-details :refer [unit-details-view]]
            [rum.core :as rum]))

(defn mount-all [state]
  (rum/mount (unit-details-view state)
             (.getElementById js/document "unit-details-mount")))
