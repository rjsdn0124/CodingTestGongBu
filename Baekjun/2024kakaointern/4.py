# 현재 가진 패에서 n+1만드려면 필요한 카드 맥시멈 값.
# 코인 + 내면서 갈 수 있는 최댓값.
# 카드 내는게 우선 but 다음 숫자에 필요한 숫자가 나오면 코인 사용
def solution(coin, cards):
    answer = 1
    n = len(cards)
    cond = n+1
    point=n//3
    hand = cards[:point]
    arr = [0 for _ in range(n)]
    
    for i in range(n-1):
        for j in range(i+1,n):
            if cards[i] + cards[j] == cond:
                while j < n:
                    arr[j]+=1
                    j+=1
    print(cards)
    print(arr)

    prev_can=max(arr[:point])
    can_give=prev_can
    result = 0
    que = [[point, coin, can_give, result]]
    for p,c,cg,r in que:
        while p != n:
            if c > 0:
                if prev_can < arr[p]:
                    prev_can = arr[p]
                    c -=1
                    cg+=1
                if prev_can < arr[p+1]:
                    prev_can = arr[p+1]
                    if cards[p] + cards[p+1] == cond:
                        if c>=2:
                            que.append([p+2, c -2,cg+1,r+1])
                    else:
                        c -=1
                        cg+=1
                print(cg)
            if cg > 0:
                cg -= 1
                r += 1
                p += 2
            else:
                break
        answer = max(r,answer)       
    return answer