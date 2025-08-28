#!/bin/bash

# shellcheck disable=SC2164
cd /workspace

# Создаём директорию явно
mkdir -p /workspace/target/allure-results

# Запуск виртуального дисплея
Xvfb :99 -screen 0 1366x768x24 &
sleep 10

# Запись экрана
ffmpeg -y -video_size 1366x768 -framerate 15 -f x11grab -i :99 \
  -codec:v libx264 -pix_fmt yuv420p "$WORKDIR/screen_recording.mp4" > /dev/null 2>&1 &
echo $! > "$WORKDIR/ffmpeg_pid.txt"

# Запуск Maven-тестов (но не прерываем скрипт при ошибке)
mvn -B clean test -Dgroups=Second -DsuiteXmlFile='src/test/resources/StartNodes.xml' \
    -DSEED_PHRASE_10="$SEED_10" -DEMAIL_10="$MAIL_10" \
    -DSEED_PHRASE_9="$SEED_9" -DEMAIL_9="$MAIL_9" \
    -DSEED_PHRASE_8="$SEED_8" -DEMAIL_8="$MAIL_8" \
    -DSEED_PHRASE_7="$SEED_7" -DEMAIL_7="$MAIL_7" \
    -DSEED_PHRASE_6="$SEED_6" -DEMAIL_6="$MAIL_6" \
    -DSEED_PHRASE_5="$SEED_5" -DEMAIL_5="$MAIL_5" \
    -DSEED_PHRASE_4="$SEED_4" -DEMAIL_4="$MAIL_4" \
    -DSEED_PHRASE_3="$SEED_3" -DEMAIL_3="$MAIL_3" \
    -DSEED_PHRASE_2="$SEED_2" -DEMAIL_2="$MAIL_2" \
    -DSEED_PHRASE_1="$SEED_1" -DEMAIL_1="$MAIL_1" \
    -DSEED_PHRASE_0="$SEED_0" -DEMAIL_0="$MAIL_0" \
    -DPASSWORD="$PASS" -DPIN="$PIN" \
    || true

# Остановка записи
# shellcheck disable=SC2046
kill -INT $(cat ffmpeg_pid.txt) && sleep 10

# Проверка и копирование файла
if [ -f "$WORKDIR/screen_recording.mp4" ]; then
  cp "$WORKDIR/screen_recording.mp4" target/allure-results/
  echo "Видео сохранено"
else
  echo "Ошибка: Файл screen_recording.mp4 не найден"
fi
