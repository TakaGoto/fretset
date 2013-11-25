(ns fretset.user.user-controller
  (:require [compojure.core :refer :all]
            [joodo.views :refer [render-template]]))

(defroutes user-controller
  (GET "/signup" [] (render-template "user/signup")))
