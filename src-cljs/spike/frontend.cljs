(ns spike.frontend
  (:require
   [cljs.reader :as reader]
   [goog.dom :as dom]
   [goog.events :as ev]
   [goog.string :as gstr]
   [goog.net.XhrIo :as xhr]))

(defn- extract-response [message]
  (reader/read-string
   (. message/target (getResponseText))))

(defn- insert-into-dom
  [id val]
  (let [target (dom/getElement id)]
    (dom/setTextContent target val)))

(defn- extract-from-dom
  [id]
  (gstr/toNumber (.-value (dom/getElement id))))

(defn callback
  [message]
  (let [result (extract-response message)
        count (:reply result)]
    (insert-into-dom "result" count)))

(defn edn-call
  [path callback method data]
  (xhr/send path
            callback
            "POST"
            (pr-str data)
            (clj->js {"Content-Type" "application/edn"})))

(defn send-request [e]
  (let [body {:req (extract-from-dom "thing")}]
    (edn-call "/rest" callback "POST" body)))

(defn ^:export init []
  (ev/listen	
   (dom/getElement "button")
   "click"
   send-request))