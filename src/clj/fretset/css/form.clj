(ns fretset.css.form
  (:require [garden.core      :refer [css]]
            [fretset.css.util :refer [clearfix gray]]))

(def form
  (css

    [:div.form-header {:text-align "center"}]

    [:div.form
     clearfix
     {:text-align "center" :padding "2em"
      :max-width "500px"   :margin-left "auto"
      :margin-right "auto"}

      [:input.button {:margin "1em"
                      :background-color "red"
                      :border "1px solid red" :color "white"
                      :padding "0.75em 2em"
                      :cursor "pointer"}]]

    [:div.form-field

      [:label {:float "left"      :width "100%"
               :text-align "left" :margin-top "0.5em"
               :margin-bottom "0.2em"}]

      [:input {:border (str "1px solid " gray) :line-height "1.5em"
               :padding "0.5em"         :width "100%"
               :background-color gray}]]

    [:div.first-column
     {:width "45%" :float "left"
      :margin "0"}]

    [:div.second-column
     {:width "45%" :float "right"
      :margin "0"}]))
