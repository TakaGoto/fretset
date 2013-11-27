(ns fretset.css.header
  (:require [garden.core :refer [css]]
            [garden.stylesheet :refer [at-media]]
            [garden.units :refer [px]]
            [fretset.css.util :refer [clearfix]]))

(defn header []
  (css

    [:div.container
     clearfix
     {:max-width "960px"   :margin "0 auto"
      :position "relative" :width "90%"}]

    [:a.logo
     {:display "block"        :float "left"
      :overflow "hidden"      :padding "0.5em 0"}]

    [:nav {:overflow "hidden"
           :width "100%"}

     [:ul {:margin "0.5em 0 0 0"
           :overflow "hidden"}]

     [:li {:list-style "none"
           :border-top "1px solid #eee"
           :text-align "center"
           :margin-bottom "0"
           :line-height "2em"}]]

    (at-media {:screen true :min-width (px 640)}
              [:nav {:float "right"
                     :width "auto"
                     :height "auto"}
               [:ul
                :margin-top "0"]
               [:li {:float "left" :border "0"
                     :margin-left "1.5em"}
                ["&:first-child" {:margin-left "0"}]]])))
