(ns leave-balance.core
  (:require [java-time]
            [environ.core :refer [env]])
  (:gen-class))

;; date now/end of year
(def now (java-time/local-date))
(def cur-year (java-time/as now :year))
(def end-of-year (java-time/local-date cur-year 12 31))

;; days/weeks left before the end of the year
(def days-left (java-time/time-between now end-of-year :days))
(def weeks-left (/ days-left 7.0))

(defn leave-info
  "Get a map of leave information from env vars or use defaults."
  []
  (let [max-bal (Integer. (or (env :leave-max) 300))
        accrual-rate (Float. (or (env :leave-rate) 4.0))
        current-bal (Float. (or (env :leave-bal) 250.0))]
    {:max max-bal
     :rate accrual-rate
     :bal current-bal}))

(defn new-leave
  "Determine the new leave hours accrued."
  [rate]
  (if (< weeks-left 0)
    0.0
    (* weeks-left rate)))

(defn total-leave
  "Total leave balance after new hours are accrued."
  [bal rate]
  (+ bal (new-leave rate)))

(defn leave-lost
  "Calculate how much leave will be lost if none is taken."
  [cur-bal end-bal max]
  (if (> end-bal cur-bal) (- end-bal max) 0))

(defn rec-leave
  "Recommended leave to take each week to avoid losing any."
  [lost-leave]
  (/ lost-leave weeks-left))

(defn cur-bal-info
  "Display current leave balance information."
  [myleave]
  (println "Current Leave Balance Info")
  (println "-> Today is:" (java-time/format "YYYY-MM-dd" now))
  (println "-> Current balance:" (:bal myleave))
  (println "-> Accrual rate (hours gained a week):" (:rate myleave))
  (printf "-> Weeks left to accrue leave: %.2f\n" weeks-left))

(defn end-year-info
  "Display end of year leave balance information"
  [myleave]
  (let [end-bal (total-leave (:bal myleave) (:rate myleave))
        lost-leave (leave-lost (:bal myleave) end-bal (:max myleave))]
  (println "\nEnd of Year Leave Balance Info")
  (printf "-> Est End of Year Total leave: %.2f\n" end-bal)
  (println "-> Max leave carry over:" (:max myleave))
  (printf "-> Leave lost (if none taken): %.2f\n" lost-leave)
  (printf "-> Take this many hours of leave a week to avoid losing any: %.2f\n"
          (rec-leave lost-leave))))

(defn -main
  "Estimate the end of year leave balance."
  []
  (let [myleave (leave-info)]
    (cur-bal-info myleave)
    (end-year-info myleave)
    (flush)))