(ns fretset.css.table
  (:require [garden.core :refer [css]]))

(def table
  (css

    [:table
     [:tr
      [:th {:padding "1em" :text-align "left"}]
      [:td {:padding "1em" :text-align "left"}]]]

    [:div.user-table {:margin "auto" :max-width "500px" :padding "2em"}]))
