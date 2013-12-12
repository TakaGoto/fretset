(ns fretset.util.modal-spec
  (:require-macros [specljs.core :refer [describe it should= before with]])
  (:require [specljs.core]
            [domina             :as dom]
            [domina.css         :as css]
            [domina.events      :as event]
            [fretset.util.modal :as modal]))

(describe "Modal"
(with modal (modal/hello "modal-id" "modal-class"))

(before
  (let [body (css/sel "body")]
    (dom/set-inner-html! (dom/single-node body) "")))

  (it "xreates a modal node"
    (let [result (dom/attrs @modal)]

      (should= "modal-id" (:id result))
      (should= "modal-class-backdrop" (:class result))))

  (it "shows the modal"
    (modal/show @modal)
    (should= @modal (dom/by-id "modal-id")))

  (it "hides the modal"
    (should= nil (dom/by-id "modal-id"))
    (modal/show @modal)
    (should= @modal (dom/by-id "modal-id"))
    (modal/hide @modal)
    (should= nil (dom/by-id "modal-id"))))
