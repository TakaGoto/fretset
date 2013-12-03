(ns fretset.css.global
  (:require [garden.core       :refer [css]]
            [garden.stylesheet :refer [at-media]]
            [garden.units      :refer [px]]
            [fretset.css.util  :refer [clearfix]]))

(def black "#0b0f11")
(def gray "#afafaf")
(def blue "#1D3846")

(def body-background "url('/images/blue_dark_background.jpg')")
(def header-background "url('/images/bg-header.png')")


(def global
  (css

    [:html {:background-image body-background}]

    ["*" {:margin "0" :padding "0"}]

    [:a {:text-decoration "none"
         :color gray :text-transform "uppercase"}]

    [:input {:font-family "adelle"
             :font-size "1em"}]

    [:div.container
     clearfix
     {:max-width "960px"   :margin "0 auto"
      :position "relative" :width "80%"}]

    [:body {:color gray
            :font-family "adelle"}]

    [:header {:color gray }]

    (at-media {:screen true :min-width (px 640)}
              [:header {}])
    ))
