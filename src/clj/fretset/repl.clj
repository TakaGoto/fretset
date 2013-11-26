(ns fretset.repl
  "Useful in development to load environment"
  (:require [joodo.env :refer [*env* load-configurations]]
            [hyperion.api :refer [set-ds! new-datastore]]
            [hyperion.log :as log]))

(defn init []
  (log/debug!)
  (System/setProperty "joodo.env" (or (System/getenv "JOODO_ENV") "development"))
  (load-configurations)
  (set-ds! (new-datastore (:datastore *env*))))
