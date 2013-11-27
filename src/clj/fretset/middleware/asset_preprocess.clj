(ns fretset.middleware.asset-preprocess
  (:require [garden.core        :refer [css]]
            [fretset.css.form   :refer [form]]
            [fretset.css.header :refer [header]]
            [fretset.css.global :refer [global]]))

(def preprocess-css
  (memoize
    (fn [handler]
      (let [css (str (global) (header) (form))]
      (spit "resources/public/stylesheets/fretset.css" css)
      handler))))
