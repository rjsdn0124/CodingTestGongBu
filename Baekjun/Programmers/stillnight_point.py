def solution(n, works):
    answer = 0
    l= len(works)
    arr = [0 for _ in range(50001)]
    i = 0
    for w in works:
        arr[w] += 1
        i = max(i,w)
    
    while n > 0:
        x = 0
        if arr[i] <= n:
            n -= arr[i]
            arr[i-1] += arr[i]
            arr[i] = 0
            i -= 1
        else:
            arr[i-1] += n
            arr[i] -= n
            n = 0
            
    for j in range(i+1):
        answer += j**2 * arr[j]
    return answer