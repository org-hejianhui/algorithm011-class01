/**
 * <p>
 * 208. 实现 Trie (前缀树)
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-08-09 17:35
 * @see LeetCode208
 * @since JDK1.8
 */
public class LeetCode208 {

    static class Trie {
        // 26个单词
        final static int MAX_SIZE= 26;
        // 表示是否叶子结点
        boolean isEnd;
        // 表示子节点
        Trie[] childs;

        /** Initialize your data structure here. */
        public Trie() {
            this.isEnd = false;
            this.childs = new Trie[26];
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            if (word == null || word.length() == 0) return;
            Trie curr = this;
            char[] words = word.toCharArray();
            for (int i = 0; i < words.length; i++) {
                int n = words[i] - 'a';
                if (curr.childs[n] == null) {
                    curr.childs[n] = new Trie();
                }
                curr = curr.childs[n];
            }
            curr.isEnd = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;

        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Trie node = searchPrefix(prefix);
            return node != null;
        }

        public Trie searchPrefix(String word) {
            Trie node = this;
            char[] words = word.toCharArray();
            for (int i = 0; i < words.length; i++) {
                node = node.childs[words[i] - 'a'];
                if (node == null) return null;
            }
            return node;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 true
        System.out.println(trie.search("app"));     // 返回 false
        System.out.println(trie.startsWith("app"));// 返回 true
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 true

    }
}
