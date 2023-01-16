# プログラミングⅡ 
## レポート課題6: Java最終課題(リバーシの作成)

<script type="text/javascript" async src="https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.7/MathJax.js?config=TeX-MML-AM_CHTML">
</script>
<script type="text/x-mathjax-config">
 MathJax.Hub.Config({
 tex2jax: {
 inlineMath: [['$', '$'] ],
 displayMath: [ ['$$','$$'], ["\\[","\\]"] ]
 }
 });
</script>

<div style="text-align: right;">
編集日:2023-01-24<br>
報告者:e225717 高嶺拓矢<br>  
協力者:なし
</div>

### GitHubリンク
https://github.com/medicine-t/Othello

### 課題概要
本課題で実装したものは、コマンドラインを用いて動作するオセロ(リバーシ)である。
ルールは一般に知られているボードゲームのものとほとんど同じであるため、詳細なルールは[Wikipedia(オセロ_ボードゲーム)](https://ja.wikipedia.org/wiki/%E3%82%AA%E3%82%BB%E3%83%AD_(%E3%83%9C%E3%83%BC%E3%83%89%E3%82%B2%E3%83%BC%E3%83%A0))をもとにしている。
ルール面での本課題と一般的なルールの差異として、棋譜のとり方、つまり駒の置かれた場所の表記方法が異なっている。一般的なルールでは初期盤面において黒の駒が右上にあるとき、左上から番号を横を`a-f`、縦を`1-8`と振り、`f5`のように指定するが、今回作成したオセロでは、`縦_横`(_はスペース)と位置を入力する際に指定する。
