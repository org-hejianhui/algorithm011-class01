package 单词接龙;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * <p>
 * 单词接龙
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-07-19 22:54
 * @see Solution
 * @since JDK1.8
 */
public class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 先把 wordList 放入哈希表里面，便于后面判断单词是否在wordList里
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }
        // 无需判断 beginWord
        wordSet.remove(beginWord);

        // 图的广度优先遍历，必须使用的队列和表示是否访问过的 visited （数组，哈希表）
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        // 用于记录是否被访问过的单词
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int wordLen = beginWord.length();
        // 包含起点，因此初始化的步长为1
        int step = 1;
        while(!queue.isEmpty()) {
            int currentSize = queue.size();
            for (int i = 0; i< currentSize; i++) {
                // 依次遍历当前队列中的单词
                String currentWord = queue.poll();
                char[] charArray = currentWord.toCharArray();
                // 每个字符左转换
                for(int j = 0; j < wordLen; j++) {
                    // 当前需转换字符,一轮之后应该重置，否则结果不正确
                    char orginChar = charArray[j];
                    // 尝试26个字母的转换
                    for (char k = 'a'; k <= 'z'; k++) {
                        // 被转换的与需转换的一致，则跳过
                        if(k == orginChar) continue;
                        // 获取替换字符之后的单词
                        charArray[j] = k;
                        String nextWord = String.valueOf(charArray);

                        // 判断单词是否在 wordSet 字典中
                        if(wordSet.contains(nextWord)) {
                            // 判断「替换单词」是否等于 目标单词
                            if (nextWord.equals(endWord)) {
                                return step + 1;
                            }

                            // 判断 「替换单词」是否被访问过
                            if(!visited.contains(nextWord)) {
                                queue.add(nextWord);
                                visited.add(nextWord);
                            }
                        }
                    }
                    // 每结束一轮后重置回原值
                    charArray[j] = orginChar;
                }
            }
            step++;
        }
        return 0;
    }
}
