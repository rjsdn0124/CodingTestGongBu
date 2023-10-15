l=int(input())
wines=[0]
for _ in range(l):
    wines.append(int(input()))
result = [0, wines[1],wines[1]+wines[2]] if l >= 2 else [0,wines[1]]
for i in range(3,l+1):
    result.append(max(result[-1],wines[i]+result[i-2],wines[i]+wines[i-1]+result[i-3]))
print(result[-1])