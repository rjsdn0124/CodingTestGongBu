def solution(n, results):
    answer = 0
    dicw = {i:[] for i in range(1,n+1)}
    dicl = {i:[] for i in range(1,n+1)}
    for a,b in results:
        dicw[a].append(b)
        dicl[b].append(a)
    
    def dfs(player,visited,dic):
        for p in dic[player]:
            if visited[p] == 0:
                visited[p] = 1
                dfs(p,visited,dic)
            
        
    for i in range(1,n+1):
        visited = [0 for _ in range(n+1)]
        visited[i] = 1
        dfs(i,visited,dicw)
        s= sum(visited)
        visited = [0 for _ in range(n+1)]
        visited[i] = 1
        dfs(i,visited,dicl)
        s+= sum(visited)
        if s == n+1:
            answer += 1
        
    return answer