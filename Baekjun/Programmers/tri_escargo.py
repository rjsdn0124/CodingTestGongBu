def solution(n):
    answer = []
    arr = [[0 for _ in range(n)] for _ in range(n)]
    
    s = sum(range(n+1))
    i = 1
    x = 0
    y = 0
    dx = [0,1,-1]
    dy = [1,0,-1]
    d = 0
    while s >= i:
        arr[y][x] = i
        i+=1
        x+=dx[d]
        y+=dy[d]
        if y >= n or y < 0 or x >= n or x < 0 or arr[y][x] > 0:
            x -= dx[d]
            y -= dy[d]
            d=(d+1)%3
            x += dx[d]
            y += dy[d]
        
    for i in range(n):
        for j in range(i+1):
            answer.append(arr[i][j])
    return answer