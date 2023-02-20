import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    public Integer[] solution(int[] numlist, int n) {
        List<Integer> solution = IntStream.of(numlist).boxed().collect(Collectors.toList());

        solution.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int o1Abs = Math.abs(o1 - n);
                int o2Abs = Math.abs(o2 - n);

                if (o1Abs == o2Abs) {
                    if (o1 > o2) return -1;
                    else if (o1 < o2) return 1;
                } else {
                    return o1Abs - o2Abs;
                }

                return 0;
            }
        });

        return solution.toArray(new Integer[0]);
    }
}