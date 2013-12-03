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

  (context "display login or logout"
    (it "displays login if there is no token cookie"
      (should-contain "login"
        (:href (second (second (display-logout-login {}))))))

    (it "displays logout if there is a token cookie"
      (should-contain "logout"
        (:href (second (second (display-logout-login {:cookies {"token" {:value "token"}}}))))))))
