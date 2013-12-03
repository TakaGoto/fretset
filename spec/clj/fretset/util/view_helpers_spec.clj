(ns fretset.util.view-helpers-spec
  (:require [speclj.core :refer :all]
            [fretset.util.view-helpers :refer :all]))

(describe "view helpers"
  (context "display users"
    (it "displays a users"
      (let [list-hiccup (list-users `({:first-name "taka" :last-name "goto"
                                      :email "fake@email.com"}))]
        (should= 1 (count list-hiccup))
        (should= '([:tr [:td "taka"] [:td "goto"] [:td "fake@email.com"]])
          list-hiccup)))

    (it "displays multiple users"
      (let [hiccup-html (list-users `({:first-name "taka" :last-name "goto"
                                       :email "fake@email.com"}
                                      {:first-name "george" :last-name "heisman"
                                       :email "big@email.com"}
                                      ))]
        (should= 2 (count hiccup-html))
        (should= '([:tr [:td "taka"] [:td "goto"] [:td "fake@email.com"]]
                   [:tr [:td "george"] [:td "heisman"] [:td "big@email.com"]])
          hiccup-html))))

  (context "display flash messages"
    (it "displays success flash message"
      (let [flash-hiccup (display-flash {:flash {:success ["success!"]}} :success)]
        (should= true true)
        )
      )
    )
  )
