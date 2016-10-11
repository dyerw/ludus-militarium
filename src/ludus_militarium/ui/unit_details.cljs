(ns ludus-militarium.ui.unit-details
  (:require [rum.core :as rum]))

(rum/defc unit-details-view < rum/reactive [state]
  (let [entity (:hovered-entity (rum/react state))]
    (if entity
      [:#unit-details
       [:div (name (:type entity))]
       [:div (str "Movement: " (:movement entity))]]
      [:#unit-details "No unit selected"])))
