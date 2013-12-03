(ns fretset.css.flash-message
  (:require [garden.core        :refer [css]]
            [fretset.css.global :refer [gray]]))

(def flash-message
  (css

    [:div.flash-message {:color gray}
      [:ul {:list-style "none"}]]))
