//primera vez
git init
git status
git add .
git commit -m "Descripción de los cambios"
git remote add origin https://github.com/davoxd1532-debug/kafkaJava.git
git push -u origin main
git push -u origin master

//apartir de la segunda vez.
git add .
git commit -m "Nuevo cambio"
git push


//validar repo
git remote -v

//setear repo
git remote set-url origin https://github.com/davoxd1532-debug/java_kafka.git

//si ya tiene datos y se quiere actualizar el local usar:
git pull origin main --rebase

//si no tiene nada y es nuevo usar
git push origin main



