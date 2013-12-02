(ns fretset.css.global
  (:require [garden.core     :refer [css]]
            [fretset.css.util :refer [clearfix]]))

(def global
  (css

    ["*" {:margin "0" :padding "0"}]

    [:a {:text-decoration "none"
         :color "#8c9397"}]

    [:div.container
     clearfix
     {:max-width "960px"   :margin "0 auto"
      :position "relative" :width "80%"}]))
