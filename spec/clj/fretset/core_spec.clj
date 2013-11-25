(ns fretset.core-spec
  (:require [speclj.core :refer :all]
            [fretset.core :refer :all]
            [joodo.spec-helpers.controller :refer [do-get with-routes
                                                   with-mock-rendering]]
            ))

(describe "route"
  (with-routes app-routes)
  (with-mock-rendering)


  (describe "home page"
    (it "returns a 200 for '/'"
      (let [result (do-get "/")]
        (should= 200 (:status result))))

    (it "returns not found for unknown routes"
      (let [result (do-get "/blah")]
        (should= 404 (:status result))))))
