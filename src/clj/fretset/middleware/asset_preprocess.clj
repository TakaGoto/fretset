(ns fretset.middleware.asset-preprocess
  (:require [fretset.css.header :refer [header]]
            [garden.core :refer [css]]
            [fretset.css.global :refer [global]]))

(def preprocess-css
  (memoize
    (fn [handler]
      (let [css (str (global) (header))]
      (spit "resources/public/stylesheets/fretset.css" css)
      handler))))
