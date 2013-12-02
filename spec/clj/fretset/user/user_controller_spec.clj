(ns fretset.user.user-controller-spec
  (:require [speclj.core                   :refer :all]
            [fretset.user.user-controller  :refer :all]
            [hyperion.api                  :refer [find-by-kind]]
            [hyperion.dev.spec-helper      :refer [with-memory-datastore]]
            [joodo.spec-helpers.controller :refer [with-mock-rendering
                                                   with-routes do-get do-post
                                                   rendered-template
                                                   should-redirect-to]]))

(describe "User Controller"
  (with-memory-datastore)
  (with-routes user-controller)
  (with-mock-rendering)

  (with valid-user
    {:first-name "George"
     :last-name "Tucker"
     :email "george@fakeemail.com"
     :password "george1" :password-confirmation "george1"})

  (context "sign up"
    (it "return a 200"
      (let [response (do-get "/signup")]
        (should= 200 (:status response)))
        (should= "user/signup" @rendered-template))

    (it "creates an account through signup form"
      (let [params @valid-user
            response (do-post "/signup" :params params)
            new-user (first (find-by-kind :user))]
        (should-redirect-to response "/")
        (should= "George" (:first-name new-user))
        (should-not-be-nil (:key new-user))))

    (it "returns a success flash message when account has been created"
      (let [params @valid-user
            response (do-post "/signup" :params params)]
        (should (:success (:flash response)))))

    (it "does not create an account if information is incorrect"
      (let [response (do-post "/signup" :params (dissoc @valid-user :email))
            new-user (find-by-kind :user)]
        (should= 0 (count new-user))))

    (it "returns with a flash error response when account information is invalid"
      (let [response (do-post "/signup" :params (dissoc @valid-user :email))]
        (should-redirect-to response "/user/signup")
        (should (:errors (:flash response))))))

  (context "list all users"
    (it "displays all the users"
      (let [response (do-get "/users")]
        (should= 200 (:status response))
        (should= "user/users" @rendered-template)
        ))))

