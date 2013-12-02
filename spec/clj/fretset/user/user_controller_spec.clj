(ns fretset.user.user-controller-spec
  (:require [speclj.core                   :refer :all]
            [fretset.user.user             :refer [user]]
            [fretset.user.user-controller  :refer :all]
            [hyperion.api                  :refer [find-by-kind save]]
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

  (with credentials {"email" "george@fakeemail.com"
                     "password" "george1"})

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
        (should= "user/users" @rendered-template))))

  (context "login"
    (context "GET login"
      (it "requests login page"
        (let [response (do-get "/login")]
          (should= 200 (:status response))))

      (it "renders the login page"
        (let [response (do-get "/login")]
          (should= "user/login" @rendered-template))))

    (context "POST login"
      (it "redirects to '/'"
        (save (user @valid-user))
        (let [response (do-post "/login" :params @credentials)]
          (should= 302 (:status response))
          (should-redirect-to response "/")))

      (it "redirects back to GET '/login' if login fails"
        (save (user @valid-user))
        (let [response (do-post "/login" :params (assoc @credentials "password" "wrongpassword"))]
          (should-redirect-to response "login"))))))

