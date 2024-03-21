from queue import PriorityQueue
import sys

def solution(n, edge):
    answer = 0
    dic = {}
    for s,e in edge:
        if not e in dic:
            dic[e] = []
        if not s in dic:
            dic[s] = []
        dic[e].append(s)
        dic[s].append(e)
        
    res = [sys.maxsize for _ in range(n+1)]
    q = PriorityQueue()
    res[1] = 0
    q.put([res[1],1])
    maxv = 0
    while not q.empty():
        v,node = q.get()
        for nn in dic[node]:
            if res[nn] > v+1:
                res[nn] = v+1
                q.put([res[nn],nn])
                if maxv < res[nn]:
                    answer = 1
                    maxv = res[nn]
                elif maxv == res[nn]:
                    answer += 1
    
    return answer