def solution(board):
    answer = 0
    n = len(board)
    dx = [1,1,0,-1,-1,-1,0,1]
    dy = [0,-1,-1,-1,0,1,1,1]
    safe = [[0 for _ in range(n)] for _ in range(n)]
    
    for i in range(n):
        for j in range(n):
            if board[i][j] == 1:
                safe[i][j] = 1
                for k in range(8):
                    x = j + dx[k]
                    y = i + dy[k]
                    if 0<=x<n and 0<=y<n:
                        safe[y][x] = 1
    answer = n*n - sum(sum(safe[i]) for i in range(n))
    return answer