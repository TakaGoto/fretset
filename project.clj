(defproject fretset "0.1.0"
  :description "itinerary builder for trips"

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [ring/ring-jetty-adapter "1.2.0"]
                 [joodo "2.0.0"]
                 [hyperion/hyperion-mongo "3.6.0"]
                 [metis "0.3.3"]]

  :profiles {
    :dev {
      :dependencies [[speclj "2.8.0"]]}}

  :plugins [[speclj "2.8.0"]
            [lein-ring "0.8.8"]]


  :test-paths ["spec/clj"]
  :source-paths ["src/clj"]

  :ring {:handler fretset.core/app}
)
