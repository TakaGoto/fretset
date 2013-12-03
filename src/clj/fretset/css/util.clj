(ns fretset.css.util
  (:require [garden.core :refer [css]]))

(def clearfix
  ["&"
   {:*zoom 1}
   ["&:before" "&:after"
    {:content "\"\""
     :display "table"}]
   ["&:after"
    {:clear "both"}]])
