(ns ludus-militarium.game
  "Game state functions and data"
  (:require [ludus-militarium.behavior :as b]))

(defrecord Game [players entities turn size])
(defrecord Player [id color])

(def player-colors ["red" "blue" "purple" "orange"])

(defn unit->movement [type unit-types]
  (:movement (first (filter #(= (:id %) type) unit-types))))

(defn load-scenario [scenario]
  (->Game (mapv #(identity {:id % :color (get player-colors %)}) (range (:players scenario)))
          (mapv #(b/map->Entity {:id             (random-uuid)
                                 :position       (:position %)
                                 :movement       (unit->movement (:type %)
                                                                 (:unit-types scenario))
                                 :current-health (:health %)
                                 :type           (:type %)
                                 :selected?      false
                                 :active?         true
                                 :owner          (:owner %)})
                (:unit-positions scenario))
          0
          (:size scenario)))

(defn next-turn [game]
  (assoc game :turn
              (mod (inc (:turn game))
                   (count (:players game)))))

(defn position->entity [position game]
  (first (filter #(= position (:position %)) (:entities game))))

(defn update-entity [entity game]
  (let [game-without-entity (assoc game
                              :entities
                              (filter #(not= (:id entity) (:id %))
                                      (:entities game)))]
    (assoc game :entities (conj (:entities game-without-entity) entity))))

(defn currently-selected-entity [game]
  (first (filter :selected? (:entities game))))

(defn some-selected? [game]
  (some :selected? (:entities game)))

(defn unselect-all [game]
  (assoc game
    :entities
    (mapv #(assoc % :selected? false)
          (:entities game))))
