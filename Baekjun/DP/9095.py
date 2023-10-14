cn=int(input())
result=[0,1,2,4]
for _ in range(cn):
    tc=int(input())
    l=len(result)
    for i in range(l, tc+1):
        result.append(result[i-1] + result[i-2] + result[i-3])
    print(result[tc])