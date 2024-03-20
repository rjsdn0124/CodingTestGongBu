# 카카오식 풀이.
def solution(info, edges):
    answer = 0
    goal = len(info) - sum(info)
    dic = {}
    for [x,y] in edges:
        if not x in dic:
            dic[x] = []
        if not y in dic:
            dic[y] = []
        dic[x].append(y)
        
    def dfs(n,s,w,varr):
        res = 0
        s += info[n] ^ 1
        w += info[n]
        if s > w:
            res = max(res, s)
            for nn in dic[n]:
                varr.append(nn)
        for i in range(len(varr)):
            res = max(res,dfs(varr[i],s,w,varr[:i]+ varr[i+1:]))
        return res
        
    answer = dfs(0,0,0,[])
    return answer