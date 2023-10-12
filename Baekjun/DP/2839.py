sugar=int(input())
dp=[0,-1,-1,1,-1,1]
for i in range(6, sugar + 1):
    if dp[i-3] == -1 and dp[i-5] == -1:
        dp.append(-1)
    elif dp[i-3] == -1:
        dp.append(dp[i-5] + 1)
    elif dp[i-5] == -1:
        dp.append(dp[i-3] + 1)
    else:
        dp.append(dp[i-3] + 1 if dp[i-3] < dp[i-5] else dp[i-5] + 1)

print(dp[sugar])