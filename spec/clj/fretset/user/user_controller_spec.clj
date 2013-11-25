(ns fretset.user.user-controller-spec
  (:require [speclj.core :refer :all]
            [fretset.user.user-controller :refer :all]
            [joodo.spec-helpers.controller :refer [with-mock-rendering
                                                   with-routes do-get
                                                   rendered-template]]))

(describe "User Controller"
  (with-routes user-controller)
  (with-mock-rendering)

  (context "sign up page"
    (it "return a 200"
      (let [result (do-get "/signup")]
        (should= 200 (:status result)))
        (should= "user/signup" @rendered-template)
      )
    )
  )

