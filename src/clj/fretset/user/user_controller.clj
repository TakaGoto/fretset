(ns fretset.user.user-controller
  (:require [compojure.core :refer :all]
            [joodo.views :refer [render-template]]
            [hyperion.api :refer [save]]
            [fretset.user.user :refer [user validate-user]]
            [ring.util.response :refer [redirect]]))

(defn- create-user [params]
  (let [user (user params)
        errors (validate-user user)]
    (if (seq errors)
      (assoc (redirect "/signup") :flash {:errors errors})
      (do
        (save user)
        (redirect "/")))))

(defroutes user-controller
  (GET  "/signup" [] (render-template "user/signup"))
  (POST "/signup" {params :params}  (create-user params)))
