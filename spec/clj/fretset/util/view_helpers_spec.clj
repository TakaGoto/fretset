(ns fretset.util.view-helpers-spec
  (:require [speclj.core :refer :all]
            [fretset.util.view-helpers :refer :all]))

(describe "view helpers"
  (context "display users"
    (it "displays a users"
      (let [hiccup-html (list-users `({:first-name "taka" :last-name "goto"
                                      :email "fake@email.com"}))]
        (should= 1 (count hiccup-html))
        (should= '([:tr [:td "taka"] [:td "goto"] [:td "fake@email.com"]])
          hiccup-html)))

    (it "displays multiple users"
      (let [hiccup-html (list-users `({:first-name "taka" :last-name "goto"
                                       :email "fake@email.com"}
                                      {:first-name "george" :last-name "heisman"
                                       :email "big@email.com"}
                                      ))]
        (should= 2 (count hiccup-html))
        (should= '([:tr [:td "taka"] [:td "goto"] [:td "fake@email.com"]]
                   [:tr [:td "george"] [:td "heisman"] [:td "big@email.com"]])
          hiccup-html)))))
