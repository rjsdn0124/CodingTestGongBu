def solution(arr):
    answer = 1
    for n in arr:
        temp = answer * n
        maxt = max(answer, n)
        mint = min(answer, n)
        while temp > 0:
            if temp % mint == 0:
                answer = temp
            temp -= maxt
        
    return answer