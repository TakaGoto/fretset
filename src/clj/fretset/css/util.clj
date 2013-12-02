(ns fretset.css.util
  (:require [garden.core :refer [css]]))

(def black "#0b0f11")

(def gray "#afafaf")

(def clearfix
  ["&"
   {:*zoom 1}
   ["&:before" "&:after"
    {:content "\"\""
     :display "table"}]
   ["&:after"
    {:clear "both"}]])
