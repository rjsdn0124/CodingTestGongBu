from collections import deque
def solution(game_board, table):
    answer = 0
    l = len(game_board)
    dx = [1,0,-1,0]
    dy = [0,1,0,-1]
    
    def bfs(j,i, visited, arr, flag):
        q = deque()
        q.append([j,i])
        b = [[0,0]]
        while q:
            x,y = q.popleft()
            for k in range(4):
                nx = x + dx[k]
                ny = y + dy[k]
                if 0 <= nx < l and 0 <= ny < l:
                    if visited[ny][nx] == 0 and arr[ny][nx] == flag:
                        b.append([nx-j,ny-i])
                        q.append([nx,ny])
                    visited[ny][nx] = 1
        return b
    
    def rotate(block):
        result = []
        u = 50
        l = 50
        for x,y in block:
            result.append([-y,x])
            if u >= x:
                u = x
                if l > -y:
                    l = -y

        for j in range(len(block)):
            result[j][0] -= l
            result[j][1] -= u
        return result
    
    def checker(blank,block):
        if len(blank) == len(block):
            blank.sort()
            for i in range(4):
                block.sort()
                if blank == block:
                    return True
                block = rotate(block)
        return False
    
    blocks = []
    visited_t = [[0 for _ in range(l)] for _ in range(l)]
    for i in range(l):
        for j in range(l):
            if visited_t[i][j] == 0 and table[i][j] == 1:
                visited_t[i][j] = 1
                blocks.append(bfs(j,i,visited_t,table,1))
            else:
                visited_t[i][j] = 1
                
    used_block = [0 for _ in range(len(blocks))]
    visited_gb = [[0 for _ in range(l)] for _ in range(l)]
    for i in range(l):
        for j in range(l):
            if visited_gb[i][j] == 0 and game_board[i][j] == 0:
                visited_gb[i][j] = 1
                blank = bfs(j,i,visited_gb,game_board,0)
                
                for k in range(len(blocks)):
                    if used_block[k] == 0:
                        if checker(blank,blocks[k]):
                            answer += len(blocks[k])
                            used_block[k] = 1
                            break
            else:
                visited_gb[i][j] = 1
    return answer