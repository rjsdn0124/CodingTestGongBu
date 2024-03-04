def solution(numbers, target):
    answer = 0
    def rec(arr):
        res = []
        if len(arr) == 0:
            return [[]]
        for n in rec(arr[1:]):
            res.append([-arr[0]] + n)
            res.append([arr[0]] + n)
        return res
    
    for a in rec(numbers):
        if sum(a) == target:
            answer +=1
    return answer