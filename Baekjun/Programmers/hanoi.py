def solution(n):
    # dp로 풀기
    # arr = [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
    # dp = [[[[] for _ in range(4)] for _ in range(4)] for _ in range(n+1)]
    # for i in range(1,n+1):
    #     for [x,y,z] in arr:
    #         dp[i][x][y] = dp[i-1][x][z] + [[x,y]] + dp[i-1][z][y]
    # return dp[-1][1][3]

    # 재귀로 풀기
    def rec(x,y,z,k):
        if k == 1:
            return [[x,y]]
        else:
            return rec(x,z,y,k-1) + [[x,y]] + rec(z,y,x,k-1)
    return rec(1,3,2,n)