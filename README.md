## リポジトリについて
CLIなオセロです。なお、先手を入力により操作可能です。また、後手番はランダムな位置に駒を置く機能を持ちます。  
引数によるプレイヤー指定は実装されていません。`src/main/java/jp/**/App.java`でプレイヤーの指定を行っているため記載を変更することでプレイヤーの変更が可能です。

## 依存ライブラリ等について
Apache commonsライブラリを使用しています。  
依存関係はgradleを用いて管理しているため`gradle build`や`gradle run`を利用しての実行を推奨します。

## How to build fat jar
依存外部ライブラリを含めたjarファイルを生成するためにGradleのshadowプラグインを導入しています  
そのため、`gradle shadowjar`によりfat jarが生成できます。