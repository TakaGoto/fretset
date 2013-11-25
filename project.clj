(defproject fretset "0.1.0-SNAPSHOT"
  :description "itinerary builder for trips"

  :dependencies [[org.clojure/clojure "1.5.1"]]

  :profiles {
    :dev {
      :dependencies [[speclj "2.8.0"]]}}

  :plugins [[speclj "2.8.0"]]

  :test-paths ["spec"]
)
