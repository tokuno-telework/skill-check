package q003;

import java.io.*;
import java.util.*;

/**
 * Q003 集計と並べ替え
 *
 * 以下のデータファイルを読み込んで、出現する単語ごとに数をカウントし、アルファベット辞書順に並び変えて出力してください。
 * resources/q003/data.txt
 * 単語の条件は以下となります
 * - "I"以外は全て小文字で扱う（"My"と"my"は同じく"my"として扱う）
 * - 単数形と複数形のように少しでも文字列が異れば別単語として扱う（"dream"と"dreams"は別単語）
 * - アポストロフィーやハイフン付の単語は1単語として扱う（"isn't"や"dead-end"）
 *
 * 出力形式:単語=数
 *
[出力イメージ]
（省略）
highest=1
I=3
if=2
ignorance=1
（省略）

 * 参考
 * http://eikaiwa.dmm.com/blog/4690/
 */
public class Q003 {
    /**
     * データファイルを開く
     * resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q003.class.getResourceAsStream("data.txt");
    }

    // 区切り文字
    private static final String SEPARATOR = "(\\s+?|\\.|,|;)";
    // 除外する記号
    private static final String IGNORED = "^[–|']*$";

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        try {
            // ファイルからバイトストリームを作る
            InputStream is = openDataFile();
            // バイトストリームから文字列ストリームを作る
            Reader reader1 = new InputStreamReader(is);
            // リーダーをバッファする
            BufferedReader br = new BufferedReader(reader1);
            // BufferedReader には、テキスト行を読み込む readLine() がある
            String line;
            // 一行ずつ取り出す
            while ((line = br.readLine()) != null) {
                // 一行を空白,ピリオド,カンマ,セミコロンで区切る
                String[] words = line.split(SEPARATOR);
                for (String word : words) {
                    // 空もしくは記号を除外する
                    if (!word.isEmpty()) {
                        String changedWord;
                        // 'I'はそのまま、'I'以外の単語を小文字に変換
                        if (word.equals("I")) {
                            changedWord = word;
                        } else {
                            changedWord = word.toLowerCase();
                        }
                        // 連想配列に単語とその数を格納
                        if (map.containsKey(changedWord)) {
                            int count = map.get(changedWord) + 1;
                            map.put(changedWord, count);
                        } else {
                            map.put(changedWord, 1);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ファイルが見つかりませんでした。");
        } catch (IOException e) {
            System.out.println("読み取りに失敗しました。");
        }

        List<String> list = new ArrayList<>();
        for (String key : map.keySet()) {
            // 記号（ハイフンやアポストロフィ）は出力対象から除外する
            if (!key.matches(IGNORED)) {
                list.add(key);
            }
        }
        // アルファベット辞書順に並び替え
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);

        String format = "%-14s= %3d";
        for (String word : list) {
            int count = map.get(word);
            System.out.printf(format, word, count);
            System.out.println();
        }
    }
}
// 完成までの時間: 03時間 30分