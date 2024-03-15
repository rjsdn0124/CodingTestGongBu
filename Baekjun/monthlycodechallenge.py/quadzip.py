def solution(arr):
    answer = []
    
    def rec(arr):
        n = len(arr)
        if n == 1:
            return arr[0]
        tn = n // 2
        res = []
        res += rec(list(arr[i][:tn] for i in range(tn)))
        res += rec(list(arr[i][tn:] for i in range(tn)))
        res += rec(list(arr[i][:tn] for i in range(tn,n)))
        res += rec(list(arr[i][tn:] for i in range(tn,n)))
        if len(res) == 4:
            temp = res[0]
            for i in range(len(res)):
                if not temp == res[i]:
                    return res
            res = [temp]
            
        return res
    
    result = rec(arr)
    s = sum(result)
    l = len(result)
    return [l-s,s]