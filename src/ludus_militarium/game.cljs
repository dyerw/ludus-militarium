(ns ludus-militarium.game
  "Game state functions and data"
  (:require [cljs.pprint :as pp]))

(defn dbg [x] (do (pp/pprint x) x))

(defrecord Entity [id position current-health movement type selected? active? owner])
(defrecord Game [players entities turn size scenario])
(defrecord Player [id color])

(def player-colors ["red" "blue" "purple" "orange"])

(defn unit->movement [type unit-types]
  (:movement (first (filter #(= (:id %) type) unit-types))))

(defn load-scenario [scenario]
  (->Game (mapv #(identity {:id % :color (get player-colors %)}) (range (:players scenario)))
          (mapv #(map->Entity {:id             (random-uuid)
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
          (:size scenario)
          scenario))

(defn update-entity [entity game]
  (let [game-without-entity (assoc game
                              :entities
                              (filter #(not= (:id entity) (:id %))
                                      (:entities game)))]
    (assoc game :entities (conj (:entities game-without-entity) entity))))

(defn next-turn [game]
  (let [next-player                      (mod (inc (:turn game))
                                              (count (:players game)))
        next-players-units               (filter #(= (:owner %) next-player)
                                                 (:entities game))
        next-players-units-with-movement (map #(assoc % :movement (unit->movement (:type %) (:unit-types (:scenario game))))
                                              next-players-units)]
    (-> game
        (assoc :turn next-player)
        (#(reduce (fn [g e] (update-entity e g)) % next-players-units-with-movement)))))

(defn position->entity [position game]
  (first (filter #(= position (:position %)) (:entities game))))

(defn currently-selected-entity [game]
  (first (filter :selected? (:entities game))))

(defn some-selected? [game]
  (some :selected? (:entities game)))

(defn unselect-all [game]
  (assoc game
    :entities
    (mapv #(assoc % :selected? false)
          (:entities game))))
