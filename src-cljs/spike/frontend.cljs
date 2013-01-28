(ns spike.frontend
  (:require
   [cljs.reader :as reader]
   [goog.dom :as dom]
   [goog.events :as ev]
   [goog.net.XhrIo :as xhr]))

(defn- extract-response [message]
  (reader/read-string
   (. message/target (getResponseText))))

(defn- insert-into-dom
  [id val]
  (let [target (dom/getElement id)]
    (dom/setTextContent target val)))

(defn callback
  [message]
  (let [result (extract-response message)]
    (insert-into-dom "result" (:message result))))

(defn edn-call
  [path callback method data]
  (xhr/send path
            callback
            method
            (pr-str data)
            (clj->js {"Content-Type" "application/edn"})))

(defn send-request [e]
  (let [body {:name (.-value (dom/getElement "name"))}]
    (edn-call "/rest" callback "POST" body)))

(defn ^:export init []
  (ev/listen	
   (dom/getElement "button")
   "click"
   send-request))