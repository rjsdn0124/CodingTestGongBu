import sys 
sys.setrecursionlimit(10000)

def solution(maps):
    answer = []
    marr = []
    for m in maps:
        arr = []
        for c in m:
            if c == 'X':
                arr.append(0)
            else:
                arr.append(int(c))
        marr.append(arr)
                
    g = len(marr[0])
    s = len(marr)
    dx = [1,0,-1,0]
    dy = [0,1,0,-1]
    
    visited = [[0 for _ in range(g)] for _ in range(s)]
    
    def dfs(i,j):
        res = marr[i][j]
        for d in range(4):
            x = j + dx[d]
            y = i + dy[d]
            if 0<=x<g and 0<=y<s and marr[y][x] > 0 and visited[y][x] == 0:
                visited[y][x] = 1
                res += dfs(y,x)
        return res
    
    for i in range(s):
        for j in range(g):
            if marr[i][j] > 0 and visited[i][j] == 0:
                visited[i][j] = 1
                answer.append(dfs(i,j))
    answer.sort()
    if len(answer) == 0:
        answer = [-1]
    return answer