package q004;

/**
 * Q004 ソートアルゴリズム
 *
 * ListManagerクラスをnewして、小さい順に並び変えた上でcheckResult()を呼び出してください。
 *
 * 実装イメージ:
 * ListManager data = new ListManager();
 * // TODO 並び換え
 * data.checkResult();
 *
 * - ListManagerクラスを修正してはいけません
 * - ListManagerクラスの dataList を直接変更してはいけません
 * - ListManagerクラスの比較 compare と入れ替え exchange を使って実現してください
 */
public class Q004 {
    public static void main(String[] args) {
        ListManager data = new ListManager();
        for (int i = 0; i < data.size(); i++) {
            int index = i;
            for (int j = i + 1; j < data.size(); j++) {
                // iとi+1を比較する
                if (data.compare(index, j) == 1) {
                    // i+1の方が値が小さい場合はindexを入れ替える
                    index = j;
                }
            }
            if (i != index) {
                // 値を並び替える
                data.exchange(i, index);
            }
        }
        // 結果発表
        data.checkResult();
    }
}
// 完成までの時間: 01時間 00分