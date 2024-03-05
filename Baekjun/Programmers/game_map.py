from collections import deque

def solution(maps):
    dx = [1,0,-1,0]
    dy = [0,1,0,-1]
    dst = [len(maps[0]) - 1, len(maps) - 1]
    
    q = deque()
    q.append([0,0,0])
    
    while q:
        x,y,answer = q.popleft()
        answer += 1
        if x == dst[0] and y == dst[1]:
            return answer
        else:
            for i in range(4):
                nowx = x+dx[i]
                nowy = y+dy[i]
                if 0 <= nowx <= dst[0] and 0 <= nowy <= dst[1] and maps[nowy][nowx] == 1:
                    maps[nowy][nowx] = 0
                    q.append([nowx,nowy, answer])
    
                    
    return -1