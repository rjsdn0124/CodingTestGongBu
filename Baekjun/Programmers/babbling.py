def solution(babbling):
    answer = 0
    dic = {}
    words = ["aya","ye", "woo", "ma"]
    
    def dfs(arr):
        res = []
        if len(arr) == 1:
            return arr
        for i in range(len(arr)):
            for v in dfs(arr[:i] + arr[i+1:]):
                res.append(v+arr[i])
                res.append(v)
        return res
    
    for v in dfs(words):
        dic[v] = 1
        
    for b in babbling:
        if b in dic:
            answer+=1
            
    return answer