(ns fretset.core
  (:require [compojure.route                     :as route]
            [compojure.core                      :refer :all]
            [compojure.handler                   :as handler]
            [fretset.middleware.asset-preprocess :refer [preprocess-css]]
            [fretset.user.user-controller        :refer :all]
            [hyperion.api                        :refer [set-ds! new-datastore]]
            [joodo.env                           :refer [*env* load-configurations]]
            [joodo.middleware.view-context       :refer [wrap-view-context]]
            [joodo.middleware.util               :refer [wrap-development-maybe]]
            [joodo.middleware.request            :refer [wrap-bind-request *request*]]
            [joodo.views                         :refer [render-template]]
            [ring.adapter.jetty                  :refer [run-jetty]]
            [ring.middleware.cookies             :refer [wrap-cookies]]
            [ring.middleware.resource            :refer [wrap-resource]]
            [ring.middleware.params              :refer [wrap-params]]
            [ring.middleware.flash               :refer [wrap-flash]]
            [ring.middleware.session             :refer [wrap-session]]))

(defroutes app-routes
  (GET "/" [] (render-template "util/home"))

  (context "/user" [] user-controller)

  (route/not-found "<h1>Page not found</h1>"))

(defn- setup-data-store []
  (set-ds!
    (new-datastore
      (:datastore *env*))))

(def app
  (->
    (handler/site app-routes)
    wrap-bind-request
    wrap-development-maybe
    preprocess-css
    wrap-params
    wrap-flash
    wrap-session
    wrap-cookies
    (wrap-resource "public")
    (wrap-view-context :template-root "fretset"
                       :ns `fretset.util.view-helpers
                       :layout "util/layout")))

(defn -main [& args]
  (load-configurations)
  (setup-data-store)
  (run-jetty app {:port 4040}))
