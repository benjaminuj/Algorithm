import java.util.*;

class Solution {
    List<Deque<String>> result;
    String[][] tickets;

    public String[] solution(String[][] tickets) {
        result = new ArrayList<>();
        this.tickets = tickets;

        boolean[] visited = new boolean[tickets.length];
        Deque<String> dq = new ArrayDeque<>();
        dq.push("ICN");

        dfs(visited, dq, 0);

        if (result.size() > 1) {
            Collections.sort(result, new Comparator<Deque<String>>() {
                @Override
                public int compare(Deque<String> o1, Deque<String> o2) {
                    Iterator<String> it1 = o1.iterator();
                    Iterator<String> it2 = o2.iterator();
                    while (it1.hasNext() && it2.hasNext()) {
                        String s1 = it1.next();
                        String s2 = it2.next();

                        if (!s1.equals(s2)) {
                            return s1.compareTo(s2);
                        }
                    }

                    return 0;
                }
            });
        }

        Deque<String> res = result.remove(0);
        String[] answer = new String[res.size()];

        int i = 0;
        for (String s : res) {
            answer[i++] = s;
        }

        return answer;
    }

    public void dfs(boolean[] visited, Deque<String> dq, int len) {
        if (len == tickets.length) {
            Deque<String> res = new ArrayDeque<>(dq);
            result.add(res);
            return;
        }
        
        String arrive = dq.peekLast();

        for (int i = 0; i < tickets.length; i++) {
            String[] tic = tickets[i];

            if (!visited[i] && arrive.equals(tic[0])) {
                dq.add(tic[1]);
                visited[i] = true;

                dfs(visited, dq, len + 1);

                visited[i] = false;
                dq.removeLast();
            }
        }
    }
}
