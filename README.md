# kotlin-training002

Kotlin でAndroid 開発を試してみたリポジトリです。

## 開発環境

- Windows 11
- Android Studio 2023.1.1
- Amazon Corretto 17.0.3
- Docker Desktop

## 実行方法

1. Android Studio でプロジェクトを開く

2. プロジェクトを実行

仮想デバイスで動作テスト

### APIサーバーとデータベースの起動

本アプリは、APIサーバーとの連携を行います。

APIサーバーのリポジトリは以下です。

<https://github.com/y-uchiida/kotlin-training003>

データベースは、Docker で起動します。

お手元の環境に、Docker Desktop をインストールしてください。

1. APIサーバーのリポジトリをクローン

  ```powershell
  git clone git@github.com:y-uchiida/kotlin-training003.git
  ```

2. APIサーバーのディレクトリに移動

  ```powershell
  cd kotlin-training003
  ```

3. データベースの起動

  ```powershell
  docker compose up -d
  ```

4. APIサーバーの起動

  ```powershell
  ./gradlew bootRun
  ```

  以下のURLにアクセスして、APIサーバーが起動していることを確認します。

  <http://localhost:8080/hello>

## API サーバーの接続先

開発用マシンで APIサーバーとの連携を試す場合、Android 仮想端末上から localhost 指定でAPIサーバーにリクエストすることができません。  

そのため、サンプルではお手元の開発用マシンのIPアドレスを指定してリクエストしています。

API サーバーの接続先を変更する場合は、以下のファイルを修正してください。

`app\src\main\res\values\strings.xml`

```xml
    <!-- APIエンドポイントの通信先ホストの設定 -->
    <string name="api_schema">http</string>
    <string name="api_port">8080</string>
    <string name="api_host">192.168.1.5</string>
```

上記のようになっているので、 `api_host` の値をそれぞれの環境にあわせて設定してください。

APIサーバーのポート番号を変更した場合は、 `api_port` の値も変更してください。
