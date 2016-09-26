(ns ludus-militarium.game
  "Game state functions and data"
  (:require [ludus-militarium.behavior :as b]))

(defrecord Game [players entities turn size])
(defrecord Player [id color])

(def player-colors ["red" "blue"])

(defn load-scenario [scenario]
  (->Game (mapv #(identity {:id % :color (get player-colors %)}) (range (:players scenario)))
          (mapv #(b/->Entity (random-uuid)
                             (:position %)
                             (:health %)
                             (:type %)
                             false
                             true
                             (:owner %))
                (:unit-positions scenario))
          0
          (:size scenario)))

(defn next-turn [game]
  (assoc game :turn []))

(defn position->entity [position game]
  (first (filter #(= position (:position %)) (:entities game))))

(defn update-entity [entity game]
  (let [game-without-entity (assoc game
                              :entities
                              (filter #(not= (:id entity) (:id %))))]
    (assoc game :entities (conj (:entities game) entity))))
