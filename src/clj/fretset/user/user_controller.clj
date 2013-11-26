(ns fretset.user.user-controller
  (:require [compojure.core :refer :all]
            [joodo.views :refer [render-template]]
            [hyperion.api :refer [save]]
            [fretset.user.user :refer [user]]
            [ring.util.response :refer [redirect]]))

(defn- create-user [params]
  (save (user params))
  (redirect "/"))

(defroutes user-controller
  (GET "/signup" [] (render-template "user/signup"))
  (POST "/signup" {params :params}  (create-user params))
  )
