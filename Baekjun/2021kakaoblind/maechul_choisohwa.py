import sys
def solution(sales, links):
    answer = 0
    arr = [[] for i in range(len(sales)+1)]
    for sn,en in links:
        arr[sn].append(en)

    def dfs(node):
        nu = 0
        u = sales[node-1]
        if len(arr[node]) > 0:
            flag = True
            v = 0
            minm = sys.maxsize
            for cn in arr[node]:
                cnu,cu = dfs(cn)
                if cnu > cu:
                    flag = False
                v += min(cnu,cu) 
                minm = min(minm,cu-cnu)
            nu += v + (minm if flag else 0)
            u += v
        return [nu,u]
    answer = min(dfs(1))
    return answer