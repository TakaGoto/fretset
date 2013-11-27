(ns fretset.user.user
  (:require [hyperion.api :refer [defentity]]
            [metis.core   :refer [defvalidator]]
            [digest       :refer [sha-256]]))

(def email-validation-regex
  #"(?i)[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")

(def salt "Fretset not Jetset!")

(defn- validate-email-format [user field options]
  (when-not
    (re-matches
      email-validation-regex (or (field user) ""))
    "Please input a correct email format"))

(defn- confirm-matching-password [user field options]
  (when-not (= (field user) (:password-confirmation user))
    "Password and password confirmation don't match"))

(defn ->digest [password]
  (sha-256 (str salt password)))

(defvalidator validate-user
  [[:first-name]            :presence {:message "Please input your first name"}]
  [[:last-name]              :presence {:message "Please input your last name"}]

  [[:email]                  :presence {:message "Please input your email"}]
  [:email :validate-email-format]

  [[:password]               :presence {:message "Please input your password"}]
  [[:password :password-confirmation] [:length {:greater-than-or-equal-to 6}]]
  [[:password-confirmation]  :presence {:message "Please input your password confirmation"}]
  [:password :confirm-matching-password])

(defentity User
  [first-name]
  [last-name]
  [email]
  [password :packer ->digest]
  [created-at]
  [updated-at])
