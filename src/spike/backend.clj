(ns spike.backend
  (:use compojure.core
        compojure.route
        ring.middleware.edn))

(defn generate-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/edn"}
   :body (pr-str data)})

(defroutes main-routes
  (GET "/" req (slurp "resources/public/index.html"))
  
  (GET "/rest" req
       (generate-response {:message "Hello stranger"}))

  (POST "/rest" {edn-params :edn-params}
        (generate-response
         {:message (str "Hello " (->> edn-params :name reverse (apply str)))}))

  (resources "/"))

(def handler
  (-> main-routes
      wrap-edn-params))