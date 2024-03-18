def solution(n, wires):
    answer = n
    arr = [[] for _ in range(n+1)]
    
    for [v1,v2] in wires:
        arr[v1].append(v2)
        arr[v2].append(v1)
    
    def rec(node,visited):
        res = 1
        for n in arr[node]:
            if visited[n] == 0:
                visited[n] = 1
                res += rec(n,visited)
        return res
    
    for [v1,v2] in wires:
        visited = [0 for _ in range(n+1)]
        visited[v1] = 1
        visited[v2] = 1
        answer = min(answer, abs(rec(v1,visited) - rec(v2,visited)))
    return answer