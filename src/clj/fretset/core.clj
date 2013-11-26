(ns fretset.core
  (:require [compojure.route :as route]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [fretset.user.user-controller :refer :all]
            [hyperion.api :refer [set-ds! new-datastore]]
            [joodo.env :refer [*env* load-configurations]]
            [joodo.middleware.view-context :refer [wrap-view-context]]
            [joodo.middleware.util :refer [wrap-development-maybe]]
            [ring.adapter.jetty :refer [run-jetty]]))

(defroutes app-routes
  (GET "/" [] "<h1>Hello World </h1>")

  (context "/user" [] user-controller)

  (route/not-found "<h1>Page not found</h1>"))

(defn setup-data-store []
  (set-ds!
    (new-datastore
      (:datastore *env*))))

(def app
  (->
    (handler/site app-routes)
    wrap-development-maybe
    (wrap-view-context :template-root "fretset"
                       :ns `fretset.util.view-helpers
                       :layout "util/layout")))

(defn -main [& args]
  (load-configurations)
  (setup-data-store)
  (run-jetty app {:port 3000}))
