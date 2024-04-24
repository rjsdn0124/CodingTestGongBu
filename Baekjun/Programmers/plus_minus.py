import sys
def solution(arr):
    answer = -1
    ms = sys.maxsize
    n = len(arr) // 2 + 1
    dp = [[[ms,-ms] for _ in range(n)] for _ in range(n)]
    
    for step in range(n):
        for i in range(n-step):
            j = i + step
            if step == 0:
                num = int(arr[2*i])
                dp[i][j][0] = num
                dp[i][j][1] = num
            else:
                for k in range(i,j):
                    if arr[2*k+1] == '+':
                        dp[i][j][0] = min(dp[i][j][0],dp[i][k][0]+dp[k+1][j][0])
                        dp[i][j][1] = max(dp[i][j][1],dp[i][k][1]+dp[k+1][j][1])
                    else:
                        dp[i][j][0] = min(dp[i][j][0],dp[i][k][0]-dp[k+1][j][1])
                        dp[i][j][1] = max(dp[i][j][1],dp[i][k][1]-dp[k+1][j][0])

    answer = dp[0][n-1][1]
    return answer