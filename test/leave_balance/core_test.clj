(ns leave-balance.core-test
  (:require [clojure.test :refer :all]
            [leave-balance.core :as core]))

(def faux-weeks-left-good 2.0)
(def faux-weeks-left-bad 0)

(deftest test-new-leave-good
  (testing "New Leave Calculations with good data"
    (with-redefs [core/weeks-left faux-weeks-left-good]
      (are [func result] (= func result)
        ; new-leave [rate]
        (leave-balance.core/new-leave 2.0) 4.0
        (leave-balance.core/new-leave 6.0) 12.0))))

(deftest test-new-leave-bad
  (testing "New Leave Calculations with bad data"
    (with-redefs [core/weeks-left faux-weeks-left-bad]
      (are [func result] (= func result)
        ; new-leave [rate]
        (leave-balance.core/new-leave 2.0) 0.0
        (leave-balance.core/new-leave 6.0) 0.0))))

(deftest test-total-leave
  (testing "Total leave balance after new hours are accrued."
    (with-redefs [core/weeks-left faux-weeks-left-good]
      (are [func result] (= func result)
        ; total-leave [bal rate]
        (leave-balance.core/total-leave 10.0 2.0) 14.0
        (leave-balance.core/total-leave 10.0 6.0) 22.0))))

(deftest test-leave-lost
  (testing "Leave lost if none taken."
    (with-redefs [core/weeks-left faux-weeks-left-good]
      (are [func result] (= func result)
        ; leave-lost [cur-bal end-bal max]
        (leave-balance.core/leave-lost 10.0 14.0 10.0) 4.0
        (leave-balance.core/leave-lost 10.0 22.0 10.0) 12.0))))

(deftest test-rec-leave
  (testing "Recommended leave to take each week to avoid losing any."
    (with-redefs [core/weeks-left faux-weeks-left-good]
      (are [func result] (= func result)
        ; rec-leave [lost-leave]
        (leave-balance.core/rec-leave 4.0) 2.0 
        (leave-balance.core/rec-leave 12.0) 6.0))))