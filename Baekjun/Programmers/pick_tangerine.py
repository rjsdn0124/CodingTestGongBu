def solution(k, tangerine):
    answer = 0
    s = max(tangerine)
    arr = [0 for _ in range(s+1)]
    for t in tangerine:
        arr[t] +=1
    arr.sort(reverse=True)
    for n in arr:
        k -= n
        answer += 1
        if k <= 0:
            break
    return answer