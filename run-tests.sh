#!/usr/bin/env bash
set -o pipefail   # не прерывать скрипт, но фиксировать ошибки команд

# -------------------------------------------------
# 1. Переменные
# -------------------------------------------------
WORKDIR="/workspace"
cd "$WORKDIR"

# -------------------------------------------------
# 2. Создаём нужные каталоги (после монтирования)
# -------------------------------------------------
mkdir -p "$WORKDIR/target/allure-results"
mkdir -p "$WORKDIR/.x11-unix"   # иногда требуется для Xvfb

# -------------------------------------------------
# 3. Запуск виртуального дисплея
# -------------------------------------------------
echo "▶️  Запускаем Xvfb ..."
Xvfb :99 -screen 0 1366x768x24 &
XVFB_PID=$!
sleep 5   # небольшая пауза, чтобы Xvfb успел стартовать

# -------------------------------------------------
# 4. Запуск записи экрана
# -------------------------------------------------
FFMPEG_LOG="$WORKDIR/ffmpeg.log"
echo "▶️  Запускаем ffmpeg (лог → $FFMPEG_LOG) ..."
ffmpeg -y -video_size 1366x768 -framerate 15 -f x11grab -i :99 \
       -c:v libx264 -pix_fmt yuv420p "$WORKDIR/screen_recording.mp4" \
       >"$FFMPEG_LOG" 2>&1 &
FFMPEG_PID=$!
echo "$FFMPEG_PID" > "$WORKDIR/ffmpeg_pid.txt"

# -------------------------------------------------
# 5. Запуск Maven‑тестов
# -------------------------------------------------
echo "▶️  Запускаем Maven‑тесты ..."
# Мы НЕ хотим, чтобы падение тестов прервало скрипт → || true
mvn -B clean test -Dgroups=First -DsuiteXmlFile='src/test/resources/StartNodes.xml' \
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

# -------------------------------------------------
# 6. Остановка записи экрана
# -------------------------------------------------
echo "▶️  Останавливаем ffmpeg (PID=$FFMPEG_PID) ..."
kill -INT "$FFMPEG_PID" 2>/dev/null || echo "⚠️  ffmpeg уже завершён"
sleep 3   # даём ffmpeg время завершить файл

# -------------------------------------------------
# 7. Копируем видео в директорию Allure
# -------------------------------------------------
if [[ -f "$WORKDIR/screen_recording.mp4" ]]; then
    cp "$WORKDIR/screen_recording.mp4" "$WORKDIR/target/allure-results/"
    echo "✅  Видео скопировано в target/allure-results/"
else
    echo "❌  Файл screen_recording.mp4 НЕ найден!"
fi

# -------------------------------------------------
# 8. Выводим небольшие отладочные данные
# -------------------------------------------------
echo "📂 Содержимое $WORKDIR/target/allure-results/:"
ls -l "$WORKDIR/target/allure-results/" || echo "❗ Папка пуста"
echo "📝 Содержание ffmpeg.log (первые 20 строк):"
head -n 20 "$FFMPEG_LOG" || echo "❗ ffmpeg.log отсутствует"

# Завершаем Xvfb (чисто для порядка)
kill "$XVFB_PID" 2>/dev/null || true