# EDN service spike

Spike REST service using the content type "edn/application".
The backend is implemented in Clojure, the frontend in ClojureScript.

There is a small blog post describing the spike [here](http://tgk.github.com/2013/01/edn-service-spike.html).

## Usage

The ClojureScript portion of the project can be compiled using the cljsbuild plugin

    lein cljsbuild auto

The ring server, which serves both the html and supplies the REST service can be started using the lein ring plugin

    lein ring server

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
