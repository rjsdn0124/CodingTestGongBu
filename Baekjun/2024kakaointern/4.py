# 실제 풀이
def solution(coin, cards):
    answer = 0
    dic = {}
    l = len(cards)
    can_count = 1
    for i in range(l // 3):
        if (l + 1 - cards[i]) in dic:
            can_count += 1
        dic[cards[i]] = i
        
    ndic={}
    oc = 0
    tc = 0
    i = l // 3
    while i < l and can_count > 0:
        can_count -= 1
        answer += 1 
        if (l + 1 - cards[i]) in dic:
            oc += 1
        if (l + 1 - cards[i+1]) in dic:
            oc += 1
        if (l + 1 - cards[i]) in ndic:
            tc += 1
        ndic[cards[i]] = i
        if (l + 1 - cards[i+1]) in ndic:
            tc += 1
        else:
            ndic[cards[i+1]] = i+1
        if can_count == 0:
            if coin > 0 and oc > 0:
                can_count += 1
                oc -= 1
                coin -= 1
            elif coin > 1 and tc > 0:
                can_count += 1
                tc -= 1
                coin -= 2
                
        i += 2
        
    if i == l and can_count > 0:
        answer += 1
            
    return answer

# 카카오 코테칠 때 풀었던 풀이.
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