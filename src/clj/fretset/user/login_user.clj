(ns fretset.user.login-user
  (:require [hyperion.api       :refer [find-by-kind save]]
            [fretset.user.user  :refer [->digest]]
            [ring.util.response :refer [set-cookie redirect]]))

(defn- valid-password? [password user]
  (let [digested-password (->digest password)]
    (if (= digested-password (:password user))
      true false)))

(defn build-token [user]
  (str (:key user) ":" (:password user)))

(defn login [request]
  (let [password (get (:params request) "password")
        email (get (:params request) "email")
        user (first (find-by-kind :user :filters [:= :email email]))]
    (if (valid-password? password user)
      (assoc
        (set-cookie (redirect "/") :token (build-token user))
        :flash {:success {:login ["You have logged in!"]}})
      (assoc
        (redirect "login")
        :flash {:errors {:login ["Login failed"]}}))))
