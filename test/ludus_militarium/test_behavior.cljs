(ns ludus-militarium.test-behavior
  (:require [ludus-militarium.behavior :refer [Entity on-selected map->Entity]]
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
