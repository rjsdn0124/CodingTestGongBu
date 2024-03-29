n,k=map(int,input().split())
m=[[0,0]]
for i in range(n):
    w,v=map(int,input().split())
    m.append([w,v])

dp=[[0 for _ in range(k+1)] for _ in range(n+1)]

for i in range(1, n+1):
    for j in range(1, k+1):
        iw=m[i][0]
        iv=m[i][1]
        if j < iw:
            dp[i][j]=dp[i-1][j]
        else:
            dp[i][j] = max(dp[i-1][j], dp[i-1][j-iw]+iv)
    
print(dp[n][k])