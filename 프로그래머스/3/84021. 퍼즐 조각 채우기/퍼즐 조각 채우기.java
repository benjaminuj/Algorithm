import java.util.*;

class Solution {
    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    private static Queue<Pos> q = new LinkedList<>();

    public int solution(int[][] game_board, int[][] table) {
        var answer = 0;
        var len = game_board.length;
        var gameBoardBlockNum = BFS(len, game_board, 0);
        var tableBlockNum = BFS(len, table, 1);

        var gameBoardBlockUsed = new int[gameBoardBlockNum];
        var gameBoardBlockCount = new int[gameBoardBlockNum];
        var tableBlockUsed = new int[tableBlockNum];
        var tableBlockCount = new int[tableBlockNum];

        var tables = new int[4][len][len];
        tables[0] = table;
        for (int i = 0; i < 3; i++) {
            tables[i + 1] = rotate(tables[i]);
        }

        for (int y = 0; y < len; y++) {
            for (int x = 0; x < len; x++) {
                gameBoardBlockCount[game_board[y][x]]++;
                tableBlockCount[tables[0][y][x]]++;
            }
        }

        for (int y = 0; y < len; y++) {
            for (int x = 0; x < len; x++) {
                if (game_board[y][x] < 2 || gameBoardBlockUsed[game_board[y][x]] == 1) {
                    continue;
                }

                var count = gameBoardBlockCount[game_board[y][x]];
                if (fill(y, x, game_board, count, tables[0], tableBlockCount, tableBlockUsed)
                        || fill(y, x, game_board, count, tables[1], tableBlockCount, tableBlockUsed)
                        || fill(y, x, game_board, count, tables[2], tableBlockCount, tableBlockUsed)
                        || fill(y, x, game_board, count, tables[3], tableBlockCount, tableBlockUsed)) {

                    gameBoardBlockUsed[game_board[y][x]] = 1;
                    System.out.println(count);
                    answer += count;
                }
            }
        }

        return answer;
    }

    private int BFS(int len, int[][] map, int ground) {
        q.clear();
        var blockNum = 2;
        var visited = new int[len][len];

        for (int y = 0; y < len; y++) {
            for (int x = 0; x < len; x++) {
                if (map[y][x] != ground) {
                    continue;
                }

                q.add(new Pos(y, x));
                map[y][x] = blockNum;
                visited[y][x] = 1;

                while (!q.isEmpty()) {
                    var pos = q.poll();

                    for (int i = 0; i < 4; i++) {
                        var ny = pos.y + dy[i];
                        var nx = pos.x + dx[i];

                        if (ny < 0 || ny >= len || nx < 0 || nx >= len) {
                            continue;
                        }

                        if (map[ny][nx] != ground) {
                            continue;
                        }

                        if (visited[ny][nx] == 1) {
                            continue;
                        }

                        map[ny][nx] = blockNum;
                        visited[ny][nx] = 1;
                        q.add(new Pos(ny, nx));
                    }
                }
                blockNum++;
            }
        }

        return blockNum;
    }

    private int[][] rotate(int[][] source) {
        var q = new LinkedList<Integer>();
        var target = new int[source.length][source.length];

        for (int y = 0; y < source.length; y++) {
            for (int x = 0; x < source.length; x++) {
                q.add(source[y][x]);
            }
        }

        for (int x = source.length - 1; x >= 0; x--) {
            for (int y = 0; y < source.length; y++) {
                target[y][x] = q.poll();
            }
        }

        return target;
    }

    private boolean fill(int mapY, int mapX, int[][] map, int count, int[][] table, int[] tableBlockCounts, int[] tableBlockUsed) {
        var visited = new int[table.length][table.length];

        for (int y = 0; y < table.length; y++) {
            for (int x = 0; x < table.length; x++) {
                if (table[y][x] == 0) {
                    continue;
                }

                if (tableBlockUsed[table[y][x]] == 1) {
                    continue;
                }

                if (count != tableBlockCounts[table[y][x]]) {
                    continue;
                }

                q.clear();
                q.add(new Pos(y, x));
                visited[y][x] = 1;

                var tableBlockCount = 1;

                while (!q.isEmpty()) {
                    var pos = q.poll();

                    for (int i = 0; i < 4; i++) {
                        var ny = pos.y + dy[i];
                        var nx = pos.x + dx[i];

                        if (ny < 0 || ny >= table.length || nx < 0 || nx >= table.length) {
                            continue;
                        }

                        if (table[ny][nx] == 0 || table[ny][nx] != table[y][x]) {
                            continue;
                        }

                        if (visited[ny][nx] == 1) {
                            continue;
                        }

                        var nMapY = mapY + (ny - y);
                        var nMapX = mapX + (nx - x);
                        if (nMapY < 0 || nMapY >= table.length || nMapX < 0 || nMapX >= table.length
                                || map[nMapY][nMapX] != map[mapY][mapX]) {
                            q.clear();
                            break;
                        }

                        visited[ny][nx] = 1;
                        q.add(new Pos(ny, nx));
                        tableBlockCount++;
                    }
                }

                if (tableBlockCount == count) {
                    tableBlockUsed[table[y][x]] = 1;
                    return true;
                }
            }
        }

        return false;
    }

    private static class Pos {
        private int y;
        private int x;

        private Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}