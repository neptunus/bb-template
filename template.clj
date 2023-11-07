#!/usr/bin/env bb
(ns template
  (:require
   [selmer.parser :refer [render render-file add-filter!]]
   [clojure.string :as str]
   [babashka.fs :as fs]))

(defn pascal-case
  [string]
  (str/join ""
            (map #(str/capitalize %)
                 (str/split string #" "))))

(defn kebab-case
  [string]
  (-> string
      str/lower-case
      str/trim
      (str/replace " " "-")))

(add-filter! :kebab-case kebab-case)

(add-filter! :pascal-case pascal-case)

(let [[component-name] *command-line-args*]
  
  (when (empty? component-name)
    (println "Usage: <name of component>")
    (System/exit 1))
  
  (let [vars {:component-name component-name}
        kebab (render "{{component-name|kebab-case}}" vars)] 
    
    (fs/create-dirs (str "./dist/" kebab)) 

    (let [templates (map fs/file-name (fs/list-dir "./resources/templates/"))]
      (run! #(let [filename (render % vars)]
               (spit (str "dist/" kebab "/" filename) (render-file (str "templates/" %) vars)))
            templates))))