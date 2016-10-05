(ns ludus-militarium.util.test-tile
  (:require [ludus-militarium.util.tile :refer [pixel->tile tile->center-pixel distance]]
            [cljs.test :refer-macros [deftest is]]))

;; Example Tests
(deftest test-pixel->tile
  (is (= {:x 0 :y 0} (pixel->tile {:x 0 :y 0} 10)))
  (is (= {:x 1 :y 1} (pixel->tile {:x 11 :y 11} 10))))

(deftest test-tile->center-pixel
  (is (= {:x 5 :y 5} (tile->center-pixel {:x 0 :y 0} 10)))
  (is (= {:x 7 :y 2} (tile->center-pixel {:x 1 :y 0} 5))))

(deftest test-distance
  (is (= 1 (distance {:x 0 :y 0} {:x 1 :y 1})))
  (is (= 3 (distance {:x 0 :y 1} {:x 0 :y 4}))))

