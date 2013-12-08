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

(describe "Login"
  (with-memory-datastore)

  (with new-user {:first-name "george" :last-name "tucker"
                  :email "george@gmail.com" :password"george1"
                  :password-confirmation "george1"})

  (context "user login"
    (it "lets a user login"
      (let [george (save (user @new-user))
            response (login mock-request)]
        (should= (build-token george) (:value (:token (:cookies response))))))

    (it "sends a message that user has logged in"
      (let [george (save (user @new-user))
            response (login mock-request)]
        (should= "You have logged in!" (first (:login (:success (:flash response)))))))

    (it "rejects failed password"
      (let [george (save (user @new-user))
            response (login (assoc mock-request :params {:password "wrongpassword"}))]
        (should= "*Login failed" (first (:login (:errors (:flash response)))))))

    (it "sends back email address if password fails"
      (let [george (save (user @new-user))
            response (login (assoc mock-request :params {"password" "wrongpassword"
                                                         "email" "george@gmail.com"}))]
        (should= "george@gmail.com" (first (:email (:user (:flash response)))))))

    (it "redirects to '/' if login is successful"
      (let [george (save (user @new-user))
            response (login mock-request)]
        (should-redirect-to response "/"))))

  (context "user logout"
    (with response (logout {:cookies {:token {:value "token"}}}))

    (it "sets expiration on token when user logs out"
      (should= 0 (:max-age (:token (:cookies @response)))))

    (it "sets the value of token to nil"
      (should= "expired" (:value (:token (:cookies @response)))))

    (it "sets the path to root"
      (should= "/" (:path (:token (:cookies @response)))))))
