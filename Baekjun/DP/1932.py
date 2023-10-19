size=int(input())
dp=[]

for i in range(1, size + 1):
    temp=list(map(int, input().split()))
    if i > 1:
        for j in range(i):
            if j > 0 and j < i - 1:
                temp[j] += max(dp[j-1:j+1])
            else:
                temp[j] += dp[0] if j == 0 else dp[j-1]
    dp = temp
print(max(dp))