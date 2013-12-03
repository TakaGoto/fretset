(ns fretset.css.header
  (:require [garden.core         :refer [css]]
            [garden.stylesheet   :refer [at-media]]
            [garden.units        :refer [px]]
            [fretset.css.util    :refer [clearfix]]
            [fretset.css.global  :refer [black]]))

(def header
  (css

    [:header {:overflow "hidden"
              :margin-bottom "3em"
              :padding "0.5em 0"}]

    [:a.logo
     {:display "block"        :float "left"
      :overflow "hidden"      :padding "0.5em 0"}]

    [:nav {:overflow "hidden"
           :width "100%"}

     [:ul {:overflow "hidden"}]

     [:li {:border-top (str "1px solid " black)
           :list-style "none"
           :line-height "2.25em"
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
