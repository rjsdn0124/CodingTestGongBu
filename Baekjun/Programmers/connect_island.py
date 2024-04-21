def solution(n, costs):
    answer = 0
    visited = [0 for _ in range(n)]
    costs.sort(key = lambda x:x[2])
    temp = 1
    for s,e,c in costs:
        vs = visited[s]
        ve = visited[e]
        if vs > 0 and ve == 0:
            visited[e] = vs
            answer += c
        elif ve > 0 and vs == 0:
            visited[s] = ve
            answer += c
        elif not ve == vs:
            for i in range(n):
                if visited[i] == ve:
                    visited[i] = vs
            answer += c
        elif ve == 0 and vs == 0:
            visited[e] = temp
            visited[s] = temp
            temp += 1
            answer += c
    return answer