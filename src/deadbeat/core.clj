(ns deadbeat.core
  (:require
    [deadbeat.slack.rtm :as rtm]
    [environ.core :as environ]
    [clojure.core.async :as a :refer [go chan <! >!]]))

(def hardcode-token (environ/env :api-token))

(defn print-message [{:keys [user text]}]
  (println (:name user) ": " text))

(defn -main [& args]
  (let [rtm-connection (rtm/connect hardcode-token)]
    (println "Connected to RTM!")
    (rtm/on-message rtm-connection "general" print-message)
    (println (read-line))))