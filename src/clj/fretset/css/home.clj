(ns fretset.css.home
  (:require [garden.core :refer [css]]))

(defn precompile []
  (css
    [:body {:color "red"}]))
