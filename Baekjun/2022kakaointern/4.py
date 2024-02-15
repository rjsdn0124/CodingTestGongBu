from queue import PriorityQueue
def solution(n, paths, gates, summits):
    answer = []
    graph = [[10000000,[]] for _ in range(n+1)]
    que = PriorityQueue()
    for g in gates:
        graph[g][0] = 0
    for s in summits:
        graph[s][0] = -1
    for p in paths:
        n1 = p[0]
        n2 = p[1]
        i = p[2]
        if graph[n1][0] == 0:
            que.put([i,n2,n1])
            graph[n1][1].append([i,n2])
        elif graph[n2][0] == 0:
            que.put([i,n1,n2])
            graph[n2][1].append([i,n1])
        else:
            graph[n2][1].append([i,n1])
            graph[n1][1].append([i,n2])
            
    while not que.empty():
        i,node,prev = que.get()
        if graph[node][0] == -1:
            answer = [node,i]
            while not que.empty():
                i,node,prev = que.get()
                if answer[1] != i:
                    return answer
                if graph[node][0] == -1:
                    answer[0] = min(node,answer[0])
                elif graph[node][0] > i:
                    for g in graph[node][1]:
                        if g[1] != prev:
                            que.put([i,g[1],node])
            break
        else:
            if graph[node][0] > i:
                graph[node][0] = i
                for g in graph[node][1]:
                    if g[1] != prev:
                        maximum = max(g[0],i)
                        que.put([maximum,g[1],node])

    return answer