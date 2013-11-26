(ns fretset.user.user-controller-spec
  (:require [speclj.core :refer :all]
            [fretset.user.user-controller :refer :all]
            [hyperion.api :refer [find-by-kind]]
            [hyperion.dev.spec-helper :refer [with-memory-datastore]]
            [joodo.spec-helpers.controller :refer [with-mock-rendering
                                                   with-routes do-get do-post
                                                   rendered-template
                                                   should-redirect-to]]))

(describe "User Controller"
  (with-memory-datastore)
  (with-routes user-controller)
  (with-mock-rendering)

  (context "sign up"
    (it "return a 200"
      (let [result (do-get "/signup")]
        (should= 200 (:status result)))
        (should= "user/signup" @rendered-template))

    (it "creates an account through signup form"
      (let [params {:full-name "George" :email "george@fakeemail.com" :password "george1" :password-confirmation "george1"}
            result (do-post "/signup" :params params)
            new-user (first (find-by-kind :user))]
        (should-redirect-to result "/")
        (should= "George" (:full-name new-user))
        (should-not-be-nil (:key new-user))))))

