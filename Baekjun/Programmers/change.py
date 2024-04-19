def solution(n, money):
    answer = 0
    dp = [0 for _ in range(n+1)]
    money.sort()
    dp[0] = 1
    for m in money:
        for p in range(m,n+1):
            dp[p] += dp[p-m]
            dp[p] %= 1000000007
    return dp[-1]