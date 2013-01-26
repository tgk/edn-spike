(ns edn-service-stub.routes
  (:use compojure.core
        compojure.route
        ring.middleware.edn))

(defn generate-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/edn"}
   :body (pr-str data)})

(defroutes main-routes
  (GET "/rest" req
       (generate-response {:reply 0}))

  (POST "/rest" {edn-params :edn-params}
        (generate-response {:reply (inc (:req edn-params))}))

  (resources "/"))

(def handler
  (-> main-routes
      wrap-edn-params))