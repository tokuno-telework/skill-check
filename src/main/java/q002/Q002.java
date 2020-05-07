package q002;

import java.util.*;
/**
 * Q002 並べ替える
 *
 * dataListに "ID,名字" の形式で20個のデータがあります。
 * これをID順に並べて表示するプログラムを記述してください。
 *
 * dataListの定義を変更してはいけません。
 *
 *
[出力結果イメージ]
1,伊藤
2,井上
（省略）
9,清水
10,鈴木
11,高橋
（省略）
20,渡辺
 */
public class Q002 {
    /**
     * データ一覧
     */
    private static final String[] dataList = {
            "8,佐藤",
            "10,鈴木",
            "11,高橋",
            "12,田中",
            "20,渡辺",
            "1,伊藤",
            "18,山本",
            "13,中村",
            "5,小林",
            "3,加藤",
            "19,吉田",
            "17,山田",
            "7,佐々木",
            "16,山口",
            "6,斉藤",
            "15,松本",
            "2,井上",
            "4,木村",
            "14,林",
            "9,清水"
    };

    public static void main(String[] args) {
        Map<Integer, Integer> numPart = new HashMap<>();
        List<String> namePart = new ArrayList<>();

        // 数字部分と名前部分を分ける
        for (int i = 0; i < dataList.length; i++) {
            String[] data = dataList[i].split(",", 0);
            numPart.put(Integer.parseInt(data[0]), i);
            namePart.add(data[1]);
        }

        // 数字部分に紐づく名前部分を,番号順に取得する
        for(Map.Entry<Integer, Integer> entry : numPart.entrySet()) {
            System.out.println(entry.getKey() + ":" + namePart.get(entry.getValue()));
        }
    }
}
// 完成までの時間: 04時間 30分