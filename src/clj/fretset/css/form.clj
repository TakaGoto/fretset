(ns fretset.css.form
  (:require [garden.core      :refer [css]]
            [fretset.css.util :refer [clearfix]]))

(def form
  (css

    [:div.form-header {:text-align "center"}]

    [:div.user-registration-form
     clearfix
     {:text-align "center" :padding "2em"
      :max-width "500px"   :margin-left "auto"
      :margin-right "auto"}

      [:input.button {:margin "1em"}]]

    [:div.form-field

      [:label {:float "left"      :width "100%"
               :text-align "left" :margin-top "0.5em"
               :margin-bottom "0.2em"}]

      [:input {:border "1px solid #DDD" :line-height "1.5em"
               :padding "0.5em"         :width "100%"}]]

    [:div.first-column
     {:width "45%" :float "left"
      :margin "0"}]

    [:div.second-column
     {:width "45%" :float "right"
      :margin "0"}]))
