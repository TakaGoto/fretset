(defproject fretset "0.1.0"
  :description "itinerary builder for trips"

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [digest "1.4.3"]
                 [garden "1.1.4"]
                 [hiccups "0.2.0"]
                 [hyperion/hyperion-mongo "3.6.0"]
                 [joodo "2.0.0"]
                 [metis "0.3.3"]
                 [ring/ring-jetty-adapter "1.2.0"]]

  :profiles {
    :dev {
      :main fretset.core
      :dependencies [[speclj "2.8.0"]]}}

  :repl-options {:init-ns fretset.repl
                 :init (do (clojure.core/require 'fretset.repl) (fretset.repl/init))}

  :plugins [[speclj "2.8.0"]
            [lein-ring "0.8.8"]]

  :test-paths ["spec/clj"]
  :source-paths ["src/clj"]

  :ring {:handler fretset.core/app}
)
