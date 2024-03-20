import sys
sys.setrecursionlimit(100000)

def solution(nodeinfo):
    answer = [[],[]]
    dic = {}
    newinfo = []
    for i in range(len(nodeinfo)):
        x,y = nodeinfo[i]
        newinfo.append([-y,x,i])
    newinfo.sort()
    root = newinfo[0][2]
    dic[root] = [-1,-1]
    
    for y,x,i in newinfo[1:]:
        now = root
        while not i in dic:
            tx,ty = nodeinfo[now]
            if x > tx:
                if dic[now][1] >= 0:
                    now = dic[now][1]
                elif dic[now][1] < 0:
                    dic[now][1] = i
                    dic[i] = [-1,-1]
            else:
                if dic[now][0] >= 0:
                    now = dic[now][0]
                elif dic[now][0] < 0:
                    dic[now][0] = i
                    dic[i] = [-1,-1]
                    
    def dfs(n):
        answer[0].append(n+1)
        for nn in dic[n]:
            if nn >= 0:
                dfs(nn)
        answer[1].append(n+1)
    dfs(root)
    return answer