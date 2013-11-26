(alter-env!
  assoc
  :joodo-env "development"
  :datastore { :implementation :mongo
               :host "localhost"
               :port 27017
               :database "fretset"
               :username "fretset"
               :password "fretset"})

