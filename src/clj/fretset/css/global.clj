(ns fretset.css.global
  (:require [garden.core :refer [css]]))

(defn global []
  (css

    ["*" {:margin "0" :padding "0"}]

    [:a {:text-decoration "none"
         :color "#8c9397"}]

    [:header {:border-bottom "1px solid #DDD"
              :background "#fff"
              :overflow "hidden"
              :margin-bottom "3em"
              :padding "0.5em 0"}]))