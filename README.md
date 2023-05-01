# 概要

JSONとファイルをmultipart/form-dataでフロントから受け取りBFFからBEに送信する例
BE編

## 実行

以下コマンドで実行

```
gradle bootRun
```

下記コマンドでpostの確認。

```
curl -i -X POST \
 -F "file=@./public/wordlist.txt" \
 -F "jsonValue={\"isUseDefaultWordList\": true};type=application/json" \
 http://localhost:8080/word-list
```

## 備考

ITとコントローラの実装までできています。
[IT](src/test/java/com/example/demo/api/it/UploadIntegrationTest.java)
[コントローラのUT](src/test/java/com/example/demo/api/presentations/FirstControllerTest.java)
