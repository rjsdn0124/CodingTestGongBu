def solution(n):
    answer = 0
    dp = [0,3]
    for i in range(2,n // 2 + 1):
        sum = 3*dp[i-1] + 2
        for j in range(2,i):
            sum += 2*dp[i-j]
        dp.append(sum % 1000000007)
    return dp[-1]