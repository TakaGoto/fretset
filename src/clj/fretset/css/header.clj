(ns fretset.css.header
  (:require [garden.core       :refer [css]]
            [garden.stylesheet :refer [at-media]]
            [garden.units      :refer [px]]
            [fretset.css.util  :refer [clearfix]]))

(def header
  (css

    [:header {:border-bottom "1px solid #DDD"
              :background "#fff"
              :overflow "hidden"
              :margin-bottom "3em"
              :padding "0.5em 0"}]

    [:a.logo
     {:display "block"        :float "left"
      :overflow "hidden"      :padding "0.5em 0"}]

    [:nav {:overflow "hidden"
           :width "100%"}

     [:ul {:overflow "hidden"}]

     [:li {:border-top "1px solid #eee"
           :list-style "none"
           :line-height "2em"
           :margin-bottom "0"
           :text-align "center"}]]

    (at-media {:screen true :min-width (px 640)}
              [:nav {:float "right"
                     :height "auto"
                     :width "auto"}
               [:ul
                :margin-top "0"]
               [:li {:border "0" :float "left" :margin-left "1.5em"}
                ["&:first-child" {:margin-left "0"}]]])))
