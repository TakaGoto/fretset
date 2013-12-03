(ns fretset.user.user-controller
  (:require [compojure.core           :refer :all]
            [joodo.views              :refer [render-template]]
            [hyperion.api             :refer [save find-by-kind]]
            [fretset.user.user        :refer [user validate-user]]
            [fretset.user.login-user  :refer [login logout]]
            [joodo.middleware.request :refer [*request*]]
            [ring.util.response       :refer [redirect]]))

(defn- create-user [params]
  (let [user (user params)
        errors (validate-user user)]
    (if (seq errors)
      (assoc (redirect "/user/signup") :flash {:errors errors})
      (do
        (save user)
        (assoc (redirect "/") :flash {:success {:account ["Your account has been created!"]}})))))

(defn- render-signup-form []
  (render-template "user/signup" :flash (:flash *request*)))

(defroutes user-controller
  (GET   "/users" [] (render-template "user/users" :users (find-by-kind :user)))

  (GET   "/login" [] (render-template "user/login" :flash (:flash *request*)))
  (POST  "/login" [] (login *request*))

  (GET   "/logout" [] (logout (redirect "/")))

  (GET   "/signup" [] (render-signup-form))
  (POST  "/signup" {params :params}  (create-user params)))
