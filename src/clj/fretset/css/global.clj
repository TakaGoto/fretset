(ns fretset.css.global
  (:require [garden.core     :refer [css]]
            [fretset.css.util :refer [clearfix]]))

(def black "#0b0f11")
(def gray "#afafaf")
(def blue "#1D3846")

(def body-background "url('/images/blue_dark_background.jpg')")
(def header-background "url('/images/bg-header.png')")


(def global
  (css

    ["*" {:margin "0" :padding "0"}]

    [:a {:text-decoration "none"
         :color blue :text-transform "uppercase"}]

    [:div.container
     clearfix
     {:max-width "960px"   :margin "0 auto"
      :position "relative" :width "80%"}]

    [:body {:background-color black :color gray
            :background-image body-background
            :font-family "adelle"}]

    [:header {:background-image header-background
              :color gray :font-weight "100"}]))

