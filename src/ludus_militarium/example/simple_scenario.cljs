(ns ludus-militarium.example.simple-scenario)

(def example-scenario
  {:title "Simple Battle"
   :description "Just a few units having it out"
   :players 2
   :size {:height 20 :width 20}
   ;; Empty array is just a grass field
   :terrain []
   :weapon-types [:slashing :piercing :bludgeoning]
   :weapons [{:id :sword
              :description "Sword"
              :damage 5
              :accuracy 10
              :type :slashing
              :range 1}
             {:id :spear
              :description "Spear"
              :damage 3
              :accuracy 7
              :type :piercing
              :range 1}
             {:id :hammer
              :description "Hammer"
              :damage 8
              :accuracy 3
              :type :bludgeoning
              :range 1}
             {:id :bow
              :description "Bow"
              :damage 2
              :type :piercing
              :range 3}]
   :armor-types [:mail :plate :none]
   :armors [{:id :full-plate
             :description "Full Plate"
             :protection 5
             :type :plate}
            {:id :full-mail
             :description "Full Mail"
             :protection 3
             :type :mail}
            {:name :cloth
             :description "Cloth"
             :protection 0
             :type :none}]
   :weapon-armor-interactions [{:weapon :piercing
                                :armor :plate
                                :multiplier 1.5}
                               {:weapon :slashing
                                :armor :none
                                :multiplier 2.0}
                               {:weapon :slashing
                                :armor :mail
                                :multiplier 0.5}]
   :unit-types [{:id :knight
                 :description "Knight"
                 :weapon :sword
                 :armor :full-plate
                 :expertise 10
                 :movement 4
                 :health 10
                 :morale 10}
                {:id :peasant
                 :description "Peasant"
                 :weapon :spear
                 :armor :cloth
                 :movement 2
                 :expertise 3
                 :health 10
                 :morale 2}
                {:id :infantry
                 :description "Infantry"
                 :weapon :spear
                 :armor :mail
                 :movement 1
                 :expertise 6
                 :health 10
                 :morale 8}
                {:id :archer
                 :description "Archer"
                 :weapon :bow
                 :armor :cloth
                 :movement 1
                 :health 10
                 :morale 8}]
   :unit-positions [{:type :knight
                :position {:x 0 :y 0}
                :owner 0}
               {:type :knight
                :position {:x 3 :y 0}
                :owner 0}
               {:type :archer
                :position {:x 1 :y 0}
                :owner 0}
               {:type :peasant
                :position {:x 5 :y 10}
                :owner 1}
               {:type :infantry
                :position {:x 5 :y 9}
                :owner 1}
               {:type :infantry
                :position {:x 4 :y 9}
                :owner 1}]
   })
