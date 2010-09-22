#!/usr/bin/env cake run
(in-ns 'textmate)
(clojure.core/require '[clojure.contrib.repl-utils :as ru])
(clojure.core/require '[clojure.java.io :as io])
(clojure.core/require '[clojure.repl :as repl])
(clojure.core/load-file (clojure.core/str (io/file (cake/*env* "TM_BUNDLE_SUPPORT") "utils.clj")))

; (println (file-ns) "<br>")
; (println (ns-refers (file-ns)) "<br>")
(defn- get-completions []  
  (let [cur-symbol (get-current-symbol-str)]    
    (concat
      (for [s (map (comp str first) (concat (ns-publics (file-ns)) (ns-refers (file-ns))))
            :when (.startsWith s cur-symbol)] s))))

(println (string/join " " (get-completions)))