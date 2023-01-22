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
編集日:2023-01-22<br>
報告者:e225717 高嶺拓矢<br>  
協力者:なし
</div>

### GitHubリンク
[https://github.com/medicine-t/Othello]()

### 課題概要
本課題で実装したものは、コマンドラインを用いて動作するオセロ(リバーシ)である。
ルールは一般に知られているボードゲームのものとほとんど同じであるため、詳細なルールは[Wikipedia(オセロ_ボードゲーム)](https://ja.wikipedia.org/wiki/%E3%82%AA%E3%82%BB%E3%83%AD_(%E3%83%9C%E3%83%BC%E3%83%89%E3%82%B2%E3%83%BC%E3%83%A0))をもとにしている。
ルール面での本課題と一般的なルールの差異として、棋譜のとり方、つまり駒の置かれた場所の表記方法が異なっている。一般的なルールでは初期盤面において黒の駒が右上にあるとき、左上から番号を横を`a-f`、縦を`1-8`と振り、`f5`のように指定するが、今回作成したオセロでは、`縦_横`(_はスペース)と位置を入力する際に指定する。

### 取り組み方
* クラス設計について
  * ゲームの状態(プレイヤー情報やゲーム中の盤面情報)は`GameManager`が扱っている。
  * 盤面を操作するAPIとして`Othello`クラスを用意している。
  * 盤面を操作するプレイヤーは`Player`interfaceを持っており、将来的に探索アルゴリズムなどを使用したプレイヤーを実装したいとなった際に既存部分の変更が抑えられるようになっている。
    * 今回は標準入力からの操作が可能な`InteractivePlayer`、着手可能な位置からランダムに1つ選択する`RandomWalk`の2プレイヤーを実装した。
  
### コード解説
* Othello.checkPuttable()について
与えられた位置に駒を置くとした場合に、反転する端点と反転方向を持ったクラスのArrayListを返す。
ここでは反転処理の端点の導出方法について述べる。
オセロのルールについての確認となるが、ある地点に駒を置いたとき、反転処理はそれぞれの方向について最も近い自駒までについて行う。つまり`_oooxoox`(自手:`x`)としたとき`_`に駒を置くと`xxxxxoox`となる。
処理としては各方向について盤面の位置を`(i,j)`と表すときwhileにより異なる自駒になるまで`(i,j)`を更新する。
更新処理は`Othello`に各方向に1マス文更新するのに必要な差分を`di`、`dj`として配列を用意しており、この差分を`(i,j)`に加えることで更新している。
```java
    /**
     * 駒の反転処理はput()で行う。
     * 
     * @param i       盤面を左上原点として見たときの行番号
     * @param j       盤面を左上原点として見たときの列番号
     * @param isBlack 先手番か否か
     * @return true/false
     */
    public ArrayList<Reversible> checkPuttable(int i, int j, boolean isBlack) {
        if ((i < 0 || i > board.BOARD_HEIGHT) || (j < 0 || j > board.BOARD_WIDTH)) {
            return new ArrayList<>();
        }
        if (board.blackBoard[i][j] || board.whiteBoard[i][j])
            return new ArrayList<>();
        else {
            ArrayList<Reversible> puttable = new ArrayList<>();
            for (int d = 0; d < 8; d++) {
                int ni = i;
                int nj = j;
                boolean breaked = false;
                int reverseCount = 0;
                do {
                    ni += di[d];
                    nj += dj[d];
                    if (Math.min(ni, nj) < 0) {
                        breaked = true;
                        break;
                    }
                    if (Math.max(ni, nj) >= 8) {
                        breaked = true;
                        break;
                    }
                    reverseCount++;
                } while (isBlack ? board.whiteBoard[ni][nj] : board.blackBoard[ni][nj]);
                if (!breaked && reverseCount > 1 && (isBlack ? board.blackBoard[ni][nj] : board.whiteBoard[ni][nj])) {
                    puttable.add(new Reversible(ni, nj, d));
                }
            }
            return puttable;
        }
    }
```
* Othello.put()について
ここでは主に反転処理について解説する。
序盤で呼び出される`checkPuttable()`(先述)により設置場所(起点)から8方向それぞれについて(存在するならば)反転処理の起点でない端点と方向を要素に持つArrayListとして得ることができる。
このとき得る事のできる方向(.dir)は起点から見た場合の方向であるため、反転処理を行うに当たり8方向の反対を向ける。処理としては`7 - .dir`としているがこれは`Othello.di`及び`Othello.dj`で方向を定義しており、`7 - .dir`により正しい方向となるような値になっているためである。
以下Othello.put()のコード
```java
/**
     * 引数に与えられたポジションに駒を設置する。
     * 
     * @param i       盤面を左上原点として見たときの行番号
     * @param j       盤面を左上原点として見たときの列番号
     * @param isBlack 先手番か否か
     * @return boolean: 実際に設置できたか否か
     */
    public boolean put(int i, int j, boolean isBlack) {
        var puttable = checkPuttable(i, j, isBlack);
        if (puttable.isEmpty()) {
            return false;
        }
        var myBoard = board.blackBoard;
        var enemyBoard = board.whiteBoard;
        if (!isBlack) {
            myBoard = board.whiteBoard;
            enemyBoard = board.blackBoard;
        }

        myBoard[i][j] = true;
        enemyBoard[i][j] = false;

        for (Reversible rev : puttable) {
            int newDir = (7 - rev.dir);
            while (rev.i != i || rev.j != j) {
                myBoard[rev.i][rev.j] = true;
                enemyBoard[rev.i][rev.j] = false;
                rev.i += di[newDir];
                rev.j += dj[newDir];

                if (isBlack) {
                    board.blackCount++;
                    if (rev.i != i || rev.j != j)
                        board.whiteCount--;
                } else {
                    board.whiteCount++;
                    if (rev.i != i || rev.j != j)
                        board.blackCount--;
                }
            }
        }
        return true;
    }
```

### 実行結果
(`gradle run`でも動作は可能だがgradleのタイマー表示が掲載上邪魔なためjarをビルド後、単体で実行している)
```bash
$ gradle shadowjar && java -jar ./app/build/libs/app-all.jar
BUILD SUCCESSFUL in 738ms
2 actionable tasks: 1 executed, 1 up-to-date
white: o / black: x
==========================
 01234567
0........
1........
2........
3...ox...
4...xo...
5........
6........
7........
Statics: Black2,White2
現在の手番はx
縦の番号 横の番号  の形式で入力
4 5
==========================
 01234567
0........
1........
2........
3...ox...
4...xxx..
5........
6........
7........
Statics: Black4,White1
[Random Walk] put 5, 5
==========================
 01234567
0........
1........
2........
3...ox...
4...xox..
5.....o..
6........
7........
Statics: Black3,White3
現在の手番はx
縦の番号 横の番号  の形式で入力
5 4
==========================
(中略)
==========================
 01234567
0ox..xxxx
1oxxxxxox
2oxoxooxx
3oxooxxox
4oxoxxoxx
5oxxxxxox
6oxoxxoxx
7x.ooxxxx
Statics: Black53,White32
[Random Walk] put 0, 3
==========================
 01234567
0ox.oxxxx
1oxoooxox
2ooooooxx
3oxooxxox
4oxoxxoxx
5oxxxxxox
6oxoxxoxx
7x.ooxxxx
Statics: Black48,White40
現在の手番はx
縦の番号 横の番号  の形式で入力
7 1
==========================
 01234567
0ox.oxxxx
1oxoooxox
2ooooooxx
3oxooxxox
4oxoxxoxx
5oxxxxxox
6oxxxxoxx
7xxxxxxxx
Statics: Black53,White37
[Random Walk] put 0, 2
==========================
 01234567
0ooooxxxx
1oooooxox
2ooooooxx
3oxooxxox
4oxoxxoxx
5oxxxxxox
6oxxxxoxx
7xxxxxxxx
黒:先手番[x] の勝ちです!
```
### テスト結果
```bash
$ gradle test

BUILD SUCCESSFUL in 989ms
3 actionable tasks: 2 executed, 1 up-to-date
```

### 考察
今回の課題を振り返って、課題期間中盤に作業が止まったこと、設計に反映されていないが挑戦したいと思っていたGUI化や探索アルゴリズムによるPlayer実装を行うことができなかったことが心残りだった。
中盤に失速したことについては、特に課題に追われていたなどではなく、趣味に時間を使っていたためであるから、GitHub milestoneを利用するなど機能をチケット化した上でスケジュールを管理すると良いのではないかと感じた。
また、課題当初から探索を利用したプライヤーを実装したかったため、Playerをinterfaceを用いて実装したが、アルゴリズムの実装を想定したときに状態の持ち方などで悩んでしまったことが今回課題期間内で実装ができなかった原因だと考えている。この点については学サポで相談しながら実装方針を建てることができたのではないかと考えている。