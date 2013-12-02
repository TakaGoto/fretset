(ns fretset.middleware.asset-preprocess
  (:require [fretset.css.form   :refer [form]]
            [fretset.css.global :refer [global]]
            [fretset.css.header :refer [header]]
            [fretset.css.table  :refer [table]]))

(def css-file-path "resources/public/stylesheets/fretset.css")

(def styles (str global header form table))

(def preprocess-css
  (memoize
    (fn [handler]
      (spit css-file-path styles)
      handler)))
