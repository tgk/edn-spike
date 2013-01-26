(defproject edn-service-stub "0.1.0-SNAPSHOT"
  :description "A spike demonstrating how edn can be used in a REST service"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [ring "1.1.6"]
                 [ring-edn "0.1.0"]
                 [compojure "1.1.3"]]
  :plugins [[lein-ring "0.7.5"]
            [lein-cljsbuild "0.3.0"]]
  :cljsbuild {:builds
              [{:source-paths ["src-cljs"],
                :compiler
                {:output-to "resources/public/js/compiled.js",
                 :optimizations :advanced}}]}
  :ring {:handler edn-service-stub.routes/handler})
