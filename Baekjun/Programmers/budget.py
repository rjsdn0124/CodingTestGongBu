def solution(d, budget):
    answer = 0
    d.sort()
    for v in d:
        budget -= v
        if budget < 0:
            return answer
        answer += 1
        
    return answer