n=int(input())
apt=[list(range(15))for _ in range(15)]


for i in range(1, 15):
    for j in range(1, 15):
        apt[i][j] = sum(apt[i-1][1:j+1])

for _ in range(n):
    print(apt[int(input())][int(input())])
