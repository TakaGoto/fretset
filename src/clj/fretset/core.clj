(ns fretset.core
  (:require [compojure.route :as route]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [fretset.user.user-controller :refer :all]
            [joodo.middleware.view-context :refer [wrap-view-context]]
            ))

(defroutes app-routes
  (GET "/" [] "<h1>Hello World </h1>")

  (context "/user" [] user-controller)

  (route/not-found "<h1>Page not found</h1>"))

(def app
  (-> (handler/site app-routes)
      (wrap-view-context :template-root "fretset"
                         :ns `fretset.util.view-helpers
                         :layout "util/layout"
                         ))
  )
