/**
 * @author jhkim
 * CheckArrange.java
 * @date [2017. 7. 11.] 오후 4:24:51
 */
package main.java.dart;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Map으로 저장하여 만들기
 * 1. 각 맵을 설정 (S.D.T)
 * 2. 순차적으로 맵을 돌며 Arrange 계산
 * 3. 적합한 Arragne는 List에 저장
 */
public class CheckArrangeByMap {
    LinkedHashMap<String, Integer> singleMap = new LinkedHashMap<>();
    LinkedHashMap<String, Integer> doubleMap = new LinkedHashMap<>();
    LinkedHashMap<String, Integer> tripleMap = new LinkedHashMap<>();
    static ArrayList<ArrayList<String>> resultList = new ArrayList<>();
    

    public static void main(String[] args) {
        int goalScore = 80;
        CheckArrangeByMap cabMap = new CheckArrangeByMap();
        cabMap.calcurate(goalScore);

        ArrayList<ArrayList<String>> alByThrowNum = cabMap.getByThrowNum(3);
//        System.out.println(alByThrowNum);
//        cabMap.printMaterFinishingByThree(alByThrowNum);
//        cabMap.printDoubleFinishingByThree(alByThrowNum);
        cabMap.printDoubleFinishingByTwo(alByThrowNum);
        System.out.printf("Goal Score is %d\n", goalScore);
    }

    /** 
     * @author jhkim
     * @date [2017. 8. 21.] 오후 4:04:37
     *
     * @param alByThrowNum
     */
    private void printDoubleFinishingByTwo(ArrayList<ArrayList<String>> alByThrowNum) {
        int cnt = 0;
        for (ArrayList<String> arrayList : alByThrowNum) {
            if (arrayList.size() == 2 
                    && arrayList.get(arrayList.size() - 1).endsWith("D")) {
//            if (arrayList.get(arrayList.size() - 1).equals("16D")) {
                System.out.println(arrayList);
                cnt++;
            }
        }
        System.out.println("printDoubleFinishingByTwo - " + cnt + "\n");
    }

    private void printDoubleFinishingByThree(ArrayList<ArrayList<String>> alByThrowNum) {
        int cnt = 0;
        for (ArrayList<String> arrayList : alByThrowNum) {
//            if (arrayList.get(arrayList.size() - 1).endsWith("D")) {
            if (arrayList.get(arrayList.size() - 1).equals("16D")) {
                System.out.println(arrayList);
                cnt++;
            }
        }
        System.out.println("printDoubleFinishingByThree - " + cnt + "\n");
    }

    private void printMaterFinishingByThree(ArrayList<ArrayList<String>> alByThrowNum) {
        int cnt = 0;
        for (ArrayList<String> arrayList : alByThrowNum) {
            if (arrayList.get(arrayList.size() - 1).endsWith("T")) {
                System.out.println(arrayList);
                cnt++;
            }
        }
        System.out.println("printMaterFinishingByThree - " + cnt + "\n");
    }

    /** 
     * @author jhkim
     * @date [2017. 8. 06.] 오후 8:01:34
     *
     */
    private ArrayList<ArrayList<String>> getByThrowNum(int throwNum) {
        int count = 0;
        ArrayList<ArrayList<String>> tempList = new ArrayList<>();
        for (ArrayList<String> al : resultList) {
            if (al.size() == throwNum) {
                count++;
                tempList.add(al);
            }
        }
        System.out.println("all is " + count);
        return tempList;
    }

    /** 
     * @author jhkim
     * @date [2017. 7. 11.] 오후 5:06:34
     *
     */
    private void calcurate(int goalScore) {
        int firstVal = 0;
        int secoundVal = 0;
        int thirdVal = 0;
        singleMap = getSingleMap();
        doubleMap = getDoubleMap();
        tripleMap = getTripleMap();
        
        LinkedHashMap<Integer, LinkedHashMap<String, Integer>> lhm = new LinkedHashMap<>();
        lhm.put(1, singleMap);
        lhm.put(2, doubleMap);
        lhm.put(3, tripleMap);
        
        ArrayList<String> tempList = new ArrayList<>();
        
        first:
        for (int i = 0; i < lhm.size(); i++) {
            LinkedHashMap<String, Integer> m1 = lhm.get(i + 1);
            for (String m1Key : m1.keySet()) {
                firstVal = m1.get(m1Key);
                if (firstVal == goalScore) {
                    tempList.add(m1Key);
                    resultList.add(tempList);
                    tempList = new ArrayList<>();
                    break first;
                }
                
                secound:
                for (int j = 0; j < lhm.size(); j++) {
                    LinkedHashMap<String, Integer> m2 = lhm.get(j + 1);
                    for (String m2Key : m2.keySet()) {
                        secoundVal = m2.get(m2Key);
                        if (firstVal + secoundVal == goalScore) {
                            tempList.add(m1Key);
                            tempList.add(m2Key);
                            resultList.add(tempList);
                            tempList = new ArrayList<>();
                        }
                        
                        third:
                        for (int k = 0; k < lhm.size(); k++) {
                            LinkedHashMap<String, Integer> m3 = lhm.get(k + 1);
                            for (String m3Key : m3.keySet()) {
                                thirdVal = m3.get(m3Key);
                                if (firstVal + secoundVal + thirdVal == goalScore) {
                                    tempList.add(m1Key);
                                    tempList.add(m2Key);
                                    tempList.add(m3Key);
                                    resultList.add(tempList);
                                    tempList = new ArrayList<>();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /** 
     * @author jhkim
     * @date [2017. 7. 31.] 오후 6:29:57
     *
     * @return
     */
    private LinkedHashMap<String, Integer> getSingleMap() {
        LinkedHashMap<String, Integer> hm = new LinkedHashMap<>();
        for (int i = 0; i < 20; i++) {
            hm.put(String.valueOf(i + 1), i + 1);
        }
        return hm;
    }

    /** 
     * @author jhkim
     * @date [2017. 7. 31.] 오후 6:29:54
     *
     * @return
     */
    private LinkedHashMap<String, Integer> getDoubleMap() {
        LinkedHashMap<String, Integer> hm = new LinkedHashMap<>();
        for (int i = 0; i < 20; i++) {
            hm.put((i + 1) + "D", (i + 1) * 2);
        }
        hm.put("Bull", 50);
        return hm;
    }

    /** 
     * @author jhkim
     * @date [2017. 7. 31.] 오후 6:29:46
     *
     * @return
     */
    private LinkedHashMap<String, Integer> getTripleMap() {
        LinkedHashMap<String, Integer> hm = new LinkedHashMap<>();
        for (int i = 0; i < 20; i++) {
            hm.put((i + 1) + "T", (i + 1) * 3);
        }
        return hm;
    }

}
