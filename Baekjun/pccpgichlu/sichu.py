from collections import deque
# DFS하려면 재귀 회수 설정해줘야하네!
import sys
sys.setrecursionlimit(200000)

def solution(land):
    n = len(land)
    m = len(land[0])
    answer = [0 for _ in range(m)]
    visited = [[0 for _ in range(m)] for _ in range(n)]

    dx = [1,0,-1,0]
    dy = [0,1,0,-1]
    
    # DFS
#     def dfs(x,y,rows):
#         res = 1
#         for k in range(4):
#             nx = x + dx[k]
#             ny = y + dy[k]
#             if 0 <= nx < m and 0 <= ny < n and visited[ny][nx] == 0:
#                 visited[ny][nx] = 1
#                 if land[ny][nx] == 1:
#                     rows[nx] = nx
#                     res += dfs(nx,ny,rows)
#         return res
            
#     for i in range(n):
#         for j in range(m):
#             if visited[i][j] == 0:
#                 visited[i][j] = 1
#                 if land[i][j] == 1:
#                     rows = {}
#                     rows[j] = j
#                     oilnum = dfs(j,i,rows)
#                     for v in rows:
#                         answer[v] += oilnum
                        
    
    # BFS
    for i in range(n):
        for j in range(m):
            if visited[i][j] == 0:
                visited[i][j] = 1
                if land[i][j] == 1:
                    rows = {}
                    oilnum = 0
                    q = deque()
                    q.append([i,j])
                    while q:
                        y,x = q.popleft()
                        rows[x] = x
                        oilnum += 1
                        for k in range(4):
                            nx = x + dx[k]
                            ny = y + dy[k]
                            if 0 <= nx < m and 0 <= ny < n and visited[ny][nx] == 0:
                                visited[ny][nx] = 1
                                if land[ny][nx] == 1:
                                    q.append([ny,nx])
                        
                    for v in rows:
                        answer[v] += oilnum
    return max(answer)