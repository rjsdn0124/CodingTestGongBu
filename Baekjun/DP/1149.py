n=int(input())

dp = [0,0,0]
for _ in range(n):
    temp=[*map(int,input().split())]
    dp=[temp[0]+min(dp[1],dp[2]), temp[1]+min(dp[0],dp[2]), temp[2]+min(dp[0],dp[1])]
print(min(dp))