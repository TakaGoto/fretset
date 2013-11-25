(ns fretset.user.user
  (:require [hyperion.api :refer [defentity]]
            [metis.core :refer [defvalidator]]))

(def email-regex
  #"(?i)[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")

(defn- validate-email-format [user field options]
  (when-not
    (re-matches
      email-regex (or (field user) ""))
    "Please input a correct email format"))

(defn- confirm-matching-password [user field options]
  (when-not (= (field user) (:password-confirmation user))
    "Password and password confirmation don't match"))

(defvalidator validate-user
  [[:full-name]              :presence {:message "Please input your full name"}]

  [[:email]                  :presence {:message "Please input your email"}]
  [:email :validate-email-format]

  [[:password]               :presence {:message "Please input your password"}]
  [[:password :password-confirmation] [:length {:greater-than-or-equal-to 6}]]
  [[:password-confirmation]  :presence {:message "Please input your password confirmation"}]
  [:password :confirm-matching-password])

(defentity User
  [full-name]
  [email]
  [password]
  [created-at]
  [updated-at])
