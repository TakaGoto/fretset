(ns fretset.util.view-helpers
  (:require [joodo.views              :refer [render-partial *view-context*]]
            [joodo.middleware.request :refer [*request*]]
            [hiccup.page              :refer :all]
            [hiccup.form              :refer :all]))

(defn- display-user [user]
  [:tr
   [:td (:first-name user)]
   [:td (:last-name user)]
   [:td (:email user)]])

(defn list-users [users]
  (map
    #(display-user %)
    users))
