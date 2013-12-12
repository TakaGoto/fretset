(ns fretset.util.modal
  (:require-macros [hiccups.core :as h])
  (:require [hiccups.runtime :as hiccupsrt]
            [domina :as dom]
            [domina.css :as css]))

(defn hello [modal]
  (js/alert "hello thur"))
