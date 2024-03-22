from collections import deque
def solution(rectangle, characterX, characterY, itemX, itemY):
    answer = 0
    l = max(max(rectangle[i]) for i in range(len(rectangle))) * 2
    togo = [[[] for _ in range(l+1)] for _ in range(l+1)]
    visited = [[0 for _ in range(l+1)] for _ in range(l+1)]
    characterX *=2
    characterY *=2
    itemX *=2
    itemY *=2
    for sx,sy,ex,ey in rectangle: 
        sx *=2
        sy *=2
        ex *=2
        ey *=2
        for i in range(sx+1,ex):
            togo[sy][i] += [[1,0],[-1,0]]
            togo[ey][i] += [[1,0],[-1,0]]
            for j in range(sy+1,ey):
                visited[j][i] = 1
        for i in range(sy+1,ey):
            togo[i][sx] += [[0,1],[0,-1]]
            togo[i][ex] += [[0,1],[0,-1]]
            for j in range(sx+1,ex):
                visited[i][j] = 1
        togo[sy][sx] = [[1,0],[0,1]]
        togo[sy][ex] = [[-1,0],[0,1]]
        togo[ey][sx] = [[1,0],[0,-1]]
        togo[ey][ex] = [[-1,0],[0,-1]]
            
    q = deque()
    q.append([characterX,characterY,0])
    while q:
        x,y,c = q.popleft()
        for dx,dy in togo[y][x]:
            nx = x+dx
            ny = y+dy
            if nx == itemX and ny == itemY:
                return (c+1)//2
            elif 0 <= nx <= l and 0 <= ny <= l and visited[ny][nx] == 0 and togo[ny][nx]:
                visited[ny][nx] = 1
                q.append([nx,ny,c+1])
    return answer