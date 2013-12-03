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

(defn- display-message [message]
  [:li
   (first (second message))
   [:br]])

(defn display-flash [flash-messages flash-key]
  (map
    #(display-message %)
    (flash-key flash-messages)))

(defn display-logout-login [request]
  (if (:value (get (:cookies request) "token"))
  [:li
   [:a {:href "/user/logout"} "Logout"]]
  [:li
   [:a {:href "/user/login"} "Login"]]))
