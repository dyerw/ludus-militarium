(ns ludus-militarium.runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [ludus-militarium.util.test-tile]
            [ludus-militarium.test-behavior]))

(doo-tests 'ludus-militarium.util.test-tile
           'ludus-militarium.test-behavior)
