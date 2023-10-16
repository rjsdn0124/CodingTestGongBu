n=int(input())
cases=[int(input()) for _ in range(n)]
max_n=max(cases)
dp0=[1,0]
dp1=[0,1]

for i in range(2,max_n+1):
    dp0.append(dp0[i-1] + dp0[i-2])
    dp1.append(dp1[i-1] + dp1[i-2])

for c in cases:
    print(dp0[c],dp1[c])
