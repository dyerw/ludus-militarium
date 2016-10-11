(ns ludus-militarium.test-behavior
  (:require [ludus-militarium.behavior :refer [on-selected]]
            [ludus-militarium.game :refer [Entity map->Entity]]
            [cljs.test :refer-macros [deftest is]]))

(def example-entity (map->Entity {:id "abc"
                                  :position {:x 0 :y 0}
                                  :current-health 10
                                  :movement 3
                                  :type :default
                                  :selected? false
                                  :active? true
                                  :owner 0}))

(deftest test-on-selected
  ;; Should select
  (is (= (assoc example-entity :selected? true)
         (on-selected example-entity)))
  ;; Shouldn't select
  (is (= (assoc example-entity :active? false)
         (on-selected (assoc example-entity :active? false)))))
