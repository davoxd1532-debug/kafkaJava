# 📌 Apuntes de Git

Guía rápida para inicializar, configurar y trabajar con un repositorio Git en GitHub.

---

## 🚀 Primera vez (configuración inicial)

Estos comandos se utilizan cuando creas el repositorio por primera vez en tu máquina:

```bash
git init
git status
git add .
git commit -m "Descripción de los cambios"
git remote add origin https://github.com/davoxd1532-debug/kafkaJava.git
git push -u origin main
git push -u origin master
```

## 🔄 A partir de la segunda vez (nuevos cambios)
```bash
git add .
git commit -m "Nuevo cambio"
git push
```

## 🔍 Validar el repositorio remoto
Verifica a qué repositorio remoto (origin) está conectado tu proyecto:
```bash
git remote -v
```

## ⚙️ Cambiar (setear) el repositorio remoto
Si necesitas cambiar el repositorio remoto al que está vinculado:
```bash
git remote set-url origin https://github.com/davoxd1532-debug/java_kafka.git
```

## 🔄 Sincronizar repositorio
Dependiendo de la situación:
Si el remoto ya tiene datos y quieres actualizar tu local:
```bash
git pull origin main --rebase
```

## Si el remoto está vacío (nuevo) y quieres subir tu proyecto local:
```bash
git push origin main
```
