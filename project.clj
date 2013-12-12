(defproject fretset "0.1.0"
  :description "itinerary builder for trips"

  :dependencies [[compojure "1.1.6"]
                 [digest "1.4.3"]
                 [domina "1.0.2"]
                 [garden "1.1.4"]
                 [hiccups "0.2.0"]
                 [hyperion/hyperion-mongo "3.6.0"]
                 [joodo "2.0.0"]
                 [metis "0.3.3"]
                 [org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2014"]
                 [ring/ring-jetty-adapter "1.2.0"]]

  :profiles {
    :dev {
      :main fretset.core
      :dependencies [[speclj  "2.8.0"]
                     [specljs "2.8.0"]]}}

  :repl-options {:init-ns fretset.repl
                 :init (do (clojure.core/require 'fretset.repl) (fretset.repl/init))}

  :plugins [[speclj "2.8.0"]
            [lein-ring "0.8.8"]
            [lein-cljsbuild "0.3.4"]]

  :test-paths   ["spec/clj"]
  :source-paths ["src/clj"]

  :cljsbuild {
    :builds {
      :dev {:source-paths ["src/cljs" "spec/cljs"]
            :compiler {:output-to "resources/public/javascript/fretset_dev.js"
                       :optimizations :none
                       :pretty-print true }}}}

  :ring {:handler fretset.core/app})
