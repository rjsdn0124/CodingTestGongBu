def solution(routes):
    answer = 0
    routes.sort(key=lambda x: x[1])
    t = -30000
    for s,e in routes:
        if t < s:
            t = e
            answer += 1
    return answer