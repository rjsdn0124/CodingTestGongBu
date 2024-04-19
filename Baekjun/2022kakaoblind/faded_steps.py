def solution(board, aloc, bloc):
    answer = 0
    sero = len(board)
    garo = len(board[0])
    dx = [1,0,-1,0]
    dy = [0,1,0,-1]
    # 둘 다 이기려고 한다.
    
    def dfs(x,y,nx,ny,isA):
        result = not isA
        if board[y][x] == 0:
            return result, 0
        board[y][x] = 0
        maxc = 0
        minc = 25
        for i in range(4):
            tx = x+dx[i]
            ty = y+dy[i]
            if 0 <= ty < sero and 0 <= tx < garo and board[ty][tx]:
                r,c = dfs(nx,ny,tx,ty,not isA)
                if r == isA:
                    minc = min(minc, c+1)
                    result = isA
                else:
                    maxc = max(maxc, c+1)
        count = minc if result == isA else maxc
        board[y][x] = 1
        return result, count
    r, answer = dfs(aloc[1],aloc[0],bloc[1],bloc[0],True)
    return answer