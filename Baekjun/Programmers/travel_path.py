from collections import deque

def solution(tickets):
    l = len(tickets)
    tup = {}
    
    tickets.sort()
    for ticket in tickets:
        if not ticket[0] in tup:
            tup[ticket[0]] = []
        if not ticket[1] in tup:
            tup[ticket[1]] = []      
        tup[ticket[0]].append([ticket[1], 1])

    def rec(arr):
        result = []
        if len(arr) == l+1:
            return arr
        elif len(arr) < l+1:
            now = arr[-1]
            for i,v in enumerate(tup[now]):
                if v[1] >= 1:
                    tup[now][i][1] = 0
                    result = rec(arr+[tup[now][i][0]])
                    if result:
                        return result
                    else:
                        tup[now][i][1] += 1
        return result
    
    return rec(["ICN"])