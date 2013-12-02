(ns fretset.user.user-controller
  (:require [compojure.core           :refer :all]
            [joodo.views              :refer [render-template]]
            [hyperion.api             :refer [save find-by-kind]]
            [fretset.user.user        :refer [user validate-user]]
            [ring.util.response       :refer [redirect]]))

(defn- create-user [params]
  (let [user (user params)
        errors (validate-user user)]
    (if (seq errors)
      (assoc (redirect "/user/signup") :flash {:errors errors})
      (do
        (save user)
        (redirect "/")))))

(defn- render-form []
  (render-template "user/signup"))

(defroutes user-controller
  (GET  "/users" [] (render-template "user/users" :users (find-by-kind :user)))
  (GET  "/signup" [] (render-form))
  (POST "/signup" {params :params}  (create-user params)))
