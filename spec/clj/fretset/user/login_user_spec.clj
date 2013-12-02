(ns fretset.user.login-user-spec
  (:require [speclj.core                   :refer :all]
            [fretset.user.user             :refer :all]
            [fretset.user.login-user       :refer :all]
            [fretset.user.user-controller  :refer :all]
            [hyperion.api                  :refer [save find-by-kind]]
            [hyperion.dev.spec-helper      :refer [with-memory-datastore]]
            [joodo.spec-helpers.controller :refer [with-routes do-get do-post
                                                   should-redirect-to]]))

(def mock-request {:params {"email" "george@gmail.com" "password" "george1"}})

(describe "user login"
  (with-memory-datastore)

  (with new-user {:first-name "george" :last-name "tucker"
                  :email "george@gmail.com" :password"george1"
                  :password-confirmation "george1"})

  (it "lets a user login"
    (let [george (save (user @new-user))
          response (login mock-request)]
      (should= (build-token george) (:value (:token (:cookies response))))))

  (it "rejects failed password"
    (let [george (save (user @new-user))
          response (login (assoc mock-request :params {:password "wrongpassword"}))]
      (should= "Login failed" (first (:errors (:flash response))))))

  (it "redirects to '/' if login is successful"
    (let [george (save (user @new-user))
          response (login mock-request)]
      (should-redirect-to response "/"))))
