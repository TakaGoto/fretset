(ns fretset.core
  (:require [compojure.route :as route]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [fretset.user.user-controller :refer :all]
            [hyperion.api :refer [set-ds! new-datastore]]
            [joodo.middleware.view-context :refer [wrap-view-context]]
            [joodo.middleware.util :refer [wrap-development-maybe]]))

(defroutes app-routes
  (GET "/" [] "<h1>Hello World </h1>")

  (context "/user" [] user-controller)

  (route/not-found "<h1>Page not found</h1>"))

(defn setup-data-store []
  (set-ds!
    (new-datastore
      :implementation :mongo
      :host "localhost"
      :port 27017
      :database "fretset"
      :username "fretset"
      :password "fretset")))

(defn wrap-environement [handler]
  (setup-data-store)
  (fn [request]
    (handler request)))

(def app
  (->
    (handler/site app-routes)
    wrap-environement
    wrap-development-maybe
    (wrap-view-context :template-root "fretset"
                       :ns `fretset.util.view-helpers
                       :layout "util/layout")))
