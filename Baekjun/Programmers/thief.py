def solution(money):
    answer = 0
    l = len(money)
    dp = [[0,0] for _ in range(l+1)]
    dp[1] = [money[0],0]
    dp[2] = [money[0],money[1]]
    dp[3] = [money[0],max(dp[2][1],money[2])]
    for i in range(3,l):
        dp[i+1][0] = max(dp[i-1][0] + money[i-1], dp[i][0])
        dp[i+1][1] = max(dp[i-1][1] + money[i], dp[i][1])
    answer = max(dp[-1])
    return answer