(defproject leave_balance "0.1.0-SNAPSHOT"
  
  ;;; Project Metadata
  :description "Calculate end of year leave balance."
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  
  ;;; Dependencies, Plugins
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [clojure.java-time "0.3.2"]
                 [environ "1.2.0"]]
  
  ;;; Profiles
  :profiles {:uberjar {:aot :all}}

  ;;; Running Project Code
  :main ^:skip-aot leave-balance.core)
