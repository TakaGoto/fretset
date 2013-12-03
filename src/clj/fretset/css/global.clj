(ns fretset.css.global
  (:require [garden.core     :refer [css]]
            [fretset.css.util :refer [clearfix black
                                      header-background
                                      gray body-background]]))

(def global
  (css

    ["*" {:margin "0" :padding "0"}]

    [:a {:text-decoration "none"
         :color black :text-transform "uppercase"}]

    [:div.container
     clearfix
     {:max-width "960px"   :margin "0 auto"
      :position "relative" :width "80%"}]

    [:body {:background-color black :color gray
            :background-image body-background
            :font-family "proxima-nova"}]

    [:header {:background-image header-background}]))

