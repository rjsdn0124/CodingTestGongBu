from queue import PriorityQueue
import sys
def solution(n, s, a, b, fares):
    answer = sys.maxsize
    dic = {}
    for c,d,f in fares:
        if not c in dic:
            dic[c] = []
        if not d in dic:
            dic[d] = []
        dic[c].append([f,d])
        dic[d].append([f,c])
        
    ctod = [[0,0] for _ in range(n+1)]
    ston = []
    varr = [0 for _ in range(n+1)]
    res = [sys.maxsize for _ in range(n+1)]
    
    for i in range(1,n+1):
        tvarr = varr[:]
        tres = res[:]
        tres[i] = 0
        q = PriorityQueue()
        q.put([tres[i],i])
        j = 0
        while not q.empty() and j < n:
            [minv, minind] = q.get()
            if tvarr[minind] == 0:
                tvarr[minind] = 1
                j += 1
                if tvarr[a] == 1 and tvarr[b] == 1:
                    ctod[i] = ([tres[a],tres[b]])
                    break
                if  minind in dic:
                    for f,d in dic[minind]:
                        if tvarr[d] == 0:
                            tres[d] = min(tres[d], f+tres[minind])
                            q.put([tres[d],d])
        if i == s:
            ston = tres
    for i in range(1,n+1):
        answer = min(ston[i] + sum(ctod[i]),answer)
    
    return answer