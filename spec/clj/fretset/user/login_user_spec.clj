(ns fretset.user.login-user-spec
  (:require [speclj.core :refer :all]
            [fretset.user.login-user :refer :all]
            [fretset.user.user-controller :refer :all]
            [hyperion.dev.spec-helper      :refer [with-memory-datastore]]
            [joodo.spec-helpers.controller :refer [with-routes do-get do-post]]))

(describe "user logins"
  (with-memory-datastore)
  (with user {:first-name "george" :last-name "tucker" :email "george@gmail.com"
              :password "george1" :password-confirmation "george1"})

  (it "lets a user login"
    (let [login-param {:email "josh@email.com" :password "josh"}]
      (should= true true)
      )
    )
  )
