package q005;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Q005 データクラスと様々な集計
 *
 * 以下のファイルを読み込んで、WorkDataクラスのインスタンスを作成してください。
 * resources/q005/data.txt
 * (先頭行はタイトルなので読み取りをスキップする)
 *
 * 読み込んだデータを以下で集計して出力してください。
 * (1) 役職別の合計作業時間
 * (2) Pコード別の合計作業時間
 * (3) 社員番号別の合計作業時間
 * 上記項目内での出力順は問いません。
 *
 * 作業時間は "xx時間xx分" の形式にしてください。
 * また、WorkDataクラスは自由に修正してください。
 *
[出力イメージ]
部長: xx時間xx分
課長: xx時間xx分
一般: xx時間xx分
Z-7-31100: xx時間xx分
I-7-31100: xx時間xx分
T-7-30002: xx時間xx分
（省略）
194033: xx時間xx分
195052: xx時間xx分
195066: xx時間xx分
（省略）
 */
public class Q005 {
    public static void main(String[] args) throws IOException {
        // データファイルを読み込み
        InputStream is = Q005.class.getResourceAsStream("data.txt");
        Reader reader1 = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader1);
        String line;
        // 一行目はタイトルなので取得しない
        br.readLine();
        // 変数初期化
        Map<Integer, String> number = new HashMap<>();     // 社員番号
        Map<Integer, String> department = new HashMap<>(); // 部門
        Map<Integer, String> position = new HashMap<>();   // 役職
        Map<Integer, String> pcode = new HashMap<>();      // Pコード
        Map<Integer, Integer> worktime = new HashMap<>();   // 作業時間
        int count = 0; // インデックス
        while ((line = br.readLine()) != null) {
            // 一行をカンマごとに分解してセクションごとの値を取得する
            String[] sections = line.split(",", 0);
            number.put(count, sections[0]);
            department.put(count, sections[1]);
            position.put(count, sections[2]);
            pcode.put(count, sections[3]);
            worktime.put(count, Integer.parseInt(sections[4]));
            count++;
        }

        // 設問(1)の実行
        calcTimeByDepartment(position, worktime);
        // 設問(2)の実行
        calcTimeByPcode(pcode, worktime);
        // 設問(3)の実行
        calcTimeByNumber(number, worktime);

    }
    /* 役職ごとの作業時間算出メソッド
     * params Map<Integer, String> 役職, Map<Integer, Integer> 作業時間(分)
     * return なし
     */
    public static void calcTimeByDepartment(Map<Integer, String> post, Map<Integer, Integer> time) {
        // 結果格納用
        Map<String, Integer> result = new HashMap<>();
        for (int i = 0; i < post.size(); i++) {
            String tmpPost = post.get(i);
            // 連想配列に役職と作業時間を格納する
            if(result.containsKey(tmpPost)) {
                // 作業時間を加算
                Integer sum = result.get(tmpPost) + time.get(i);
                result.remove(tmpPost);
                result.put(tmpPost, sum);
            } else {
                // 新規に役職と作業時間を格納する
                result.put(tmpPost, time.get(i));
            }
        }

        // 結果の出力
        for (String i : result.keySet()) {
            String workTimes = calculation(result.get(i));
            System.out.print(i);
            System.out.print("：");
            System.out.println(workTimes);
        }
    }

    /* Pコードごとの作業時間算出メソッド
     * params Map<Integer, String> Pコード, Map<Integer, Integer> 作業時間(分)
     * return なし
     */
    public static void calcTimeByPcode(Map<Integer, String> pcode, Map<Integer, Integer> time) {
        // 結果格納用
        Map<String, Integer> result = new HashMap<>();
        for (int i = 0; i < pcode.size(); i++) {
            String tmpPost = pcode.get(i);
            // 連想配列にPコードと作業時間を格納する
            if(result.containsKey(tmpPost)) {
                // 作業時間を加算
                Integer sum = result.get(tmpPost) + time.get(i);
                result.remove(tmpPost);
                result.put(tmpPost, sum);
            } else {
                // 新規にPコードと作業時間を格納する
                result.put(tmpPost, time.get(i));
            }
        }

        // 結果の出力
        for (String i : result.keySet()) {
            String workTimes = calculation(result.get(i));
            System.out.print(i);
            System.out.print("：");
            System.out.println(workTimes);
        }
    }

    /* 社員番号ごとの作業時間算出メソッド
     * params Map<Integer, String> 社員番号, Map<Integer, Integer> 作業時間(分)
     * return なし
     */
    public static void calcTimeByNumber(Map<Integer, String> num, Map<Integer, Integer> time) {
        // 結果格納用
        Map<String, Integer> result = new HashMap<>();
        for (int i = 0; i < num.size(); i++) {
            String tmpPost = num.get(i);
            // 連想配列に社員番号と作業時間を格納する
            if(result.containsKey(tmpPost)) {
                // 作業時間を加算
                Integer sum = result.get(tmpPost) + time.get(i);
                result.remove(tmpPost);
                result.put(tmpPost, sum);
            } else {
                // 新規に社員番号と作業時間を格納する
                result.put(tmpPost, time.get(i));
            }
        }

        // 結果の出力
        for (String i : result.keySet()) {
            String workTimes = calculation(result.get(i));
            System.out.print(i);
            System.out.print("：");
            System.out.println(workTimes);
        }
    }

    /* 分を時間に変換するメソッド
     * params Integer 作業時間（xx分）
     * return String 作業時間（xx時間xx分）
     */
    public static String calculation(Integer workTimes) {
        int Hours = workTimes / 60;
        int Times = workTimes % 60;
        return Hours + "時間" + Times + "分";
    }
}
// 完成までの時間: 04時間 40分