(ns fretset.util.view-helpers
  (:require [joodo.views              :refer [render-partial *view-context*]]
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

(defn- display-message [message]
  [:li
   (first (second message))
   [:br]])

(defn display-flash [flash-messages flash-key]
  (map
    #(display-message %)
    (flash-key flash-messages)))
