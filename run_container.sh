docker stop dropgame_score_server &>/dev/null
docker rm dropgame_score_server &>/dev/null
docker run --name=dropgame_score_server \
--restart=always -it \
-v ./testdb.mv.db:/opt/app/testdb.mv.db \
-p8080:80 \
dropgame_score_server

