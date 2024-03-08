def solution(dice):
    answer = [] 
    l = len(dice)
    sum_arr = []
    d_arr = []
    
    def dfs(i,depth,darr):
        res = []
        new_arr = darr+[i+1]
        if depth == l//2:
            d_arr.append(new_arr)
            return [dice[i]]
        else:
            for j in range(i+1,l):
                for na in dfs(j,depth+1,new_arr):
                    temp = []
                    for n in na:
                        for d in dice[i]:
                            temp.append(d+n)
                    res.append(temp)
        return res
    
    for i in range(0,l//2 + 1):
        for a in dfs(i,1,[]):
            sum_arr.append(a)
            
    mw = 0
    n = len(sum_arr)
    for i in range(n):
        a = sum_arr[i]
        b = sum_arr[n-i-1]
        a.sort()
        b.sort()
        w = 0
        y = 0
        for tx in range(len(a)):
            w+=y
            for tyi in range(y, len(b)):
                if a[tx] <= b[tyi]:
                    y = tyi
                    break
                else:
                    w += 1
        if w > mw:
            answer = d_arr[i]
            mw = w
    return answer