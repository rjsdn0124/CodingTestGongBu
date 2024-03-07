def solution(n, tops):
    dp = [1,2 if tops[0] == 0 else 3]
    
    for i in range(2,2*n+1):
        if tops[(i-1)//2] == 0:
            dp.append(dp[i-2] + dp[i-1])
        else:
            if i % 2 == 0:
                dp.append(dp[i-2] + dp[i-1])
            else:
                dp.append(2 * dp[i-1] + dp[i-2])
        dp[i] %= 10007
    return dp[-1]