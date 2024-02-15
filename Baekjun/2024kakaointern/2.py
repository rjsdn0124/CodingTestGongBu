def solution(edges):
    answer = [0,0,0,0]
    maxi = 0
    graph = [[0,[]] for _ in range(1000001)]
    passed=0
    for e in edges:
        s = e[0]
        end = e[1]
        maxi = max([s,end,maxi])
        graph[s][1].append(end)
        graph[s][0] += 1
        graph[end][0] -= 1

    for i in range(1,1000001):
        sc,g = graph[i]
        if sc >= 2:
            answer[0]=i
    sp = answer[0]
    for node in graph[sp][1]:
        sc, g = graph[node]
        if len(g) == 2:
            answer[3] += 1 
            
        elif len(g) == 1:
            temp = [g[0]]
            start = node
            for t in temp:
                if len(graph[t][1]) == 2:
                    answer[3] += 1
                    break
                elif start == t:
                    answer[1] += 1
                    break
                elif len(graph[t][1]) == 0:
                    answer[2] += 1
                    break
                temp.append(graph[t][1][0])
            
            
        elif len(g) == 0:
            answer[2] +=1

    return answer