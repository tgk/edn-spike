(defproject edn-service-spike "0.1.0"
  :description "A spike demonstrating how edn can be used in a REST service"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [ring "1.2.1"]
                 [ring-edn "0.1.0"]
                 [compojure "1.1.6"]]
  :plugins [[lein-ring "0.8.8"]
            [lein-cljsbuild "0.3.0"]]
  :cljsbuild {:builds
              [{:source-paths ["src-cljs"],
                :compiler
                {:output-to "resources/public/js/compiled.js",
                 :optimizations :advanced}}]}
  :ring {:handler spike.backend/handler})
